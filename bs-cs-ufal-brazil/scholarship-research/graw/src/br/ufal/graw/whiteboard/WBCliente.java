import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;
import java.applet.Applet;

// Classe principal, faz conecção com servidor e monta o applet
public class WBCliente extends Applet {

	Socket socket;							// Socket para connecção com o servidor
	DataInputStream in;						// Stream de leitura
	DataOutputStream out;					// Stream de escrita
	String server, porta, apelido, salaID;	// Parametros do applet
	WBClienteCanvas canvas;

	// Cria Panel com controles e Canvas para desenho gráfico.
	public void init() {
		
		// Recupera parametros
		server = getParameter("server");
		porta = getParameter("porta");
		apelido = getParameter("apelido");
		salaID = getParameter("salaID");
		
		// Conecta com o servidor
		try {
//			showStatus("Conectando com o servidor em " + server + ":" + porta);
			socket = new Socket(server, Integer.parseInt(porta));
//			showStatus("Conectado!");

			// Cria stream de leitura e gravação
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

		} catch (Exception e) {
//			showStatus("Erro conectando com o servidor: " + e.getMessage());
			return;
		}
		
		// Manda apelido e sala
		try {		
			out.writeBytes(apelido + "\n");
			out.writeBytes(salaID + "\n");
			out.flush();
		} catch (IOException e) { }

		// Utiliza BorderLayout e cor do sistema
		setBackground(new Color(0x00406F8F));
		setLayout(new BorderLayout());

		// Cria canvas para desenho gráfico
		canvas = new WBClienteCanvas(in, out);
		add(canvas, BorderLayout.CENTER);

		// Cria Panel de controles
		add(new WBClientePanel(canvas), BorderLayout.SOUTH);
	}
	
	// Remove o usuário quando ele fechar a janela
	public void destroy() {
		try {
			// Para o thread
			canvas.thread.stop();
			
			// Envia mensagem que usuário saiu
			out.writeByte(0xFF);
			out.flush();

			// Fecha o socket
			out.close();
			in.close();
			socket.close();
		} catch (Exception e) { }
	}
}

// Canvas para interface gráfica
class WBClienteCanvas extends Canvas implements MouseListener, MouseMotionListener, Runnable {
	
	int w, h;							// Dimensões da área de desenho
	Image image;						// Buffer secundário para o desenho
	int tool = 1;						// Ferramenta atual
	int xi, yi, xf, yf;					// Coordenadas iniciais e finais do objeto a ser desenado
	DataInputStream in;					// Stream de leitura
	DataOutputStream out;				// Stream de escrita
	Color cor = Color.black;			// Cor atual
	Graphics graphics = null;			// Interface para desenho
	Rectangle rect = new Rectangle();	// Coordenadas para um retângulo
	Thread thread;
	boolean texto = true;
	
	// Construtor
	public WBClienteCanvas(DataInputStream in, DataOutputStream out) {
		super();
		
		// Streams de entrada e saída
		this.in = in;
		this.out = out;

		// Escuta ações do mouse
		addMouseListener(this);
		addMouseMotionListener(this);
		setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		
		// Cria o thread deste cliente
		thread = new Thread(this);
//		thread.start();
	}
	
	// Define cor do desenho
	public void setCor(Color cor) {
		this.cor = cor;
	}
	
	// Define modo de desenho
	public void setTool(int tool) {
		this.tool = tool;

		// Define cursor para a ferramenta
		setCursor(new Cursor(tool == 7 ? Cursor.TEXT_CURSOR : Cursor.CROSSHAIR_CURSOR));
	}
	
	// Prepara coordenadas do retângulo
	public void makeRect() {
		rect.x = Math.min(xi, xf);
		rect.y = Math.min(yi, yf);
		rect.width = Math.abs(xi - xf);
		rect.height = Math.abs(yi - yf);
	}

	// Desenha texto
	public void drawTexto(String txt, int x, int y) {
		texto = true;
		graphics.setColor(cor);
		graphics.drawString(txt, x, y);
		repaint();
		
		// Envia texto para os demais
		try {
			out.writeByte(7); out.writeInt(cor.getRGB());
			out.writeShort(x); out.writeShort(y);
			out.writeBytes(txt + "\n");
			out.flush();
		} catch (Exception e) {}
	}
	
