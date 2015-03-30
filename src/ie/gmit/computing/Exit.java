package ie.gmit.computing;

public class Exit {
	private String nextLocation;
	private Directions direction;

	public Exit(String nextLocation, Directions direction) {
		this.nextLocation = nextLocation;
		this.direction = direction;
	}

	public Directions getDirection() {
		return direction;
	}

	public String getLocation() {
		return nextLocation;
	}
}
