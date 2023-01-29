package com.wlv.abominodo.views;
import java.awt.*;
import javax.swing.*;

import com.wlv.abominodo.controller.Aardvark;
import com.wlv.abominodo.models.Domino;

//import java.awt.event.*;
//import java.net.URL;

public class PictureFrame{
  public int[] reroll = null;
  Aardvark master = null;

  public class DominoPanel extends JPanel {
    private static final long serialVersionUID = 4190229282411119364L;

    public void drawGrid(Graphics g) {
    	
      for (int are = 0; are < 7; are++) {
        for (int see = 0; see < 8; see++) {
          drawDigitGivenCentre(g, 30 + see * 20, 30 + are * 20, 20, 
        		  master.grid[are][see], Color.BLACK);
        }
      }
    }
    
    
   

    public void drawGridLines(Graphics g) {
      g.setColor(Color.LIGHT_GRAY);
      for (int are = 0; are <= 7; are++) {
        g.drawLine(20, 20 + are * 20, 180, 20 + are * 20);
      }
      for (int see = 0; see <= 8; see++) {
        g.drawLine(20 + see * 20, 20, 20 + see * 20, 160);
      }
    }

    public void drawHeadings(Graphics g) {
      for (int are = 0; are < 7; are++) {
        fillDigitGivenCentre(g, 10, 30 + are * 20, 20, are+1);
      }

      for (int see = 0; see < 8; see++) {
        fillDigitGivenCentre(g, 30 + see * 20, 10, 20, see+1);
      }
    }

    public void drawDomino(Graphics g, Domino d) 
    {
      if (d.placed) 
      {
        int y = Math.min(d.ly, d.hy);
        int x = Math.min(d.lx, d.hx);
        int w = Math.abs(d.lx - d.hx) + 1;
        int h = Math.abs(d.ly - d.hy) + 1;
        g.setColor(Color.WHITE);
        //g.setColor(c);
        g.fillRect(20 + x * 20, 20 + y * 20, w * 20, h * 20);
        g.setColor(Color.RED);
        g.drawRect(20 + x * 20, 20 + y * 20, w * 20, h * 20);
        drawDigitGivenCentre(g, 30 + d.hx * 20, 30 + d.hy * 
        		20, 20, d.high, Color.BLUE);
        drawDigitGivenCentre(g, 30 + d.lx * 20, 30 + 
        		d.ly * 20, 20, d.low,  Color.BLUE);
      }
    }

   /* void drawDigitGivenCentre(Graphics g, int x, int y, int diameter, int n) {
     // int radius = diameter / 2;	// The value of the local 
      * variable radius is not used - this is a bad smell (Temporary Field) this fixed like this
      g.setColor(Color.BLACK);
      // g.drawOval(x - radius, y - radius, diameter, diameter);
      FontMetrics fm = g.getFontMetrics();
      String txt = Integer.toString(n);
      g.drawString(txt, x - fm.stringWidth(txt) / 2, y + fm.getMaxAscent() / 2);
    }*/

    
    void drawDigitGivenCentre(Graphics g, int x, int y, int diameter, int n, Color c) {
      // int radius = diameter / 2; // This local variable radius is not used 
      g.setColor(c);
      // g.drawOval(x - radius, y - radius, diameter, diameter);
      FontMetrics fm = g.getFontMetrics();
      String txt = Integer.toString(n);
      g.drawString(txt, x - fm.stringWidth(txt) / 2, y + fm.getMaxAscent() / 2);
    }

    void fillDigitGivenCentre(Graphics g, int x, int y, int diameter, int n) {
      int radius = diameter / 2;
      g.setColor(Color.GREEN);
      g.fillOval(x - radius, y - radius, diameter, diameter);
      g.setColor(Color.BLACK);
      g.drawOval(x - radius, y - radius, diameter, diameter);
      FontMetrics fm = g.getFontMetrics();
      String txt = Integer.toString(n);
      g.drawString(txt, x - fm.stringWidth(txt) / 2, y + fm.getMaxAscent() / 2);
    }

    protected void paintComponent(Graphics g) {
      g.setColor(Color.YELLOW);
      g.fillRect(0, 0, getWidth(), getHeight());

      if (master.mode == 1) {
        drawGridLines(g);
        drawHeadings(g);
        drawGrid(g);
        master.drawGuesses(g);
      }
      if (master.mode == 0) {
        drawGridLines(g);
        drawHeadings(g);
        drawGrid(g);
        master.drawDominoes(g);
      }
    }

    public Dimension getPreferredSize() {
      return new Dimension(202, 182);
    }
  }

  public DominoPanel dp;
  public DominoPanel myDp=new DominoPanel();
  // public void pictureFrame(Aardvark sf) { // This method name is not same as constructor name 
  public void pictureFrame(Aardvark sf) {
    master = sf;
    if (dp == null) {
      JFrame f = new JFrame("Abominodo");
      dp = new DominoPanel();
      f.setContentPane(dp);
      f.pack();
      f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      f.setVisible(true);
      // calling to implemented forms
      setUpDifficultyLevels();
      setUpMainMenuController();
      setUpProfile();
      
      
    }
  }
  
  
  
