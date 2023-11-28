import java.util.Scanner;

public class Inventory {
    private Weapon[] weapon_slots = new Weapon[3];

    Inventory() {
        weapon_slots[0] = new Weapon ("shovel", 3, "a weak weapon", 0);
        for (int i = 1; i < weapon_slots.length; i++) {
            weapon_slots[i] = new Weapon("empty", 0, "none", 0);


        }
    }
    public void addWeapon(Weapon weapon) {
        int i;
        for (i = 0; i < weapon_slots.length; i++) {
            if (weapon_slots[i].getName() == "empty") {
                weapon_slots[i] = weapon;
                break;
            }

            if (weapon_slots[weapon_slots.length - 1].getName() != "empty") {
                StdOut.println("Your Weapon inventory is full.");
            }
        }
    }

    public String getInventory(){
        String test = "";
        for (int i= 0; i<weapon_slots.length; i++){
             StdOut.println(weapon_slots[i].getName() + "           " + '\t' + weapon_slots[i].getDamage());
            test= test + weapon_slots[i].getName() +", ";
        }

        test = test.substring(0, test.length() - 2);
        return test;
    }

    public int equipWep(Weapon weapon) {
        if (weapon.getName() != "empty")
        {
             return weapon.getDamage();
        }
        else
            return 0;
    }

    public int equipnew() {
        //Scanner pandora_input1 = new Scanner(System.in);
        int in = 1;
        if (in == 1) {
            StdOut.println("Counting top to bottom, which weapon would you like to equip (enter a number)");
            Scanner input_1 = new Scanner(System.in);
            int pi2 = input_1.nextInt();
            return equipWep(weapon_slots[pi2 - 1]);

        }
        else
            return 0;

    }

}
