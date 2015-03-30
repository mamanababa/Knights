package ie.gmit.computing;

import ie.gmit.computing.characters.Hero;
import ie.gmit.computing.fuzzy.GCInteraction;

import java.util.Scanner;

public class Play {
	private Scanner scanner = new Scanner(System.in);
	private Semantic semantic = new Semantic();
	private Location startLoc;
	private Location goalLoc;
	private Location currentLoc;
	private Hero myGC;
	private boolean keepPlaying = true;

	public static void main(String[] args) {
		new Play();
	}

	public Play() {
		// initialize the game
		initial();
		// user input and play the game
		play();
	}

	private void play() {
		while (keepPlaying) {
			switch (scanner.nextLine()) {
			// look information of this location
			case "LOOK":
				currentLoc.look();
				break;

			// check items and lif force
			case "CHECK":
				myGC.check();
				break;

			// eat medicine or food
			case "EAT":
				eat();
				break;

			// get items of this location
			case "PICK":
				pick();
				break;

			// drop items
			case "DROP":
				drop();
				break;

			// fight with enemies
			case "FIGHT":
				fight();
				break;

			// go north
			case "N":

				break;

			// go south
			case "S":

				break;

			// go west
			case "W":

				break;

			// go east
			case "E":

				break;

			// go north－west
			case "NW":

				break;

			// go north-east
			case "NE":

				break;

			// go south-west
			case "SW":

				break;

			// go south-east
			case "SE":

				break;

			case "HELP":
				help();
				break;

			default:
				System.out
						.println("---Invalid, please input again! Or input HELP to check all commands.");
				break;
			}// end switch

			if (myGC.getLifeForce() == 0) {
				System.out.println("You are dead. GAME OVER");
				keepPlaying = false;
				System.exit(0);
			} else {

			}
		}// end while
	}// end play()

	private void fight() {
//		currentLoc.
		// new GCInteraction().fight(weapon, myGC, opponent);
	}

	private void eat() {
		System.out.println("---FOOD or MEDICINE ?");
		String eat = scanner.nextLine();
		if (eat.equals("FOOD") || eat.equals("MEDICINE"))
			myGC.useItem(eat);
		else
			System.out.println("You have no such item!");
	}// end eat()

	private void pick() {
		System.out.print("---");
		for (Item item : currentLoc.getItem())
			System.out.print(item.getObjName().toUpperCase() + ", ");
		System.out.println(" ?\n");
		String pick = scanner.nextLine();
		for (Item item : currentLoc.getItem()) {
			if (pick.toLowerCase().equals(item.getObjName())) {
				myGC.addItem(item);
				break;
			}
		}
	}// end pick()

	private void drop() {
		System.out.print("---");
		for (Item item : myGC.getItems())
			System.out.print(item.getObjName().toUpperCase() + ", ");
		System.out.println(" ?\n");
		String drop = scanner.nextLine();
		for (Item item : myGC.getItems()) {
			if (drop.toLowerCase().equals(item.getObjName())) {
				myGC.dropItem(item);
				break;
			}
		}
	}// end drop()

	private void help() {
		System.out.println("---LOOK to get information of this location");
		System.out.println("---CHECK to check all your items");
		System.out
				.println("---EAT to use medicine or food to increase life force");
		System.out.println("---PICK to pick items of this location");
		System.out.println("---DROP to drop items you want");
		System.out.println("---FIGHT to fight with enemies");
		System.out
				.println("---N/W/E/S/NW/NE/SW/SE to move to different locations");
	}// end help()

	private void initial() {
		// create map
		semantic.parseAndCreate();

		startLoc = semantic.getStartLoc();
		goalLoc = semantic.getGoalLoc();
		myGC = (Hero) Semantic.charaMap.get("Hero");
		currentLoc = myGC.getInitLocation().get(0);

		System.out
				.println("---------------Knights Templar Fuzzy Logic Grail Quest---------------\n");
		System.out.println("You are in " + currentLoc.getName() + ". "
				+ startLoc.getDescription());
		System.out.println("You have to take HolyGrail from "
				+ startLoc.getName() + " to " + goalLoc.getName() + ".");
		System.out
				.println("\n---------------------------------------------------------------------\n");
	}// end initial()

	private void celebrate() {

	}
}
