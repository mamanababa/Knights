package ie.gmit.computing;

import ie.gmit.computing.Exit;
import ie.gmit.computing.Item;
import ie.gmit.computing.Location;
import ie.gmit.computing.characters.Brigands;
import ie.gmit.computing.characters.Saracen;
import ie.gmit.computing.characters.Friend;
import ie.gmit.computing.characters.GameCharacter;
import ie.gmit.computing.characters.Hero;
import ie.gmit.computing.characters.Neutral;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Semantic {

	public static Map<String, Location> locMap = new HashMap<String, Location>();
	public static List<Edges> edgeList = new ArrayList<Edges>();
	public static Map<String, GameCharacter> charaMap = new HashMap<String, GameCharacter>();
	private Location startLoc = null;
	private Location goalLoc = null;

	public void parseAndCreate() {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			// get the document tree of this file
			Document doc = dBuilder.parse(new File("information.xml"));
			if (doc != null) {
				// get all location elements
				NodeList locNodeList = doc.getElementsByTagName("location");
				// System.out.println("total locations: " +
				// locNodeList.getLength());
				// loop all location elements
				for (int i = 0; i < locNodeList.getLength(); i++) {
					// every single location element
					Element eleLoc = (Element) locNodeList.item(i);
					// set location information
					setLocationInfo(eleLoc);
				}

				// get all edge elements
				NodeList edgeNodeList = doc.getElementsByTagName("edge");
				for (int i = 0; i < edgeNodeList.getLength(); i++) {
					Element eleEdge = (Element) edgeNodeList.item(i);
					Location loc1 = locMap.get(eleEdge
							.getAttribute("location1"));
					Location loc2 = locMap.get(eleEdge
							.getAttribute("location2"));
					Directions direction = setDirections(eleEdge
							.getAttribute("direction"));
					String distance = eleEdge.getAttribute("distance");
					String difficulty = eleEdge.getAttribute("difficulty");

					Edges edge = new Edges(loc1, loc2, direction,
							Integer.valueOf(distance),
							Integer.valueOf(difficulty));
					edgeList.add(edge);
					// System.out.println(i + " from " + loc1.getName() + " go "
					// + direction + " to " + loc2.getName() + " with " +
					// distance + "km" + " and " + difficulty +
					// " difficulty\n");
				}

				// get all charactor elements
				NodeList charasNodeList = doc.getElementsByTagName("charactor");
				for (int j = 0; j < charasNodeList.getLength(); j++) {
					Element eleCharas = (Element) charasNodeList.item(j);
					// set characters information
					setCharaInfo(eleCharas);
				}// end of for
			}// end of if
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		createMap();
	}// end of parse()

	// set information of loctions, called in the loop
	private void setLocationInfo(Element eleLoc) {
		Location location;
		Item item;
		Exit exit;
		// get information of this location
		String locName = eleLoc.getAttribute("name").toString();
		String goalDis = eleLoc.getAttribute("goalDistance").toString();
		String danger = eleLoc.getAttribute("danger").toString();
		String description = eleLoc.getElementsByTagName("description").item(0)
				.getTextContent();
		// System.out.println("----------------" + locName +
		// "----------------");
		// System.out.println("goalDis:" + goalDis + " danger:" + danger +
		// " description:" + description);
		// create new Location instance
		location = new Location(locName, Integer.valueOf(goalDis),
				Integer.valueOf(danger), description);
		locMap.put(locName, location);

		// get all objects elements
		NodeList objsNodeList = eleLoc.getElementsByTagName("object");
		// System.out.println("Objects: ");
		for (int j = 0; j < objsNodeList.getLength(); j++) {
			Element eleObjs = (Element) objsNodeList.item(j);
			String objName = eleObjs.getFirstChild().getNodeValue();
			String type = eleObjs.getAttribute("type");
			int power = Integer.valueOf(eleObjs.getAttribute("power"));
			// create new Item instance
			item = new Item(objName, type, power);
			location.addItem(item);
			// System.out.println(objName + " type: " + type + " power:" +
			// power);
		}

		// get all exits elements
		NodeList exitsNodeList = eleLoc.getElementsByTagName("exit");
		// System.out.println("Exits:");
		for (int j = 0; j < exitsNodeList.getLength(); j++) {
			Element eleExits = (Element) exitsNodeList.item(j);
			String nextLoc = eleExits.getAttribute("nextLocation");
			Directions dire = setDirections(eleExits.getAttribute("directions"));
			exit = new Exit(nextLoc, dire);
			location.addExits(exit);
			// System.out.println("go "+ dire + " to " +
			// eleExits.getAttribute("nextLocation"));
		}
		// System.out.println();
	}// end setLocationInfo()

	// set information of characters
	private void setCharaInfo(Element eleCharas) {
		String name = eleCharas.getElementsByTagName("name").item(0)
				.getTextContent();
		String behavior = eleCharas.getElementsByTagName("behavior").item(0)
				.getTextContent();

		NodeList itemsNodelList = eleCharas.getElementsByTagName("object");
		List<Item> item = new ArrayList<Item>();
		for (int i = 0; i < itemsNodelList.getLength(); i++) {
			Element eleItem = (Element) itemsNodelList.item(i);
			String type = eleItem.getAttribute("type");
			int power = Integer.valueOf(eleItem.getAttribute("power"));
			String itemName = eleItem.getFirstChild().getNodeValue();
			item.add(new Item(itemName, type, power));
		}

		// get all initial locations of this character
		NodeList iniLocNodeList = eleCharas
				.getElementsByTagName("initial_Location");
		List<Location> iniLoc = new ArrayList<Location>();
		for (int i = 0; i < iniLocNodeList.getLength(); i++) {
			Element eleLoc = (Element) iniLocNodeList.item(i);
			iniLoc.add(locMap.get(eleLoc.getFirstChild().getNodeValue()));
		}

		String initial_lifeForce = eleCharas
				.getElementsByTagName("initial_lifeForce").item(0)
				.getTextContent();

		GameCharacter character = new Hero();
		if (name.equals("Saracen"))
			character = new Saracen();
		else if (name.equals("Neutral"))
			character = new Neutral();
		else if (name.equals("Friend"))
			character = new Friend();
		else if (name.equals("Brigands"))
			character = new Brigands();

		for (Location location : iniLoc)
			location.enter(character);
		character.setName(name);
		character.setBehavior(behavior);
		character.setItems(item);
		character.setInitLocation(iniLoc);
		character.setLifeForce(Integer.valueOf(initial_lifeForce));
		charaMap.put(name, character);
		// System.out.print(name + " at " + initial_Location + " ,behaviour: " +
		// behavior + " ,life force: " + initial_lifeForce);
		// System.out.print(" ,items: ");
		// for (Item item2 : item)
		// System.out.print(item2.getObjName() + " ");
		// System.out.println();
	}

	private Directions setDirections(String dire) {
		Directions direction = Directions.W;
		if (dire.equals("N"))
			direction = Directions.N;
		else if (dire.equals("E"))
			direction = Directions.E;
		else if (dire.equals("S"))
			direction = Directions.S;
		else if (dire.equals("NW"))
			direction = Directions.NW;
		else if (dire.equals("NE"))
			direction = Directions.NE;
		else if (dire.equals("SE"))
			direction = Directions.SE;
		else if (dire.equals("SW"))
			direction = Directions.SW;
		return direction;
	}

	// set locations'connection
	private void createMap() {
		locMap.get("Jerusalem").setIsStart(true);
		setStartLoc(locMap.get("Jerusalem"));
		locMap.get("Cyprus-Nicosia").setIsGoal(true);
		setGoalLoc(locMap.get("Cyprus-Nicosia"));

		for (int i = 0; i < edgeList.size(); i++) {
			Edges edge = edgeList.get(i);
			Location loc1 = edge.getLocation1();
			Location loc2 = edge.getLocation2();
			Directions dir = edge.getDirection();
			int dis = edge.getDistance();
			int diff = edge.getDifficulty();
			loc1.addChild(loc2, dir, dis, diff);
			loc2.addChild(loc1, edge.reverseDir(dir), dis, diff);
		}// end for

		// Collection<Location> locList = locMap.values();
		// int i = 0;
		// for (Location location : locList) {
		// ++i;
		// System.out.println(i + " " + location.getName() + ":");
		// for (Item item : location.getItem())
		// System.out.println("       " + item.getObjName() + " ,type: "
		// + item.getType() + " ,power: " + item.getPower());
		// List<Location> ll = location.getChildren();
		// for (Location location2 : ll) {
		// System.out.println("  " + location2.getName());
		// }
		// }// end for
	}

	public void setStartLoc(Location startLoc) {
		this.startLoc = startLoc;
	}

	public Location getStartLoc() {
		return startLoc;
	}

	public void setGoalLoc(Location goalLoc) {
		this.goalLoc = goalLoc;
	}

	public Location getGoalLoc() {
		return goalLoc;
	}
}