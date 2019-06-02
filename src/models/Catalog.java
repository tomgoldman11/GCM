package models;

public class Catalog {
	private City[] cities;

	public Catalog(City[] cities) {
		super();
		this.cities = cities;
	}

	public City[] getCities() {
		return cities;
	}

	public void setCities(City[] cities) {
		this.cities = cities;
	}
	
	
}
