package textAdventure.Locations;

import textAdventure.Locations.Coordinate;
import textAdventure.Locations.Room;

import java.util.HashMap;
import java.util.Map;

public class Location {
    String name;
    Map<Coordinate, Room> rooms;

    public Location(String name) {
        this.name = name;
        this.rooms = new HashMap<>();
    }

    public void addRoom(Room room) {
        rooms.put(room.getCoordinate(), room);
    }

    public Room getRoom(Coordinate coordinate) {
        return rooms.get(coordinate);
    }

    public boolean isRoomAvailable(Coordinate coordinate) {
        return rooms.containsKey(coordinate);
    }

    @Override
    public String toString() {
        return "Location: " + name + " with " + rooms.size() + " rooms.";
    }
}
