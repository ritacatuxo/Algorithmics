package algstudent.test;

public class LevenshteinDistanceTest {
	
	public static void main(String[] args)
	{
		// from barcazas to abracadabra
		
		LevenshteinDistance levenshtein12 = new LevenshteinDistance("ABRACADABRA", "BARCAZAS");
		levenshtein12.levenshteinDistance();
		levenshtein12.print("ABRACADABRA", "BARCAZAS");
		levenshtein12.exam();
		levenshtein12.printChanges();
		
		
		LevenshteinDistance levenshtein2 = new LevenshteinDistance("BARCAZAS", "ABRACADABRA");
		levenshtein2.levenshteinDistance();
		levenshtein2.print("BARCAZAS", "ABRACADABRA");
		levenshtein2.exam();
		levenshtein2.printChanges();
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	

}
