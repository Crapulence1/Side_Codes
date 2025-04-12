package textAdventure.Locations;

import textAdventure.Entities.Enemys.Enemy;

public class Room {
    private Coordinate coordinate;
    private String description;
    private Enemy enemy;

    public Room(String description, Coordinate coordinate, Enemy enemy) {
        this.coordinate = coordinate;
        this.description = description;
        this.enemy = enemy;
    }

    public Room(String description, Coordinate coordinate) {
        this.coordinate = coordinate;
        this.description = description;
    }

    public Coordinate getCoordinate(){
        return coordinate;
    }
    public String getDescription(){
        return description;
    }
    public Enemy getEnemy(){
        return enemy;
    }
    public boolean containsEnemy(){
        return enemy != null;
    }



}
