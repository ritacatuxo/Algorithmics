package algstudent.s4;

import java.util.List;

public class Country {
	
	// attributes
	
	private List<Country> neighboringCountries;
	private String color;
	private String name;
	
	// constructor and methods
	
	public Country(String name, List<Country> neighboringCountries) {
		super();
		this.name = name;
		this.neighboringCountries = neighboringCountries;
	}
	
	public Country(String name)
	{
		this.name = name;
	}


	public List<Country> getNeighboringCountries() {
		return neighboringCountries;
	}


	public void setNeighboringCountries(List<Country> neighboringCountries) {
		this.neighboringCountries = neighboringCountries;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}
	
	public String getName()
	{
		return name;
	}
	
	

}
