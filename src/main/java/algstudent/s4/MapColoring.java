package algstudent.s4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class MapColoring 
{
	
	
	public static void main(String[] args)
	{

		MapColoring map = new MapColoring("colors.txt", "borders.txt");
		List<String> colorsUsed = map.colorate();
		boolean isEverythingOk = map.test();
		
		System.out.print("Colors used: ");
		System.out.println();
		for (String color: colorsUsed)
		{
			System.out.println(color);
		}
		System.out.println();
		System.out.println();
		System.out.print("Amount of colors used: " + colorsUsed.size());
		System.out.println();System.out.println();
		System.out.println("Is the algorithm working correctly? " + isEverythingOk);
	}
	
	
	
	
	// attributes
	
	private List<String> colors = new ArrayList<String>();
	private List<Country> countries = new ArrayList<Country>();
	
	
	// constructor
	
	public MapColoring(String colorsFileName, String countriesFileName)
	{
		colors = readColors(colorsFileName);
		readCountries(countriesFileName);
	}
	

	// methods 
	
	public List<String> colorate()
	{
		List<String> colorsUsed = new ArrayList<String>();
		
		for (Country country : countries) 
		{
			List<String> adjacentColors = new ArrayList<String>();
			
			//get the colors of the adjacent countries
			
			for (Country neighboringCountry: country.getNeighboringCountries())
			{
				
				// get the country from the countries main list
				Country neighboringMainCountry = null;
				for (Country mainCountry : countries) 
				{
					if (neighboringCountry.getName().strip().equals(mainCountry.getName()))
					{
						neighboringMainCountry = mainCountry;
						break;
					}
						
				}				
				
				// get its color and, if it is not already in the adjacent colors list, add it
				if (!adjacentColors.contains(neighboringMainCountry.getColor()) && neighboringMainCountry != null)
						adjacentColors.add(neighboringMainCountry.getColor());
			}
			
			//find the best color for the country and set it
			for (String color : colors)
			{
				if(!adjacentColors.contains(color))
				{
					country.setColor(color);
					
					// add the (new) color to the list if we haven't used it before 
					if (!colorsUsed.contains(color))
						colorsUsed.add(color);
					
					break;
				}
			}	
		}
		
		
		return colorsUsed;
	}
	
	
	
	
	// read files methods

	private List<String> readColors(String colorsFileName) {
		
		
		List<String> colors = new ArrayList<String>();
		
		try {
		      File myObj = new File(colorsFileName);
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        colors.add(data);
		      }
		      myReader.close();
		}
		catch (FileNotFoundException e) 
		{
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
		
		return colors;
	}
	
	
	private void readCountries(String countriesFileName) 
	{		
		List<String> lines = new ArrayList<String>();
		
		try 
		{
		      File myObj = new File(countriesFileName);
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine())
		      {
		        String data = myReader.nextLine();
		        lines.add(data);
		      }	
		      myReader.close();
		}
		catch (FileNotFoundException e) 
		{
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
		
		
		// convert from a string line into a country with its adjacent countries
		
		for(String line : lines)
		{
	        String[] separated = line.split(":");
	        
	        // the adjacent countries
	        String[] neigboringCountries = separated[1].split(",");
	        
	        List<Country> adjacentCountries = new ArrayList<Country>();
	        for(String string : neigboringCountries)
	        {
	        	
	        	if (string.strip().equals("NO"))
	        		continue;
	        	adjacentCountries.add(new Country(string));
	        }	        
	        
	        // add the final country (with name and its own adjacent countries)
	        this.countries.add(new Country(separated[0], adjacentCountries));       
		}
	}
	
	
	
	// method for test that the algorithm works
	
	private boolean test()
	{
		boolean isOk = true;
		
		for (Country country : countries)
		{
			// uncomment this line to see every country with its associated color
			// System.out.println(country.getName() + " Color: " + country.getColor());
			
			for (Country adjacentCountry : country.getNeighboringCountries())
			{
				if (country.getColor().equals(adjacentCountry.getColor()))
						isOk = false;
			}
		}
		
		return isOk;
	}
	
	

}
