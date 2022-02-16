package algstudent.s0;

public class MatrixOperationsTimes 
{
	
	public static void main(String[] args)
	{
		int repetitions = 1000; /*Integer.parseInt(args[0]);*/
		
		System.out.println("=========== MEASUREMENTS OF DIAGONAL1 ===========");
		measureDiagonal1(repetitions);
//		
		System.out.println("=========== MEASUREMENTS OF DIAGONAL2 ===========");
		measureDiagonal2(repetitions);	
		
	}
	
	
	
	private static void measureDiagonal1(int repetitions)
	{
		long t1, t2;
		for (int i = 10; i <= Integer.MAX_VALUE; i *= 3)
		{
			MatrixOperations matrix = new MatrixOperations(i, 200, 500);
			
			t1 = System.currentTimeMillis();	// milliseconds before start the sum operation
			
			for( int repetition = 0; repetition <= repetitions; repetition++)
			{
				matrix.sumDiagonal1();
			}
			
			t2 = System.currentTimeMillis();	// milliseconds after the sum operation
			
			System.out.printf("SIZE = %d, TIMEFORONE = %d microseconds\n", i, t2-t1);	
		}
	}
	
	
	private static void measureDiagonal2(int repetitions)
	{
		long t1, t2;
		for (int i = 10; i <= Integer.MAX_VALUE; i *= 3)
		{
			MatrixOperations matrix = new MatrixOperations(i, 200, 500);
			
			t1 = System.currentTimeMillis();	// milliseconds before start the sum operation

			for( int repetition = 0; repetition <= repetitions; repetition++)
			{
				matrix.sumDiagonal1();
			}
			
			t2 = System.currentTimeMillis();	// milliseconds after the sum operation
			
			System.out.printf("SIZE = %d, TIMEFORONE = %d microseconds\n", i, t2-t1);	
		}
	}
	
	
	
	
	
	
	
	
	
	
	

}
