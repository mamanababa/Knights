package ie.gmit.computing;

public class Edges {
	private Location location1 = null;
	private Location location2 = null;
	private Directions direction = null;
	private int distance = 0;
	private int difficulty = 0;

	public Edges(Location loc1, Location loc2, Directions d, int dis, int diff) {
		this.location1 = loc1;
		this.location2 = loc2;
		this.direction = d;
		this.distance = dis;
		this.difficulty = diff;
	}

	public Location getLocation1() {
		return location1;
	}

	public Location getLocation2() {
		return location2;
	}

	public Directions getDirection() {
		return direction;
	}

	public int getDistance() {
		return distance;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public Directions reverseDir(Directions dir) {
		if (dir.equals(Directions.E))
			dir = Directions.W;
		else if (dir.equals(Directions.W))
			dir = Directions.E;
		else if (dir.equals(Directions.S))
			dir = Directions.N;
		else if (dir.equals(Directions.N))
			dir = Directions.S;
		else if (dir.equals(Directions.SW))
			dir = Directions.NE;
		else if (dir.equals(Directions.NE))
			dir = Directions.SW;
		else if (dir.equals(Directions.NW))
			dir = Directions.SE;
		else if (dir.equals(Directions.SE))
			dir = Directions.NW;
		return dir;
	}
}
