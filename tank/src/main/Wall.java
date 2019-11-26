package main;

public class Wall extends MyImage {
	int id;

	Wall(Coord coord,int id){
		super(coord);
		this.id = id;
	}
}
