package ie.gmit.computing.characters;

import ie.gmit.computing.Item;
import ie.gmit.computing.Location;

import java.util.ArrayList;
import java.util.List;

public abstract class AbsGameCharacter implements GameCharacter {
	// private String behavoir = "default";
	// private String name = "default";
	// private List<Item> item = new ArrayList<Item>();
	// private int lifeForce = 0;
	// private String initialLoc = null;

	@Override
	public void run() {
	}

	@Override
	public void setLifeForce(int life) {
	}

	@Override
	public void setName(String name) {
	}

	@Override
	public void setInitLocation(List<Location> loc) {
	}

	@Override
	public void setBehavior(String behavior) {
	}

	@Override
	public void setItems(List<Item> item) {
	}

	@Override
	public int getLifeForce() {
		return 0;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public List<Location> getInitLocation() {
		return null;
	}

	@Override
	public String getBehavior() {
		return null;
	}

	@Override
	public List<Item> getItems() {
		return null;
	}
}
