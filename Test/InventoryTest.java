import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class InventoryTest {
    private Inventory newInventory;
    private Weapon sword;
    private Weapon axe;
    private int currentdamage = 3;
    @BeforeEach
    public void setUp() throws Exception {
        newInventory = new Inventory();
        sword = new Weapon("sword", 4, "longsword", 0);
        axe = new Weapon("axe", 6, "large axe", 6);
    }
    @Test
    public void storesWeapon() {
        newInventory.addWeapon(sword);
        newInventory.addWeapon(axe);
        assertEquals("shovel, sword, axe, empty, empty, empty", newInventory.getInventory());
    }


    }