	// Limpa a tela
	public void limparTela() {
		graphics.setColor(Color.white);
		graphics.fillRect(0, 0, w, h);
	}
	
	// Limpa a tela deste cliente e as dos demais
	public void limparTela2() {

		// Limpa a tela
		limparTela();
		repaint();
		
		// Envia mensagem para limpar a tela dos demais
		try {
			out.writeByte(0); out.writeInt(0);
			out.writeInt(0); out.writeInt(0);
			out.flush();
		} catch (Exception e) {}
	}
	
	// Não limpa nada, apenas atualiza tela
	public void update(Graphics g) {
		paint(g);
	}

	// Atualiza a tela
	public void paint(Graphics g) {

		// Se for a primeira vez, crie o buffer e limpe a tela
		if (graphics == null) {

			// Dimensões do applet
			Rectangle r = getBounds();
			w = r.width; h = r.height;
			
			// Cria Buffer
			image = createImage(w, h);
			graphics = image.getGraphics();
			
			// Limpa a tela
			limparTela();
			
			// Inicia o thread depois que a interface gráfica foi inicializada
			thread.start();
		}
		
		// Passa buffer para a tela
		g.drawImage(image, 0, 0, this);
	}

	// Sem funcionalidade, apenas para implementação da interface do mouse
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}	
	public void mouseMoved(MouseEvent e) {}

	// Coordenadas quando o mouse se move com o botão pressionado
	public void mouseDragged(MouseEvent e) {

		graphics.setColor(Color.white);

		// Seleciona ferramenta atual
		switch (tool) {
			case 1: // Pincel

				// Coordenadas do mouse
				xf = e.getX(); yf = e.getY();
		
				// Desenha linha
				graphics.setColor(cor);
				graphics.drawLine(xi, yi, xf, yf);
				repaint();		
		
				// Envia coordenadas do ponto
				try {
					out.writeByte(tool); out.writeInt(cor.getRGB());
					out.writeShort(xi); out.writeShort(yi);
					out.writeShort(xf); out.writeShort(yf);
					out.flush();
				} catch (Exception ex) { }
		
				// Atualiza coordenadas iniciais com as finais
				xi = xf; yi = yf;
				break;
			
			case 2: // Linha
			
				// Prepara máscara para desenhar
				graphics.setXORMode(Color.gray);

				// Desenha linha
				graphics.drawLine(xi, yi, xf, yf);

				// Coordenadas do mouse
				xf = e.getX(); yf = e.getY();

				// Desenha linha
				graphics.drawLine(xi, yi, xf, yf);
				repaint();

				// Volta ao modo normal de desenho
				graphics.setPaintMode();

				break;

			case 3: case 4: // Quadrados

				// Prepara máscara para desenhar
				graphics.setXORMode(Color.gray);

				// Apaga o retângulo anterior
				graphics.drawRect(rect.x, rect.y, rect.width, rect.height);
				
				// Prepara retângulo com coordenadas atuais
				xf = e.getX(); yf = e.getY();
				makeRect();

				// Desenha novo retângulo
				graphics.drawRect(rect.x, rect.y, rect.width, rect.height);
				repaint();

				// Volta ao modo normal de desenho
				graphics.setPaintMode();
				break;

			case 5: case 6: // Círculos

				// Prepara máscara para desenhar
				graphics.setXORMode(Color.gray);
				
				// Apaga círculo anterior
				graphics.drawOval(rect.x, rect.y, rect.width, rect.height);
				
				// Prepara retângulo com coordenadas atuais
				xf = e.getX(); yf = e.getY();
				makeRect();

				// Desenha novo círculo
				graphics.drawOval(rect.x, rect.y, rect.width, rect.height);
				repaint();

				// Volta ao modo normal de desenho
				graphics.setPaintMode();
				break;
		}
	}

	// Coordenadas iniciais do desenho
	public void mousePressed(MouseEvent e) {

		// Coodenadas iniciais do desenho
		xi = e.getX(); yi = e.getY();
		
		// Se a ferramenta não for um pincel, as coordenadas finais serão iguais as inicias
		if (tool > 1 && tool < 7) {

			// Prepara retângulo
			xf = xi; yf = yi;
			makeRect();
		}
		
		if (tool == 7 && texto) {
			texto = false;
			MensagemDialog mDialog = new MensagemDialog(new Frame(), this, xi, yi + 4);
		}
	}

	// Coordenadas finais do desenho
	public void mouseReleased(MouseEvent e) {
		
		// Conclui desenho se a ferramenta for diferente do pincel
		if (tool > 1 && tool < 7) {

			// Seleciona ferramenta atual
			switch (tool) {

				case 2: // Linha
					graphics.setColor(cor);
					graphics.drawLine(xi, yi, xf, yf);
					break;

				case 3: // Quadrado
					graphics.setXORMode(Color.gray);
					graphics.drawRect(rect.x, rect.y, rect.width, rect.height);
					graphics.setPaintMode();
					graphics.setColor(cor);
					graphics.drawRect(rect.x, rect.y, rect.width, rect.height);
					break;

				case 4: // Quadrado Preenchido
					graphics.setXORMode(Color.gray);
					graphics.drawRect(rect.x, rect.y, rect.width, rect.height);
					graphics.setPaintMode();
					graphics.setColor(cor);
					graphics.fillRect(rect.x, rect.y, rect.width, rect.height);
					break;

				case 5: // Círculo
					graphics.setXORMode(Color.gray);
					graphics.drawOval(rect.x, rect.y, rect.width, rect.height);
					graphics.setPaintMode();
					graphics.setColor(cor);
					graphics.drawOval(rect.x, rect.y, rect.width, rect.height);
					break;

				case 6: // Círculo Preenchido
					graphics.setXORMode(Color.gray);
					graphics.drawOval(rect.x, rect.y, rect.width, rect.height);
					graphics.setPaintMode();
					graphics.setColor(cor);
					graphics.fillOval(rect.x, rect.y, rect.width, rect.height);
					break;
			}

			// Atualiza a tela
			repaint();

			// Envia coordenadas do desenho
			try {
				out.writeByte(tool); out.writeInt(cor.getRGB());
				if (tool == 2) {
					out.writeShort(xi);	out.writeShort(yi);
					out.writeShort(xf); out.writeShort(yf);
				} else {
					out.writeShort(rect.x);	out.writeShort(rect.y);
					out.writeShort(rect.width); out.writeShort(rect.height);
				}
				out.flush();
			} catch (Exception ex) { }
		}
	}

	// Recebe informações do servidor para desenhar
	public void run() {
		int rgb;				// Cor utilizada pelo outro usuário
		int tool;				// Ferramenta utilizada pelo outro usuário
		String texto = "";
		int xi, yi, xf = 0, yf = 0;		// Coordenadas do desenho do outro usuário
		
		// Loop infinito
		while (true) try {

			// Ler informações do servidor
			tool = in.readUnsignedByte(); rgb = in.readInt();
			xi = in.readShort(); yi = in.readShort();
			if (tool < 7) {
				xf = in.readShort(); yf = in.readShort();
			} else if (tool == 7) texto = in.readLine();
			
			// Limpa a tela
			if (tool == 0) limparTela();

			// Executa a ação apropiada
			else {
				graphics.setColor(new Color(rgb));
					
				switch (tool) {
					case 1: // Pincel
					case 2: // Linha
						graphics.drawLine(xi, yi, xf, yf);
						break;

					case 3: // Quadrado
						graphics.drawRect(xi, yi, xf, yf);
						break;

					case 4: // Quadrado Preenchido
						graphics.fillRect(xi, yi, xf, yf);
						break;

					case 5: // Círculo
						graphics.drawOval(xi, yi, xf, yf);
						break;

					case 6: // Círculo Preenchido
						graphics.fillOval(xi, yi, xf, yf);
						break;
						
					case 7: // Desenha texto
						graphics.drawString(texto, xi, yi);
						break;
				}
			}

			// Atualiza a tela
			repaint();

		} catch (Exception e) { }
	}
}

