public class Monster {

    private String description;


    private String name;

    /** The value of this treasure. */
    private int value;
    public Monster(String name, int value, String description){
        this.name = name;
        this.value = value;
        this.description = description;

    }
    /** Returns a description of this treasure. */
    public String getDescription() {
        return description;
    }

    /** Returns this treasure's name. */
    public String getName() {
        return name;
    }

    /** Returns the value of this treasure. */
    public int getArmor() {
        return value;
    }


}
