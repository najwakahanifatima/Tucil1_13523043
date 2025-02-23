import java.util.ArrayList;
import java.util.Objects;

public class Block {
    private ArrayList<Coordinate> position; // Array koordinat awal dari input (bentuk awal)
    private Coordinate location; // Lokasi (x,y) pada papan berdasarkan ref
    private final char letter;
    private final String color;
    private int ref; // Elemen acuan
    private final int version; // 8 Kemungkinan orientasi

    // Constructor
    public Block(char newLetter, String newColor){
        this.position = new ArrayList<>();
        this.location = null;
        this.letter = newLetter;
        this.color = newColor;
        this.ref = 0;
        this.version = 1;
    } 

    public Block(Block b){
        this.position = b.getPosition();
        this.location = b.getLocation();
        this.letter = b.getLetter();
        this.color = b.getColor();
        this.ref = b.getRef();
        this.version = b.getVersion();
    }

    public Block(char newLetter, String newColor, int ref, int version, Coordinate location, ArrayList<Coordinate> position){
        this.position = position;
        this.location = location;
        this.letter = newLetter;
        this.color = newColor;
        this.ref = ref;
        this.version = version;
    }

    // Getter and Setter
    public ArrayList<Coordinate> getPosition(){
        return this.position;
    }

    public Coordinate getLocation(){
        return this.location;
    }

    public char getLetter(){
        return this.letter;
    }

    public String getColor(){
        return this.color;
    }

    public int getRef(){
        return this.ref;
    }

    public int getVersion(){
        return this.version;
    }

    public void setLocation(Coordinate loc){
        this.location = loc;
    }

    public void setRef(int ref){
        this.ref = ref;
    }

    public void setPosition(ArrayList<Coordinate> pos){
        this.position = pos;
    }

    // Menambahkan elemen (koordinat) bentuk awal
    public void addInitPosition(int x, int y){
        position.add(new Coordinate(x, y));
    }

    public static void addBlockInitPosition(Block block, int x, int y){
        block.position.add(new Coordinate(x, y));
    }

    // Copy array list position
    public static ArrayList<Coordinate> copyPosition(ArrayList<Coordinate> pos){
        ArrayList<Coordinate> newPos = new ArrayList<>();
        for (Coordinate coord : pos){
            newPos.add(new Coordinate(coord.getX(), coord.getY()));
        }
        return newPos;
    }

    // Change orientation
    public static ArrayList<Coordinate> changeOrientation(ArrayList<Coordinate> init, int ref, int ver){
        ArrayList<Coordinate> newPos = copyPosition(init);
        Coordinate reff = init.get(ref);
        // Translate coordinate to elemen ref
        for (Coordinate coord : newPos){
            int x = coord.getX() - reff.getX();
            int y = coord.getY() - reff.getY();
            // Orient based on ver
            switch (ver) {
                case 1 -> {}
                case 2 -> coord.setCoordinate(-x, y); //mirror 'V' dari versi 1 (Awal)
                case 3 -> coord.setCoordinate(x, -y); //mirror 'H' dari versi 1 (Awal)
                case 4 -> coord.setCoordinate(y, -x); //rotate 90
                case 5 -> coord.setCoordinate(y, x); //mirror 'V' dari veri 4 (rot 90)
                case 6 -> coord.setCoordinate(-y, -x); //mirror 'H' dari versi 4 (rot 90)
                case 7 -> coord.setCoordinate(-x, -y); //rotate 180
                case 8 -> coord.setCoordinate(-y, x); //rotate 270
            }
        }
        return newPos;
    }

    // Menampilkan koordinat block
    public static void showShape(ArrayList<Coordinate> listCoord) {
        System.out.print('[');
        for (Coordinate pos : listCoord){
            System.out.print(" (" + pos.getX() + ',' + pos.getY() + ") ");
        }
        System.out.print(']');
    }

    // Penyesuaiian untuk remove dan add ArrayList<Block>
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Block block = (Block) obj;
        return Objects.equals(this.letter, block.letter); // Adjust properties as needed
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter); // Adjust properties as needed
    }

}



