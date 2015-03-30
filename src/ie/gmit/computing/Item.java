package ie.gmit.computing;

//items/objects
public class Item {
	private String objName;
	private String type;
	private int power;

	public Item(String obj, String type, int power) {
		this.objName = obj;
		this.type = type;
		this.power = power;
	}

	public String getObjName() {
		return objName;
	}

	public String getType() {
		return type;
	}

	public int getPower() {
		return power;
	}
}
