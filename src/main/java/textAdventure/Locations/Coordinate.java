package textAdventure.Locations;

public class Coordinate {
    private int x;
    private int y;
    public Coordinate(int x, int y){
        this.x=x;
        this.y=y;
    }

    public Coordinate move(Direction direction) {
        return new Coordinate(this.x + direction.getDx(), this.y + direction.getDy());
    }

    public Coordinate(String rawString){
        String[] parts = rawString.split(", ");
        this.x=Integer.parseInt(parts[0]);
        this.y=Integer.parseInt(parts[1]);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coordinate that = (Coordinate) obj;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }


    public String toString(){
        return x + ", " + y;
    }

}
