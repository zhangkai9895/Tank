package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.*;
public class Game extends JPanel {
	 private Image OffScreenImage;
	 private static Image image =  new ImageIcon(Game.class.getResource("/resources/wall.gif")).getImage();
	
	
	
	 static final int width = 40;
	 static final int height = 40;
	 
	 static final int HP = width;
	 static final int MP = width;
	 
	 static final int UP = 3;
	 static final int DOWN = 0;
	 static final int LEFT = 1;
	 static final int RIGHT = 2;
	 
	 final static int BLANK = -1;
	 final static int WALLS = -2;
	 final static int STEELS = -3;
	 
	 final static int WALL = 0;
	 
	 private final static int screenWidth = 900;
	 private final static int screenHeight = 600;
	 
	 volatile static int[][] map;
	 volatile static ConcurrentHashMap<Integer, Wall> walls = new ConcurrentHashMap<>();

	 public Game()
	 {

		 setForeground(Color.WHITE);
		 setBackground(Color.BLACK);
	     setBounds(0, 0, screenWidth, screenHeight);
	     setLayout(null);
	     init_map();
	     new Thread(new Draw()).start();
	 }
	 
	 private void init_map() {
		 int x = screenWidth/Game.width;
		 int y =screenHeight/Game.height;
		 map = new int[y][x];

		 for (int i = 0; i < x; i++) {
			 for (int j = 0; j < y; j++) {
				if (i==x||i==0)
				{
					Wall wall=new Wall(new Coord(i,j),WALL);
					walls.put(wall.hashCode(),wall);
				}



			 }
		 }
//		 for (int i = 0; i < x * y / 2; ++i) {
//
//
//	            Coord c = randomCoord();
//	            map[c.y][c.x] = WALLS;
//	            Wall wall = new Wall(c, WALL);
//	            walls.put(wall.hashCode(), wall);
//	        }
	 }
	 private Coord randomCoord() {
	        Random random = new Random(System.currentTimeMillis());
	        int x, y;
	        do {
	            y = random.nextInt(map.length);
	            x = random.nextInt(map[0].length);
	        } while (map[y][x] != BLANK);
	        return new Coord(x, y);
	    }
	
	synchronized public void paint(Graphics g) {
	        super.paint(g);
	        Graphics2D g2 = (Graphics2D) g;

	       
	        for (Wall wall : walls.values()) {
	            g2.drawImage(image, wall.x, wall.y, width, height, null);
	        }

	 }
	
	class Draw implements Runnable {
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
	 
}