class ColorTool extends Canvas {
	
	WBClienteCanvas wbcliente;
	int cores[] = {0xC0C0C0, 0x404040, 0xFF0000, 0xFF00FF, 0xFFFF00, 0x00FF00, 0x00FFFF, 0x0000FF,
				   0x808080, 0x000000, 0x800000, 0x800080, 0x808000, 0x008000, 0x008080, 0x000080};
	
	public ColorTool(WBClienteCanvas wbcliente) {
		super();
		resize(84, 20);
		this.wbcliente = wbcliente;
	}
	
	public void update(Graphics g) {
		paint(g);
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, 84, 20);

		for (int i = 0; i < 8; i++) {
			g.setColor(new Color(cores[i]));
			g.fillRect(10 * i + 2, 2, 10, 8);
			g.setColor(new Color(cores[i + 8]));
			g.fillRect(10 * i + 2, 10, 10, 8);
		}
	}

	public boolean handleEvent(Event e) {
		
		if (e.id == Event.MOUSE_DOWN && e.x >= 2 && e.x <= 81 && e.y >= 2 && e.y <= 17) {
			wbcliente.setCor(new Color(cores[(e.x - 2) / 10 + (e.y >= 10 ? 8 : 0)]));
			return true;
		}

		return super.handleEvent(e);
	}
}

