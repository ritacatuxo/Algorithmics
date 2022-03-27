package algstudent.s5;

import java.util.Random;

public class LevenshteinDistanceTimes 
{
	
	public static void main(String[] args)
	{
		int repetitions = 50; 
		measureLevenshteinDistance(repetitions);
	}
	
	
	
	private static void measureLevenshteinDistance(int repetitions)
	{
		LevenshteinDistance levenshtein;
		
		for (int i = 100; i <= Integer.MAX_VALUE; i *= 2)
		{
			long t1, t2;
			
			// create strings with same length giving characters values to the string in a random way
			
			String str1 = createRandomString(i);
			String str2 = createRandomString(i);
			
			levenshtein = new LevenshteinDistance(str1, str2);
			
			
			// time measurement
			
			t1 = System.currentTimeMillis();	// milliseconds before start the sum operation
			
			for( int repetition = 0; repetition <= repetitions; repetition++)
			{
				levenshtein.levenshteinDistance();
			}
			
			t2 = System.currentTimeMillis();	// milliseconds after the sum operation
			
			System.out.printf("SIZE = %d, TIME= %d microseconds\n", i, t2-t1);	
		}
	}
	
	
	
	private static String createRandomString(int length)
	{
		Random random = new Random();
		String ret = "";
		String chars = "ABCDEFGHIJKLMNOPQRST";
		
		for (int i = 0; i < length; i++)
		{
			int randomInt = random.nextInt(chars.length());
	        char randomChar = chars.charAt(randomInt);
			ret += randomChar;
		}
		
		return ret;
	}
	
	

}
