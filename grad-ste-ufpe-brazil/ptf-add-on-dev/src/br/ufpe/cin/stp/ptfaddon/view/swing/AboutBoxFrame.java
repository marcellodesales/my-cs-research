package br.ufpe.cin.stp.ptfaddon.view.swing;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * <p>Title: PTF Add-on V3.0</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Center of Informatics (CIn)-UFPE Inc.</p>
 * @author Marcello Sales Jr.
 * @version 3.0
 */

public class AboutBoxFrame extends JDialog implements ActionListener {

  JPanel panel1 = new JPanel();
  JPanel insetsPanel1 = new JPanel();
  JButton button1 = new JButton();
  JLabel label1 = new JLabel();
  JLabel label2 = new JLabel();
  ImageIcon image1 = new ImageIcon();
  ImageIcon imageCin = new ImageIcon();
  ImageIcon imageUFPE = new ImageIcon();
  ImageIcon imageCenter of Informatics (CIn)-UFPE = new ImageIcon();

  BorderLayout borderLayout1 = new BorderLayout();
  String product = "PTF Add-on V3.0";
  String version = "3.0";
  String copyright = "Copyright (c) 2004";
  String comments = "";
  JPanel jPanel1 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel2 = new JPanel();
  JLabel labelMoto = new JLabel();
  BorderLayout borderLayout3 = new BorderLayout();
  JPanel jPanel3 = new JPanel();
  BorderLayout borderLayout4 = new BorderLayout();
  Border border1;
  TitledBorder titledBorder1;
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();

  public AboutBoxFrame(Frame parent) {
    super(parent);
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  AboutBoxFrame() {
    this(null);
  }

  //Component initialization
  private void jbInit() throws Exception  {
    imageCenter of Informatics (CIn)-UFPE = new ImageIcon(br.ufpe.cin.stp.ptfaddon.view.swing.MainFrame.class.getResource("motologo.gif"));
    imageCin = new ImageIcon(br.ufpe.cin.stp.ptfaddon.view.swing.MainFrame.class.getResource("logocin.gif"));
    imageUFPE = new ImageIcon(br.ufpe.cin.stp.ptfaddon.view.swing.MainFrame.class.getResource("ufpelogo.gif"));
    border1 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(165, 163, 151));
    titledBorder1 = new TitledBorder(new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(165, 163, 151)),"PTF Add-on Authors");
    label2.setToolTipText("");

    this.labelMoto.setIcon(imageCenter of Informatics (CIn)-UFPE);
    this.label2.setIcon(imageCin);
    label2.setText("");
    this.label1.setIcon(imageUFPE);
    label1.setText("");
    this.setTitle("About PTF Add-on");
    panel1.setLayout(borderLayout1);
    label1.setToolTipText("");
    button1.setText("Ok");
    button1.addActionListener(this);
    jPanel1.setLayout(borderLayout2);
    jPanel2.setLayout(borderLayout3);
    labelMoto.setText("");
    jPanel3.setLayout(borderLayout4);
    jLabel2.setText("");
    jPanel3.setBorder(titledBorder1);
    jLabel1.setText("José Elias (jeqca@cin.ufpe.br)");
    jLabel2.setText("Marcello Alves de Sales Jr. (masj2@cin.ufpe.br)");
    jLabel3.setText("PTF Add-on V3.0 Build 64");
    this.getContentPane().add(panel1, null);
    panel1.add(jPanel1,  BorderLayout.CENTER);
    jPanel1.add(label2, BorderLayout.CENTER);
    jPanel1.add(label1,  BorderLayout.EAST);
    jPanel1.add(jPanel2, BorderLayout.NORTH);
    jPanel2.add(labelMoto, BorderLayout.EAST);
    jPanel2.add(jPanel3,  BorderLayout.CENTER);
    jPanel3.add(jLabel1, BorderLayout.NORTH);
    jPanel3.add(jLabel2, BorderLayout.CENTER);
    insetsPanel1.add(jLabel3, null);
    insetsPanel1.add(button1, null);
    panel1.add(insetsPanel1, BorderLayout.SOUTH);
    setResizable(true);

  }

  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      cancel();
    }
    super.processWindowEvent(e);
  }

  //Close the dialog
  void cancel() {
    dispose();
  }

  //Close the dialog on a button event
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == button1) {
      cancel();
    }
  }
}
