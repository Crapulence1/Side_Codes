package textAdventure.Locations;

public enum Direction {
    north("You travel North", 0, 1),
    south("You travel South", 0, -1),
    east("You travel East", 1, 0),
    west("You travel West", -1, 0);
    private final int dx;
    private final int dy;
    private final String msg;

    Direction(String msg,int dx, int dy) {
        this.msg=msg;
        this.dx = dx;
        this.dy = dy;
    }

    public String getMsg(){
        return msg;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}
