/** Text adventure game. */
public class TextAdventure {

	public static void main(String[] args) {
		new TextAdventure().run();
	}

	/** Damage done by the best weapon the player has picked up. */
	private int bestWeaponDamage;

	/** The room where the player currently is. */
	private Room currentRoom;

	/** Total value of all treasures taken by the player. */
	private int score;

	private Inventory secondInventory;


	public TextAdventure() {
		// Create rooms
		Room entrance = new Room("forest entrance",
				"a long corridor of trees with a canopy blocking all light, a misty fog covers the ground");
		Room clearing = new Room("clearing", "a small clearing allowing light in");
		Room tree = new Room("hollow tree", "a giant tree with a small opening, inside is a giant axe");
		Room tunnel = new Room("tunnel", "a narrow tunnel leading underground, heat boiling through the opening");
		Room lair = new Room("lair",
				"a large cavern, steaming with volcanic heat");
		Room trial = new Room("test of faith", "a covered clearing with a ray of sun hitting an object in the center of the room");
		Room house = new Room("abandoned house", " a small seemingly deserted house");

		// Create connections
		entrance.addNeighbor("north", clearing);
		clearing.addNeighbor("south", entrance);
		clearing.addNeighbor("west", tree);
		tree.addNeighbor("east", clearing);
		clearing.addNeighbor("east", tunnel);
		tunnel.addNeighbor("south", lair);
		lair.addNeighbor("north", tunnel);
		tree.addNeighbor("north", trial);
		trial.addNeighbor("south", tree);
		clearing.addNeighbor("north", house);
		house.addNeighbor("south", clearing);
		// Create and install monsters
		clearing.setMonster(new Monster("wolf", 2, "a ferocious, snarling wolf"));
		lair.setMonster(new Monster("dragon", 6, "a fire-breathing dragon"));
		house.setMonster(new Monster("leprechaun", 4, "a greedy leprechaun hoarding his gold (kill with axe)"));
		// Create and install treasures
		clearing.setTreasure(new Treasure("diamond", 10,
				"a huge, glittering diamond"));
		lair.setTreasure(new Treasure("chalice", 30,
				"a golden chalice encrusted with all manner of gemstones"));
		house.setTreasure(new Treasure("gold", 60, "a large pile of gold"));

		// Create and install weapons

		Weapon axe = new Weapon("axe", 5, "a mighty battle axe (must take)", 3);
		tree.setWeapon(axe);
		Weapon sword = new Weapon("sword", 7, "a shimmering sword encased in stone", 6);
		trial.setWeapon(sword);
		//creates inventory

		secondInventory = new Inventory();

		// The player starts in the entrance, armed with a sword
		currentRoom = entrance;
		bestWeaponDamage = 3;

	}

	/**
	 * Attacks the specified monster and prints the result. If the monster is
	 * present, this either kills it (if the player's weapon is good enough) or
	 * results in the player's death (and the end of the game).
	 */
	public void attack(String name) {
		Monster monster = currentRoom.getMonster();
		if (monster != null && monster.getName().equals(name)) {
			if (bestWeaponDamage > monster.getArmor()) {
				StdOut.println("You strike it dead!");
				currentRoom.setMonster(null);
			} else {
				StdOut.println("Your blow bounces off harmlessly.");
				StdOut.println("The " + monster.getName() + " eats your head!");
				StdOut.println();
				StdOut.println("GAME OVER");
				System.exit(0);
			}
		} else {
			StdOut.println("There is no " + name + " here.");
		}
	}

	/** Moves in the specified direction, if possible. */
	public void go(String direction) {
		Room destination = currentRoom.getNeighbor(direction);
		if (destination == null) {
			StdOut.println("You can't go that way from here.");
		} else {
			currentRoom = destination;
		}
	}

	/** Handles a command read from standard input. */
	public void handleCommand(String line) {
		String[] words = line.split(" ");
		if (currentRoom.getMonster() != null
				&& !(words[0].equals("attack") || words[0].equals("look") || words[0].equals("I"))) {
			StdOut.println("You can't do that with unfriendlies about.");
			listCommands();
		} else if (words[0].equals("attack")) {
			attack(words[1]);
		} else if (words[0].equals("go")) {
			go(words[1]);
		} else if (words[0].equals("look")) {
			look();
		} else if (words[0].equals("take")) {
			take(words[1]);
		}else if (words[0].equals("I")) {
			StdOut.println("________Inventory________");
			StdOut.println("Weapon:           Damage:");
			secondInventory.getInventory();

			bestWeaponDamage = secondInventory.equipnew();


		} else {
			StdOut.println("I don't understand that.");
			listCommands();
		}
	}

	/** Prints examples of possible commands as a hint to the player. */
	public void listCommands() {
		StdOut.println("Examples of commands:");
		StdOut.println("  attack wolf");
		StdOut.println("  go north");
		StdOut.println("  look");
		StdOut.println("  take diamond");
		StdOut.println("  I to access inventory");
	}

	/** Prints a description of the current room and its contents. */
	public void look() {
		StdOut.println("You are in " + currentRoom.getDescription() + ".");
		if (currentRoom.getMonster() != null) {
			StdOut.println("There is "
					+ currentRoom.getMonster().getDescription() + " here.");
		}
		if (currentRoom.getWeapon() != null) {
			StdOut.println("There is "
					+ currentRoom.getWeapon().getDescription() + " here.");
		}
		if (currentRoom.getTreasure() != null) {
			StdOut.println("There is "
					+ currentRoom.getTreasure().getDescription() + " here.");
		}
		StdOut.println("Exits: " + currentRoom.listExits());
	}

	/** Runs the game. */
	public void run() {
		listCommands();
		StdOut.println();
		while (true) {
			StdOut.println("You are in the " + currentRoom.getName() + ".");
			StdOut.print("> ");
			handleCommand(StdIn.readLine());
			StdOut.println();
		}
	}

	/** Attempts to pick up the specified treasure or weapon. */
	public void take(String name) {
		Treasure treasure = currentRoom.getTreasure();
		Weapon weapon = currentRoom.getWeapon();
		if (treasure != null && treasure.getName().equals(name)) {
			currentRoom.setTreasure(null);
			score += treasure.getValue();
			StdOut.println("Your score is now " + score + " out of 100.");
			if (score == 100) {
				StdOut.println();
				StdOut.println("YOU WIN!");
				System.exit(0);
			}
		} else if (weapon != null && weapon.getName().equals(name) && weapon.getPrice()<=score) {
			currentRoom.setWeapon(null);
			if (weapon.getDamage() > bestWeaponDamage) {
				bestWeaponDamage = weapon.getDamage();
				score-=weapon.getPrice();
				//inventory.addItem(weapon.getName());
				secondInventory.addWeapon(weapon);

				StdOut.println(weapon.getName()+" added to inventory");
				StdOut.println("Your balance is now " + score);
				StdOut.println("You'll be a more effective fighter with this!");
			}
		} else {
			StdOut.println("There is no " + name + " here. If you tried to take a weapon, you already have it or cannot afford it");
		}
	}

}
