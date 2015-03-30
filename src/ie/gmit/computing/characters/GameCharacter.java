package ie.gmit.computing.characters;

import ie.gmit.computing.Item;
import ie.gmit.computing.Location;

import java.util.List;

public interface GameCharacter extends Runnable {

	// setters
	public void setLifeForce(int life);

	public void setName(String name);

	public void setInitLocation(List<Location> loc);

	public void setBehavior(String behavior);

	public void setItems(List<Item> item); //用map，get("weapon or medicine, food")

	// getters
	public int getLifeForce();

	public String getName();

	public List<Location> getInitLocation();

	public String getBehavior();

	public List<Item> getItems();
}