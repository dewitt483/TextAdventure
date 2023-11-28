public class Weapon {
    private String name;

    private int value;

    private String description;
    private int price;



    public Weapon(String name, int value, String description, int price) {
            this.name = name;
            this.value = value;
            this.description = description;
            this.price = price;

    }
    public String getDescription() {
        return description;
    }


    public String getName() {
        return name;
    }


    public int getDamage() {
        return value;
    }
    public int getPrice(){return price;}

}