class PencilTool extends Canvas {
	
	WBClienteCanvas wbcliente;
	
	public PencilTool(WBClienteCanvas wbcliente) {
		super();
		resize(20, 20);
		this.wbcliente = wbcliente;
	}
	
	public void update(Graphics g) {
		paint(g);
	}
	
	public void paint(Graphics g) {
		g.setColor(SystemColor.control);
		g.fill3DRect(0, 0, 20, 20, true);
		g.setColor(Color.black);
		g.drawLine(3, 12, 3, 16);
		g.drawLine(3, 16, 7, 16);
		g.drawLine(7, 16, 16, 7);
		g.drawLine(16, 7, 12, 3);
		g.drawLine(12, 3, 4, 11);
		g.drawLine(4, 11, 8, 15);

	}

	public boolean handleEvent(Event e) {
		if (e.id == Event.MOUSE_DOWN) {
			wbcliente.setTool(1);
			return true;
		}

		return super.handleEvent(e);
	}
}

class LineTool extends Canvas {
	
	WBClienteCanvas wbcliente;
	
	public LineTool(WBClienteCanvas wbcliente) {
		super();
		resize(20, 20);
		this.wbcliente = wbcliente;
	}
	
	public void update(Graphics g) {
		paint(g);
	}
	
	public void paint(Graphics g) {
		g.setColor(SystemColor.control);
		g.fill3DRect(0, 0, 20, 20, true);
		g.setColor(Color.black);
		g.drawLine(16, 3, 3, 16);
	}	

	public boolean handleEvent(Event e) {
		if (e.id == Event.MOUSE_DOWN) {
			wbcliente.setTool(2);
			return true;
		}

		return super.handleEvent(e);
	}
}

class BoxTool extends Canvas {
	
	WBClienteCanvas wbcliente;
	
	public BoxTool(WBClienteCanvas wbcliente) {
		super();
		resize(20, 20);
		this.wbcliente = wbcliente;
	}
	
	public void update(Graphics g) {
		paint(g);
	}
	
	public void paint(Graphics g) {
		g.setColor(SystemColor.control);
		g.fill3DRect(0, 0, 20, 20, true);
		g.setColor(Color.black);
		g.drawRect(3, 3, 13, 13);
	}	

	public boolean handleEvent(Event e) {
		if (e.id == Event.MOUSE_DOWN) {
			wbcliente.setTool(3);
			return true;
		}

		return super.handleEvent(e);
	}
}

class FillBoxTool extends Canvas {
	
	WBClienteCanvas wbcliente;

	public FillBoxTool(WBClienteCanvas wbcliente) {
		super();
		resize(20, 20);
		this.wbcliente = wbcliente;
	}
	
	public void update(Graphics g) {
		paint(g);
	}
	
	public void paint(Graphics g) {
		g.setColor(SystemColor.control);
		g.fill3DRect(0, 0, 20, 20, true);
		g.setColor(Color.black);
		g.fillRect(3, 3, 14, 14);
	}	

	public boolean handleEvent(Event e) {
		if (e.id == Event.MOUSE_DOWN) {
			wbcliente.setTool(4);
			return true;
		}

		return super.handleEvent(e);
	}
}

class OvalTool extends Canvas {
	
