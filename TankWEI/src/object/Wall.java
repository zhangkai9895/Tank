package object;

public class Wall extends MyImage {

    int id;
    public Wall(Coord coord, int id)
    {
        super(coord);
        this.id=id;
    }
}
