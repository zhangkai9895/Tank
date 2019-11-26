package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Main extends JFrame{
	private static int PlayTime = 0;
	
	
	public static void main(String[] args) {
		 EventQueue.invokeLater(() -> {
	            try {
	                Main frame = new Main();
	                frame.setResizable(false);
	                frame.setVisible(true);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        });

	}
	

	
	private Main() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - 600) / 3, (screenSize.height - 600) / 3, 900, 900);
        getContentPane().setLayout(new BorderLayout(0, 0));
        getContentPane().add(new Game());
      
	}
}
