package entities;

public class Category {
	//properties
	public int id;
	public String name;

	//Constructor
	public Category() {
		super(); // goi lai ham Constructor cua lop cha (Object)
	}

	public Category(int id, String name) {
		super(); // goi lai ham Constructor cua lop cha (Object)
		this.id = id;
		this.name = name;
	}

}
