package algstudent.s11;

public class Vector2 {
	
	static int[] v;
	
	
	public static void main(String arg [])
	{
		// size of the vector 
		int n = Integer.parseInt(arg[0]);
		v = new int[n];
		Vector1.fillIn(v);
		
		// we use variables with long type to measure the time in milliseconds 
		long t1, t2;
		
		t1 = System.currentTimeMillis();	// milliseconds before start the sum operation
		int sum =  Vector1.sum(v);
		t2 = System.currentTimeMillis();	// milliseconds after the sum operation
		
		System.out.printf("SIZE = %d - TIME = %d milliseconds - SUM=%d\n", n, t2-t1, sum );	
	} 
	
	
	

}