  public void setUpProfile() {

	  JFrame jf = new JFrame("Player Profile");
      Container apt = jf.getContentPane();
      apt.setLayout(new FlowLayout(FlowLayout.CENTER, 12, 12));
      
      JLabel label = new JLabel("<html>Abominodo"
      		+ "<br></html> ", SwingConstants.CENTER);
      
      JLabel label1 = new JLabel("<html>The Dominoes Puzzle Game"
        		+ "<br></html> ", SwingConstants.CENTER);
      
      JLabel label2 = new JLabel("<html>Version 1.0 (c), "
      		+ "Kevan Buckley, 2010<br></html>", SwingConstants.CENTER);
      
      label.setForeground(Color.getHSBColor(102, 0, 0));
      label.setFont(new java.awt.Font("Footlight MT Light", 1, 40));
      label.setVerticalAlignment(SwingConstants.TOP);
      label.setPreferredSize(new Dimension(500,60));
      apt.add(label);
      
      label1.setForeground(Color.RED);
      label1.setFont(new java.awt.Font("Footlight MT Light", 1, 14));
      label1.setVerticalAlignment(SwingConstants.TOP);
      label1.setPreferredSize(new Dimension(800, 80));
      apt.add(label1);

      JTextField tx = new JTextField("Player Name", 16);
      tx.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
      tx.setHorizontalAlignment(javax.swing.JTextField.CENTER);
      tx.setPreferredSize(new Dimension(200, 50));
      tx.setForeground(Color.BLACK);
      tx.setToolTipText("Player Name");
      apt.add(tx);
 
      
      JButton button = new JButton(); 
      button.setText("Continue");
      button.setHorizontalTextPosition(SwingConstants.LEADING); 
      button.setVerticalTextPosition(SwingConstants.BOTTOM);    
      button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
      button.setBackground(Color.blue);
      button.setForeground(Color.WHITE);
      button.setPreferredSize(new Dimension(200, 38));
      apt.add(button);
 
      label2.setForeground(Color.black);
      label2.setFont(new java.awt.Font("Tahoma", 1, 8));
      label2.setVerticalAlignment(SwingConstants.BOTTOM);
      label2.setPreferredSize(new Dimension(200, 100));
      apt.add(label2);
 
      jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      jf.setLocationRelativeTo(null); // center window on the screen
      jf.setSize(600, 400); 
      jf.setVisible(true);
      jf.toFront();
      
  }
 

   
  public void setUpMainMenuController() {
	 
	  JFrame jf = new JFrame("Main Menu - Controlling Window");
	  
      Container apt = jf.getContentPane();
      apt.setLayout(new FlowLayout(FlowLayout.CENTER, 12, 12));
      
      JLabel label = new JLabel("<html>Let's Start"
      		+ "<br></html> ", SwingConstants.CENTER);
      
      JLabel label1 = new JLabel("<html>Choose the option that you need to be proceed"
        		+ "<br></html> ", SwingConstants.CENTER);
      
      JLabel label2 = new JLabel("<html>Version 1.0 (c), "
      		+ "Kevan Buckley, 2010<br></html>", SwingConstants.CENTER);
      
      label.setForeground(Color.getHSBColor(0, 102, 161));
      label.setFont(new java.awt.Font("Footlight MT Light", 1, 40));
      label.setVerticalAlignment(SwingConstants.TOP);
      label.setPreferredSize(new Dimension(500,60));
      apt.add(label);
      
      label1.setForeground(Color.BLACK);
      label1.setFont(new java.awt.Font("Footlight MT Light", 1, 14));
      label1.setVerticalAlignment(SwingConstants.TOP);
      label1.setPreferredSize(new Dimension(800, 80));
      apt.add(label1);

      // Button 01 - Play Option
      JButton button = new JButton(); 
      button.setText("Play");
      button.setHorizontalTextPosition(SwingConstants.LEADING); 
      button.setVerticalTextPosition(SwingConstants.BOTTOM);    
      button.setFont(new java.awt.Font("Comic Sans MS", 1, 16));
      button.setBackground(Color.blue);
      button.setForeground(Color.WHITE);
      button.setPreferredSize(new Dimension(200, 38));
      apt.add(button);
     
      // Button 02 - View high scores
      JButton button2 = new JButton(); 
      button2.setText("View high scores");
      button2.setHorizontalTextPosition(SwingConstants.LEADING); 
      button2.setVerticalTextPosition(SwingConstants.BOTTOM);    
      button2.setFont(new java.awt.Font("Comic Sans MS", 1, 16));
      button2.setBackground(Color.blue);
      button2.setForeground(Color.WHITE);
      button2.setPreferredSize(new Dimension(200, 38));
      apt.add(button2);
 
   // Button 03 - View Rules
      JButton button3 = new JButton(); 
      button3.setText("View Rules");
      button3.setHorizontalTextPosition(SwingConstants.LEADING); 
      button3.setVerticalTextPosition(SwingConstants.BOTTOM);    
      button3.setFont(new java.awt.Font("Comic Sans MS", 1, 16));
      button3.setBackground(Color.blue);
      button3.setForeground(Color.WHITE);
      button3.setPreferredSize(new Dimension(200, 38));
      apt.add(button3);
      
     // Button 04 - Quit
      JButton button4 = new JButton(); 
      button4.setText("Quit");
      button4.setHorizontalTextPosition(SwingConstants.LEADING); 
      button4.setVerticalTextPosition(SwingConstants.BOTTOM);    
      button4.setFont(new java.awt.Font("Comic Sans MS", 1, 16));
      button4.setBackground(new Color(182, 5, 20));
      button4.setForeground(Color.WHITE);
      button4.setPreferredSize(new Dimension(200, 38));
      apt.add(button4);
      
      label2.setForeground(Color.black);
      label2.setFont(new java.awt.Font("Tahoma", 1, 8));
      label2.setVerticalAlignment(SwingConstants.BOTTOM);
      label2.setPreferredSize(new Dimension(200, 100));
      apt.add(label2);
 
      jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      jf.setLocationRelativeTo(null); // center window on the screen
      jf.setSize(600, 400); 
      jf.setVisible(true);
      jf.toFront();
      
  }
  
  	
  public void setUpDifficultyLevels() {
	  
	  JFrame jf = new JFrame("Dificulties Levels - Selection Window");
	  
      Container apt = jf.getContentPane();
      apt.setLayout(new FlowLayout(FlowLayout.CENTER, 12, 12));
      
      JLabel label = new JLabel("<html>Difficulty Levels"
      		+ "<br></html> ", SwingConstants.CENTER);
      
      JLabel label1 = new JLabel("<html>Choose Your Dificulty Level"
        		+ "<br></html> ", SwingConstants.CENTER);
      
      JLabel label2 = new JLabel("<html>Version 1.0 (c), "
      		+ "Kevan Buckley, 2010<br></html>", SwingConstants.CENTER);
      
      label.setForeground(Color.getHSBColor(0, 160, 161));
      label.setFont(new java.awt.Font("Footlight MT Light", 1, 40));
      label.setVerticalAlignment(SwingConstants.TOP);
      label.setPreferredSize(new Dimension(500,50));
      apt.add(label);
      
      label1.setForeground(Color.BLACK);
      label1.setFont(new java.awt.Font("Footlight MT Light", 1, 14));
      label1.setVerticalAlignment(SwingConstants.TOP);
      label1.setPreferredSize(new Dimension(800, 80));
      apt.add(label1);

      // Button 01 - Level : Simples
      JButton button = new JButton(); 
      button.setText("Simples");
      button.setHorizontalTextPosition(SwingConstants.LEADING); 
      button.setVerticalTextPosition(SwingConstants.TOP);    
      button.setFont(new java.awt.Font("Comic Sans MS", 1, 16));
      button.setBackground(Color.DARK_GRAY);
      button.setForeground(Color.WHITE);
      button.setPreferredSize(new Dimension(300, 45));
      apt.add(button);
     
      // Button 02 - View high scores
      JButton button2 = new JButton(); 
      button2.setText("Not simples");
      button2.setHorizontalTextPosition(SwingConstants.LEADING); 
      button2.setVerticalTextPosition(SwingConstants.BOTTOM);    
      button2.setFont(new java.awt.Font("Comic Sans MS", 1, 16));
      button2.setBackground(Color.DARK_GRAY);
      button2.setForeground(Color.WHITE);
      button2.setPreferredSize(new Dimension(300, 45));
      apt.add(button2);
 
      // Button 03 - View Rules
      JButton button3 = new JButton(); 
      button3.setText("Super shuffled");
      button3.setHorizontalTextPosition(SwingConstants.LEADING); 
      button3.setVerticalTextPosition(SwingConstants.BOTTOM); 
      button3.setHorizontalAlignment(SwingConstants.CENTER); 
      button3.setFont(new java.awt.Font("Comic Sans MS", 1, 16));
      button3.setBackground(Color.DARK_GRAY);
      button3.setForeground(Color.WHITE);
      button3.setPreferredSize(new Dimension(300, 45));
      apt.add(button3);
      
      label2.setForeground(Color.black);
      label2.setFont(new java.awt.Font("Tahoma", 1, 8));
      label2.setVerticalAlignment(SwingConstants.BOTTOM);
      label2.setPreferredSize(new Dimension(400, 50));
      apt.add(label2);
 
      jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      jf.setLocationRelativeTo(null); // center window on the screen
      jf.setSize(600, 500); 
      jf.setVisible(true);
      jf.toFront();
      
  	}
  /* public void reset(Aardvark sf) {
	 master = sf;
     if (dp == null) 
    {
      JFrame f = new JFrame("Abominodo");
      dp = new DominoPanel();
      f.setContentPane(dp);
      f.pack();
      f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      f.setVisible(false);
    }
  }*/
}   
