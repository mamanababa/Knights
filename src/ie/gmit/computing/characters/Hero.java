package ie.gmit.computing.characters;

import java.util.ArrayList;
import java.util.List;

import ie.gmit.computing.Item;
import ie.gmit.computing.Location;

public class Hero extends AbsGameCharacter {
	private String name = "default";
	private String behavoir = "default";
	private List<Item> item = new ArrayList<Item>();
	private int lifeForce = 0;
	private List<Location> initialLoc = new ArrayList<Location>();

	// roam through the semantic work in a separate thread of execution
	@Override
	public void run() {
	}

	public void move() {
	}

	public void addItem(Item i) {
		item.add(i);
		System.out.println("---New item added: " + i.getObjName());
	}

	public void useItem(String eat) {
		if (lifeForce >= 100)
			System.out.println("---Your life force already up to maximum!");
		else {
			for (Item item2 : item) {
				if (item2.getObjName().equals(eat.toLowerCase())) {
					updateLife(item2.getPower());
					item.remove(item2);
					System.out.println("---Your life force increase to "
							+ lifeForce);
					break;
				}
			}
		}// end else
	}

	public void dropItem(Item i) {
		System.out.println("---Drop " + i.getObjName());
		item.remove(i);
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	public void updateLife(int life) {
		this.lifeForce += life;
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

	public void check() {
		StringBuffer sb = new StringBuffer();
		sb.append("-----Your Items-----\n");
		for (Item i : item)
			sb.append(i.getObjName() + "\n");
		sb.append("-----Your Life Force-----\n");
		sb.append(lifeForce);
		System.out.println(sb);
	}
}
