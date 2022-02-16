package algstudent.s11;

public class Vector4 {
	
	static int[] v;
	
	
	public static void main(String arg [])
	{
		long t1, t2;
		int sum = 0;
		int repetitions = Integer.parseInt(arg[0]);	// times we repeat the operation
		
		for (int n = 10; n < Integer.MAX_VALUE; n *= 5)
		{
			v = new int[n];
			Vector1.fillIn(v);
			
			
		
			t1 = System.currentTimeMillis();	// milliseconds before start the sum operation
			
			for( int repetition = 2; repetition <= repetitions; repetition++)
			{
				sum =  Vector1.sum(v);
			}
			
			t2 = System.currentTimeMillis();	// milliseconds after the sum operation
			
			System.out.printf("SIZE = %d, TIMEFORONE = %d microseconds - SUM = %d - NTIMES = %d\n", n, t2-t1, sum, repetitions);	
		}
		
		
		
	} 
	
	
	

}
