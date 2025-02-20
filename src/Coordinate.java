public class Coordinate {
    private int x; // x = index kolom
    private int y; // y = index baris

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //Constructor koordinat
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setCoordinate(int newX, int newY){
        this.x = newX;
        this.y = newY;
    }

    //Translasi koordinat
    public static Coordinate translateCoordinate(Coordinate coor, int dx, int dy) {
        return new Coordinate(coor.getX() - dx, coor.getY() - dy);
    }

}