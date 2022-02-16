package algstudent.s11;

/**
 * Increases the size of the vector, obtaining times for each case. 
 * In this way, you will be able to follow more conveniently the evolution of the execution time.
 * @author Rita
 */
public class Vector3 {
	
	static int[] v;
	
	
	public static void main(String arg [])
	{
		// we increase the size exponentially in each iteration (because of the *5)
		
		long t1, t2;
		for (int n = 10; n < Integer.MAX_VALUE; n *= 5)
		{
			v = new int[n];
			Vector1.fillIn(v);
			
			t1 = System.currentTimeMillis();	// milliseconds before start the sum operation
			int sum =  Vector1.sum(v);
			t2 = System.currentTimeMillis();	// milliseconds after the sum operation
			
			System.out.printf("SIZE = %d - TIME = %d milliseconds - SUM = %d\n", n, t2-t1, sum );	
		}
		
		
		
	} 
	
	
	

}
