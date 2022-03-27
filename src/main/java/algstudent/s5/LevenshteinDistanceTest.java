package algstudent.s5;

public class LevenshteinDistanceTest {
	
	public static void main(String[] args)
	{
		
		
		
		// example 1
		
		LevenshteinDistance levenshtein1 = new LevenshteinDistance("ABRACADABRA", "BARCAZAS");
		int example1 = levenshtein1.levenshteinDistance();
		System.out.print("Result between ABRACADABRA and BARCAZAS: " + example1 + " changes");
		System.out.println();
		
		LevenshteinDistance levenshtein12 = new LevenshteinDistance("BARCAZAS", "ABRACADABRA");
		int example12 = levenshtein12.levenshteinDistance();
		System.out.print("Result between BARCAZAS and ABRACADABRA: " + example12 + " changes");
		System.out.println();
		
		
		// example 2
		
		LevenshteinDistance levenshtein2 = new LevenshteinDistance("BARGES", "ABRACADABRA");
		int example2 = levenshtein2.levenshteinDistance();
		System.out.print("Result between BARGES and ABRACADABRA: " + example2 + " changes");
		System.out.println();
		
		LevenshteinDistance levenshtein22 = new LevenshteinDistance("ABRACADABRA", "BARGES");
		int example22 = levenshtein22.levenshteinDistance();
		System.out.print("Result between ABRACADABRA and BARGES: " + example22 + " changes");
		System.out.println();
		
		// example 3
		
		LevenshteinDistance levenshtein3 = new LevenshteinDistance("HELLO", "HELLA");
		int example3 = levenshtein3.levenshteinDistance();
		System.out.print("Result between HELLO and HELLA: " + example3 + " change(s)");
		System.out.println();
		
		LevenshteinDistance levenshtein32 = new LevenshteinDistance("HELLA", "HELLO");
		int example32 = levenshtein32.levenshteinDistance();
		System.out.print("Result between HELLA and HELLO: " + example32 + " change(s)");
		System.out.println();
		
		
		// example 4
		
		LevenshteinDistance levenshtein4 = new LevenshteinDistance("B", "BA");
		int example4 = levenshtein4.levenshteinDistance();
		System.out.print("Result between HELLO and HELLA: " + example4 + " change(s)");
		System.out.println();
		
		LevenshteinDistance levenshtein42 = new LevenshteinDistance("BA", "B");
		int example42 = levenshtein42.levenshteinDistance();
		System.out.print("Result between HELLA and HELLO: " + example42 + " change(s)");
		System.out.println();
		
		
		
	}
	
	
	
	
	
	
	
	
	
	

}
