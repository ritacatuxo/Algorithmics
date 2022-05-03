package algstudent.test;

import java.util.ArrayList;
import java.util.HashMap;

public class LevenshteinDistance {
	
	// complexity of the algorithm --> O(len(str1)*len(str2))
	
		// attributes
		
		private int[][] words;
		private String str1;
		private String str2;
		
		// map to store the changes (key --> the action, value --> the word changed)
		//private HashMap<String, String> changes = new HashMap<String, String>();
		private ArrayList<String> changes = new ArrayList<String>(); 
		 
		// constructor
		
		public LevenshteinDistance(String str1, String str2)
		{
			this.str1 = str1;
			this.str2 = str2;
		}
		
		
		
		/**
		 * Minimum number of operations that must be done so that both strings are equal
		 * 
		 * @param str1
		 * @param str2
		 */
		public int levenshteinDistance()
		{
			words = new int[str1.length()+1][str2.length()+1];
			
			for (int i = 0; i < words.length; i++)
			{
				for (int j = 0; j < words[0].length; j++)
				{
					
					// the first row and column
					
					if (j == 0)
						words[i][j] = i;
					
					else if (i == 0)
						words[i][j] = j;
					
					
					else
					{
						if (str1.charAt(i-1) == str2.charAt(j-1))
							words[i][j] = words[i-1][j-1];
						
						else // str1 != str2
						{
							// d(i,j) = 1 + min(d[i-1, j-1], d[i, j-1], d[i-1, j])
							int firstOption = words[i-1][j-1];
							int secondOption = words[i][j-1];
							int thirdOption = words[i-1][j];
							
							int firstMin = Math.min(firstOption, secondOption);
							words[i][j] = 1 + Math.min(firstMin, thirdOption);
						}
					}
				}
			}
			
			return words[str1.length()][str2.length()];
		}
		
		
		
		public void print(String str1, String str2)
		{
			for (int i = 0; i < str1.length()+1; i++)
			{
				for (int j = 0; j < str2.length()+1; j++)
				{
					System.out.print(words[i][j] + " ");
				}
				System.out.println();
			}
			
		}
		
		
		
		// extra method to print the path
		public void exam()
		{
			int index = str1.length()-1;
			
			int row = words.length-1;
			int column = words[0].length-1;
			
			int leftElement = -1;
			int upperElement = -1;
			int diagonalElement = -1;
			
			String action = "";
			String minimum = "";
			
			
			
			
			while(row != 0 && column != 0)
			{
				// ver que elemento del ciadrante de arriba a la izquierda es mas pequeño
				
				// in case we have the element in the left border
				if(column == 0) 
				{
					//then we only have the option of the upper element
					upperElement = words[row-1][column];
					minimum = "upper";
					
				}
				// in case we have the element in the upper border
				else if (row == 0)
				{
					//then we only have the option of the left element
					leftElement = words[row][column-1];
					minimum = "left";
					
				}
				else
				{
					//elemento de la izquierda
					leftElement = words[row][column-1];
					
					//elemento de arriba
					upperElement = words[row-1][column];
					
					//elemento de la diagonal
					diagonalElement = words[row-1][column-1];
					
					
					
					// get the minimum 
					
					if (leftElement < upperElement)
						minimum = "left";
					
					else
						minimum = "upper";
					
					
					if (minimum == "left")
					{
						if (leftElement > diagonalElement)
							minimum = "diagonal";
					}
					else
					{
						if (upperElement == diagonalElement)
							minimum = "diagonal";
						else if (upperElement > diagonalElement)
							minimum = "diagonal";
					}
					
				}
				
				String value = "";
				
				
				if (index == 0)
					changes.add(" = " + str1);
				else
				{
					switch(minimum)
					{
						case "left":
								// add
								action = "add";
								
								//key = getKey(index, action);
								value = getValue(index);
								
	
								System.out.println("Insert " + str1.charAt(row) + " = " + value);
								changes.add("Insert " + str1.charAt(row) + " = " + value);
								
								index--;
								
								// chnage to tthe left element
								column  = column - 1;
								
								break;
						case "upper":
								action = "delete";
								
								value = getValue(index);
								System.out.println("Delete " + str1.charAt(row) + " = " + value);
								changes.add("Delete " + str1.charAt(row) + " = " + value);
								index--;
								
								// chnage tot he upper element
								row = row - 1;
								
								break;
						case "diagonal":
								if (diagonalElement == words[row][column])
								{
									action = "keep";
									value = getValue(index);
									System.out.println("Keep " + str1.charAt(row) + " = " + value);
									changes.add("Keep " + str1.charAt(row) + " = " + value);
									index--;
								}
									
								else
								{
									action = "change";
									value = getValue(index);
									System.out.println("change " + str2.charAt(column-1) + " to " + str1.charAt(row-1) + " = " + value);
									changes.add("change " + str2.charAt(column-1) + " to " + str1.charAt(row-1) + " = " + value);
									index--;
								}
								
								// change to the diagonal element
								column  = column - 1;
								row = row - 1;
								break;
					}
					
				
				}
				
			}
			
			
			changes.add(" = " + str1);
			
		}



	private String getValue(int index) {
	
		if (index == 0)
		{
			return str1;
		}
		
		String ret = "";
		
		for (int i = 0; i < index; i++)
		{
			ret += str1.charAt(i);
		}
		
		return ret;
	}



	private String getKey(int index, String action) {
		
		String ret = "";
		
		if (index == 0)
		{
			ret += "= " + str1;
		}
		else
		{
		
			switch(action)
			{
				case "add":
					ret += "Insert " + str2.charAt(index) + " = ";
					break;
					
				case "delete":
					ret += "Delete " + str2.charAt(index) + " = ";
					break;
					
				case "keep":
					ret += "Keep " + str2.charAt(index) + " = ";
					break;
					
				case "change":
	//				ret += "Change " + str1.charAt(index) + " to " + str2.charAt(index) + " = ";
	//				ret += "Change to " + str2.charAt(index) + " = ";
					ret += "Change to = ";
					
					break;
			}
		}
		
		return ret;
	}
	
	public void printChanges()
	{
		System.out.println("----------------");;
		for (int i = changes.size()-1; i > 0; i--)
		{
			System.out.println(changes.get(i));
		}
		
		
	}
	
	
	

	
}
