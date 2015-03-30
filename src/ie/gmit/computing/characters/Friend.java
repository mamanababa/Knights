package ie.gmit.computing.characters;

import java.util.ArrayList;
import java.util.List;

import ie.gmit.computing.Item;
import ie.gmit.computing.Location;

public class Friend extends AbsGameCharacter {
	private String name = "default";
	private String behavoir = "default";
	private List<Item> item = new ArrayList<Item>();
	private int lifeForce = 0;
	private List<Location> initialLoc = new ArrayList<Location>();

	@Override
	public void run() {
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setLifeForce(int life) {
		this.lifeForce = life;
	}

	@Override
	public int getLifeForce() {
		return this.lifeForce;
	}

	@Override
	public void setInitLocation(List<Location> loc) {
		this.initialLoc = loc;
	}

	@Override
	public void setBehavior(String behavior) {
		this.behavoir = behavior;
	}

	@Override
	public void setItems(List<Item> item) {
		this.item = item;
	}

	@Override
	public List<Location> getInitLocation() {
		return this.initialLoc;
	}

	@Override
	public String getBehavior() {
		return this.behavoir;
	}

	@Override
	public List<Item> getItems() {
		return this.item;
	}

}