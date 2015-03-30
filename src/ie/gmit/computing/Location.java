package ie.gmit.computing;

import ie.gmit.computing.characters.GameCharacter;

import java.util.*;

import org.jfree.chart.plot.dial.ArcDialFrame;

public class Location implements Lookable {
	private String name;
	private int danger;
	private int goalDistance;
	private String description;
	private List<Item> item = new ArrayList<Item>();
	private List<Exit> exits = new ArrayList<Exit>();

	private List<GameCharacter> gcObserver = new ArrayList<GameCharacter>();

	private boolean isStart = false;
	private boolean isGoal = false;

	// 用next location 得到 direction
	private Map<Location, Directions> children = new HashMap<Location, Directions>();
	// 再用direction 得到 distance
	private Map<Location, Integer> disMap = new HashMap<Location, Integer>();
	// 再用direction 得到 difficulty
	private Map<Location, Integer> diffMap = new HashMap<Location, Integer>();

	public Location(String name, int goalDis, int danger, String desc) {
		this.name = name;
		this.goalDistance = goalDis;
		this.danger = danger;
		this.description = desc;
	}

	public int getGoalDistance() {
		return goalDistance;
	}

	public void setIsStart(boolean b) {
		this.isStart = b;
	}

	public void setIsGoal(boolean b) {
		this.isGoal = b;
	}

	public boolean isGoal() {
		return isGoal;
	}

	public boolean isStart() {
		return isStart;
	}

	public List<Location> getChildren() {
		List<Location> l = new ArrayList<Location>();
		for (Location location : children.keySet())
			l.add(location);
		return l;
	}

	public void addChild(Location child, Directions dire, int distance,
			int difficulty) {
		children.put(child, dire);
		disMap.put(child, distance);
		diffMap.put(child, difficulty);
	}

	public Directions getDirectionToChild(Location child) {
		return children.get(child);
	}

	public int getDistenceToChild(Location child) {
		return disMap.get(child);
	}

	public int getDifficultyToChild(Location child) {
		return diffMap.get(child);
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getDanger() {
		return danger;
	}

	public List<Item> getItem() {
		return item;
	}

	// add item
	public void addItem(Item items) {
		item.add(items);
	}

	// add exits
	public void addExits(Exit e) {
		exits.add(e);
	}

	public List<Exit> getExits() {
		return exits;
	}

	// character come in
	public void enter(GameCharacter gc) {
		gcObserver.add(gc);
	}

	// character leave
	public void exit(GameCharacter gc) {
		gcObserver.remove(gc);
	}

	@Override
	public void look() {
		StringBuffer sb = new StringBuffer();
		sb.append("-----------" + this.name + "-----------\n");
		sb.append(this.description);

		sb.append("\n-----Characters Here-----\n");
		for (GameCharacter gc : gcObserver)
			sb.append(gc.getName() + "\n");
		
		sb.append("-----Items Here-----\n");
		for (Item i : item)
			sb.append(i.getObjName() + "\n");
		
		sb.append("-------Exits-------\n");
		for (Exit e : exits) {
			sb.append("GO " + e.getDirection());
			sb.append(" TO " + e.getLocation() + "\n");
		}
		System.out.println(sb);
	}
}
