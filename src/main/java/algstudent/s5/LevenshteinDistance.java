package algstudent.s5;

public class LevenshteinDistance 
{
	// complexity of the algorithm --> O(len(str1)*len(str2))
	
	// attributes
	
	private int[][] words;
	private String str1;
	private String str2;
	
	
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
	
	
	
	
}
