package main;

import java.awt.Rectangle;

public class MyImage {
	    int width = Game.width;
	    int height = Game.height;

	    Coord coord;
	    int x;
	    int y;

	    MyImage(int x, int y) {
	        this.x = x;
	        this.y = y;
	    }

	    MyImage(Coord coord) {
	        x = coord.x * width;
	        y = coord.y * height;
	        this.coord = coord;
	    }

	    private Rectangle getRect() {
	        return new Rectangle(x, y, width, height);
	    }

	    boolean isIntersects(MyImage other) {
	        return other.getRect().intersects(getRect());
	    }
}
