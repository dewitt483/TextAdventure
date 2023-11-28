import java.util.ArrayList;
import java.util.List;

/** A room. */
public class Room {

    /** This room's name. */
    private String name;
    /** A description of this room. */
    private String description;

    /** A list of the exists names corresponding to neighbor. */
    private ArrayList<String> exists = new ArrayList<String>();
    /** A list of the rooms corresponding to the exists names. */
    private ArrayList<Room> neighbors = new ArrayList<Room>();

    /** The treasure in this room. */
    private Treasure treasure;
    /** The weapon in this room. */
    private Weapon weapon;
    /** The monster in this room. */
    private Monster monster;
    /** The armor in this room. */
    //private Armor armor;

    /**
     * @param name
     *            This room's name.
     * @param description
     *            A description of this room.
     */
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        for (int i = 0; i <= 3; i++) {
            neighbors.add(null);
            exists.add(null);
        }
    }

    /** Returns a description of this weapon. */
    public String getDescription() {
        return description;
    }

    /** Returns this weapon's name. */
    public String getName() {
        return name;
    }

    /** Returns the corresponding room at given direction. */
    public Room getNeighbor(String direction) {
        // If statements for identifying the corresponding neighbor room.
        if (direction.equals("north")){
            return neighbors.get(0);
        } else if (direction.equals("east")){
            return neighbors.get(1);
        } else if (direction.equals("south")){
            return neighbors.get(2);
        } else if (direction.equals("west")){
            return neighbors.get(3);
        }

        return null;
    }

    /** Returns the corresponding treasure in the current room. */
    public Treasure getTreasure() { return treasure; }

    /** Returns the corresponding monster in the current room. */
    public Monster getMonster() { return monster; }

    /** Returns the corresponding weapon in the current room. */
    public Weapon getWeapon() { return weapon; }

    /** Returns the corresponding armor in the current room. */
    //public Armor getArmor() { return armor; }

    /** Set the given treasure to the room. */
    public void setTreasure(Treasure treasure) { this.treasure = treasure; }

    /** Set the given monster to the room. */
    public void setMonster(Monster monster) { this.monster = monster; }

    /** Set the given weapon to the room. */
    public void setWeapon(Weapon weapon) { this.weapon = weapon; }

    /** Set the given armor to the room. */
    //public void setArmor(Armor armor) { this.armor = armor; }

    /** Adds the corresponding room to the corresponding location. */
    public void addNeighbor(String direction, Room room) {
        // If statements for adding the room to the corresponding direction.
        if (direction.equals("north")){
            neighbors.set(0, room);
            exists.set(0, "north");
        } else if (direction.equals("east")){
            neighbors.set(1, room);
            exists.set(1, "east");
        } else if (direction.equals("south")){
            neighbors.set(2, room);
            exists.set(2, "south");
        } else if (direction.equals("west")){
            neighbors.set(3, room);
            exists.set(3, "west");
        }

    }

    /** Prints out the available rooms directions in this format:
     * [___,___,___,___]. */
    public String listExits() {
        // Building a string with the rooms that are available to enter.
        String availableExits = "[";
        if (exists.contains("north")){
            availableExits += "north, ";
        }
        if (exists.contains("south")){
            availableExits += "south, ";
        }
        if (exists.contains("west")){
            availableExits += "west, ";
        }
        if (exists.contains("east")){
            availableExits += "east, ";
        }
        availableExits = availableExits.substring(0, availableExits.length() - 2);
        availableExits += "]";
        return availableExits;

    }

}
