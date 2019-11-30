package entities;

public class Product {
	// properties
	public int id;
	public String name;
	public int price;
	public int catID;

	// Constructor (default constructor || No Argument Constructor)
	public Product() {

	}

	public Product(int id, String name, int price, int catID) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.catID = catID;
	}

}