	WBClienteCanvas wbcliente;
	
	public OvalTool(WBClienteCanvas wbcliente) {
		super();
		resize(20, 20);
		this.wbcliente = wbcliente;
	}
	
	public void update(Graphics g) {
		paint(g);
	}
	
	public void paint(Graphics g) {
		g.setColor(SystemColor.control);
		g.fill3DRect(0, 0, 20, 20, true);
		g.setColor(Color.black);
		g.drawOval(3, 3, 13, 13);
	}	

	public boolean handleEvent(Event e) {
		if (e.id == Event.MOUSE_DOWN) {
			wbcliente.setTool(5);
			return true;
		}

		return super.handleEvent(e);
	}
}

class FillOvalTool extends Canvas {
	
	WBClienteCanvas wbcliente;
	
	public FillOvalTool(WBClienteCanvas wbcliente) {
		super();
		resize(20, 20);
		this.wbcliente = wbcliente;
	}
	
	public void update(Graphics g) {
		paint(g);
	}
	
	public void paint(Graphics g) {
		g.setColor(SystemColor.control);
		g.fill3DRect(0, 0, 20, 20, true);
		g.setColor(Color.black);
		g.fillOval(3, 3, 14, 14);
	}	

	public boolean handleEvent(Event e) {
		if (e.id == Event.MOUSE_DOWN) {
			wbcliente.setTool(6);
			return true;
		}

		return super.handleEvent(e);
	}
}

// Cria Panel com os botões
class WBClientePanel extends Panel implements ActionListener {
	
	Button limpar, texto;
	WBClienteCanvas canvas;	
	
	// Cria botões
	public WBClientePanel(WBClienteCanvas canvas) {
		super();
		this.canvas = canvas;
		
		// Usa FlowLayout
		setLayout(new FlowLayout());

		// Cria botão limpar
		limpar = new Button("Limpar");
		limpar.addActionListener(this);
		add(limpar);

		// Barra de cores
		add(new ColorTool(canvas));
		
		// Adiciona ferramentas
		add(new PencilTool(canvas));
		add(new LineTool(canvas));
		add(new BoxTool(canvas));
		add(new FillBoxTool(canvas));
		add(new OvalTool(canvas));
		add(new FillOvalTool(canvas));
		
		texto = new Button("T");
		texto.addActionListener(this);
		add(texto);
	}

	// Executa a ação dos botões
	public void actionPerformed(ActionEvent evt) {
		if (evt.getActionCommand().equals("Limpar")) canvas.limparTela2();
		else if (evt.getActionCommand().equals("T")) canvas.setTool(7);
	}
}

class MensagemDialog extends Dialog implements ActionListener {
	int x, y;
	TextField texto;
	Button ok, cancel;
	WBClienteCanvas canvas;
	
	public MensagemDialog(Frame frame, WBClienteCanvas canvas, int x, int y) {
		super(frame, "WhiteBoard");
		this.canvas = canvas;
		this.x = x;
		this.y = y;
		
		setBackground(SystemColor.control);
		setResizable(false);
		setLayout(new BorderLayout());

		add(new Label("Digite o texto", Label.CENTER), BorderLayout.NORTH);

		texto = new TextField(40);
		add(texto, BorderLayout.CENTER);
		
		Panel panel = new Panel();
				
		ok = new Button("OK");
		ok.addActionListener(this);
		panel.add(ok);
		
		cancel = new Button("Cancelar");
		cancel.addActionListener(this);
		panel.add(cancel, BorderLayout.SOUTH);
		
		add(panel, BorderLayout.SOUTH);
		
		pack();		

		Toolkit tk = getToolkit();;
		Dimension dim = tk.getScreenSize(), dim2 = getSize();		
		setLocation((dim.width - dim2.width) / 2, (dim.height - dim2.height) / 2);

		show();		
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Cancelar")) dispose();
		else if (e.getActionCommand().equals("OK")) {
			canvas.drawTexto(texto.getText(), x, y);
			dispose();
		}
	}

    public boolean handleEvent(Event e) {
		if (e.id == Event.WINDOW_DESTROY) dispose();
		else if (e.key == 10) {
			canvas.drawTexto(texto.getText(), x, y);
			dispose();
		}
		return false;
    }
}