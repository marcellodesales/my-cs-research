package obliquelaunch;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.ImageIcon;
import java.awt.Point;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Toolkit;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import java.util.ArrayList;
import java.util.Vector;
/**
 *
 * @author  Marcello Junior
 * @version 22 de July de 2002, 16:01
 */
public class MathGraph extends JPanel implements ActionListener {

    /** The collection of position points of the projectile. */
    public ArrayList points;
    /** The position point of the screen when the user clicks on it. */
    public Point point;
    
    public int maxHeight;
    public int reach;
    
    /** The y coordenate */
    private int FLOOR = 0;
    private int WALL = 25;
    /** The preferred size of the entire graph. 775,280);*/
    int x = Toolkit.getDefaultToolkit().getScreenSize().width - 30;
    int y = Toolkit.getDefaultToolkit().getScreenSize().height - Toolkit.getDefaultToolkit().getScreenSize().height*43/100;
    private Dimension preferredSize = new Dimension(x,y);
    
    public JLabel background;
    
    private int frameNumber = -1;
    private boolean frozen  = false;
    private Timer timer;
    
    /** Collection of points to the animation */
    private Vector pointsGraph;
	
    /** Creates a new MathGraph with the collection of points. */
    public MathGraph(){//RectangleDemo controller) {
        //this.controller = controller;
	this.point  = new Point();
        this.points = new ArrayList();
        this.pointsGraph = new Vector();

        background = new JLabel();
        background.setPreferredSize(preferredSize);
        
        this.setLayout(new BorderLayout());
        this.add(background, BorderLayout.CENTER);

        int fps = 100;
        //How many milliseconds between frames?
        int delay = (fps > 0) ? (200 / fps) : 100;
        //Set up a timer that calls this object's action handler.
        timer = new Timer(delay, this);
        timer.setInitialDelay(0);
        timer.setCoalesce(true);
        
        //Border raisedBevel  = BorderFactory.createRaisedBevelBorder();
        //Border loweredBevel = BorderFactory.createLoweredBevelBorder();
        //Border compound     = BorderFactory.createCompoundBorder
                              //(raisedBevel, loweredBevel);
        //setBorder(compound);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                point.x = x;
                point.y = y;
                System.out.println("Pontox = "+ x);
                System.out.println("Pontoy = "+ y);
                boolean pointsArentEmpty = (!(points.isEmpty()));
                if (frozen && pointsArentEmpty) {
                    frozen = false;
                    startAnimation();
                    System.out.println("Inicializou a anim a partir de addMouseListener");
                } else {
                    frozen = true;
                    stopAnimation();
                    System.out.println("Parou a anim a partir de addMouseListener");
                }
            }
        });
    }
	
    //Can be invoked from any thread.
    public synchronized void startAnimation() {
      if (frozen) {
          //Do nothing.  The user has requested that we
          //stop changing the image.
      } else {
          //Start animating!
          if (!timer.isRunning()) {
              timer.start();
          }
          System.out.println("Inicializou a animação");
      }
    }

    //Can be invoked from any thread.
    public synchronized void stopAnimation() {
      //Stop the animating thread.
      if (timer.isRunning()) {
          timer.stop();
      }
      System.out.println("Parou a animação");
    }
 
    public void actionPerformed(ActionEvent e) {
        if (frameNumber < this.points.size()){
            //Advance animation frame.
            frameNumber++;
            //Display it.
            this.repaint();
        } else {
            this.stopAnimation();
            frameNumber = -1;
        }
    }

    /** Gets the preferred size of the screen. */
    /*public Dimension getPreferredSize() {
        return preferredSize;
    }*/
    
    private void printCoodenates(Graphics g){
        g.setColor(Color.black);
        //draws x coordenate
        this.FLOOR = this.getHeight() - 20;
        g.drawLine(this.WALL,this.FLOOR,this.getWidth(),this.FLOOR);
        //draws y coordenate
        g.drawLine(this.WALL,this.FLOOR,this.WALL,0);
    }
    
    /** Paint the graph on screen. */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  //paint background
        g.setColor(Color.white);
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        this.printCoodenates(g);
        Point point1 = new Point();
        Point point2 = new Point();
        g.setColor(Color.red);
        int a = frameNumber;
        if ((a < this.points.size()-1) && (a!=-1)){
              // Pegar todos os pontos do zero até o frame atual
              for (int i=0; i < a+1; i++){
                point1 = (Point)this.points.get(i);
                point2 = (Point)this.points.get(i+1);
                g.drawLine(point1.x+this.WALL,this.FLOOR-point1.y,point2.x+this.WALL,this.FLOOR-point2.y);
              }
        } else {
          Point point11 = new Point();
          Point point22 = new Point();
          g.setColor(Color.red);
          int reachY =0;
          for (int i = 0; i < this.points.size(); i++){
              int aa = i+1;
              if (!(aa ==  this.points.size())){
                  point11 = (Point)this.points.get(i);
                  point22 = (Point)this.points.get(aa);
                  g.drawLine(point11.x+this.WALL,this.FLOOR-point11.y,point22.x+this.WALL,this.FLOOR-point22.y);
                  reachY = point22.x;
              }
          }
          if (frameNumber != -1){
            g.setColor(Color.blue);
            int x = this.reach/2;
            int y = this.maxHeight;
            g.drawLine(x+this.WALL,this.FLOOR,x+this.WALL,this.FLOOR-y);
            g.setColor(Color.black);
            g.drawString("Altura máxima = "+y+"m",x+this.WALL-32,this.FLOOR-y/2);
            g.drawString("Alcance = "+this.reach+"m",this.reach+5,this.FLOOR+13);
            g.setColor(Color.blue);
            // draws the line of the reach
            g.drawLine(this.WALL,this.FLOOR,reachY+25,this.FLOOR);
          }
        }
    }
/*    public void paintComponent(Graphics g) {
        super.paintComponent(g);  //paint background

        Point point1 = new Point();
        Point point2 = new Point();
        g.setColor(Color.red);
        for (int i = 0; i < this.points.size(); i++){
            int a = i+1;
            if (!(a ==  this.points.size())){
                point1 = (Point)this.points.get(i);
                point2 = (Point)this.points.get(a);
                g.drawLine(point1.x+5,280-point1.y,point2.x+5,280-point2.y);
            }
        }
    }
*/
    /** Tests the component. */
    public static void main(String args[]){
        MathGraph a = new MathGraph();
        JFrame f = new JFrame("RectangleDemo");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        f.getContentPane().add(a);
        f.pack();
        f.setVisible(true);
    }
}
