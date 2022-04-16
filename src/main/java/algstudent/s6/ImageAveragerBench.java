package algstudent.s6;

import java.nio.file.Paths;


public class ImageAveragerBench {
	
	// Benchmarking settings
	private static String REAL_IMG = Paths.get("").toAbsolutePath().toString() + "./src/main/java/algstudent/s6/einstein_1_256.png";
	private static String BAD_IMG = Paths.get("").toAbsolutePath().toString() + "./src/main/java/algstudent/s6/einstein_1_256.png";
	private static String OUT_DIR_G = Paths.get("").toAbsolutePath().toString() + "./src/main/java/algstudent/s6/out_g/";
	private static String OUT_DIR_B = Paths.get("").toAbsolutePath().toString() + "./src/main/java/algstudent/s6/out_bt/";
	private static int N_IMGS = 6; 
	private static double PERCENTAGE_BAD = 25; // %
	private static double S_NOISE = 5.0; 
	private static int MAX_UNBALANCING = 2;
		
	public static void main(String[] args) {
		
		int n_real, n_bad;
		ImageAverager img_avger;
		
		// Generating and testing a single dataset instance
		n_bad = (int) ((PERCENTAGE_BAD/100.)*N_IMGS);
		n_real = N_IMGS - n_bad;
		img_avger = new ImageAverager(REAL_IMG, BAD_IMG, n_real, n_bad, S_NOISE);
				
		System.out.print("TESTING GREEDY:\n");
		img_avger.splitSubsetsGreedy(N_IMGS);
		System.out.printf("  -ZNCC: %f\n",  img_avger.zncc());
		System.out.printf("  -Counter: %d\n",  img_avger.getCounter());
		img_avger.saveResults(OUT_DIR_G);
			
		System.out.print("TESTING BACKTRACKING BALANCING:\n");
		img_avger.splitSubsetsBacktracking(2);
		System.out.printf("  -ZNCC: %f\n",  img_avger.zncc());
		System.out.printf("  -Counter: %d\n",  img_avger.getCounter());
		img_avger.saveResults(OUT_DIR_B);

		
		// Measurements
		
		measureBacktracking();
		measureBacktrackingBalancing();
		measureGreedy();
		
	}
	
	
	/**
	 * Method to measure the backtracking algorithm
	 */
	private static void measureBacktracking()
	{
		
		int n_real, n_bad;
		ImageAverager img_avger;
		
		
		for (int i = 2; i <= Integer.MAX_VALUE; i++)
		{
			long t1, t2;
			
			// Generating a single dataset instance
			n_bad = (int) ((PERCENTAGE_BAD/100.)*i);
			n_real = i - n_bad;
			img_avger = new ImageAverager(REAL_IMG, BAD_IMG, n_real, n_bad, S_NOISE);
			
			
			// time measurement
			
			t1 = System.currentTimeMillis();	// milliseconds before start the sum operation
			
			img_avger.splitSubsetsBacktracking();			
			
			t2 = System.currentTimeMillis();	// milliseconds after the sum operation
			
			System.out.printf("n = %d, TIME= %d microseconds, ZNCC: %f\n", i, t2-t1, img_avger.zncc());	
		}
	}
	
	
	
	/**
	 * Method to measure the backtracking with balancing algorithm
	 */
	private static void measureBacktrackingBalancing()
	{
		
		int n_real, n_bad;
		ImageAverager img_avger;
		
		
		for (int i = 2; i <= Integer.MAX_VALUE; i++)
		{
			long t1, t2;
			
			// Generating a single dataset instance
			n_bad = (int) ((PERCENTAGE_BAD/100.)*i);
			n_real = i - n_bad;
			img_avger = new ImageAverager(REAL_IMG, BAD_IMG, n_real, n_bad, S_NOISE);
			
			
			// time measurement
			
			t1 = System.currentTimeMillis();	// milliseconds before start the sum operation
			
			img_avger.splitSubsetsBacktracking(MAX_UNBALANCING);			
			
			t2 = System.currentTimeMillis();	// milliseconds after the sum operation
			
			System.out.printf("n = %d, TIME= %d microseconds, ZNCC: %f\n", i, t2-t1, img_avger.zncc());	
		}
	}
	
	
	
	/**
	 * Method to measure the greedy algorithm
	 */
	private static void measureGreedy()
	{
		
		int n_real, n_bad;
		ImageAverager img_avger;
		
		
		for (int i = 15; i <= Integer.MAX_VALUE; i++)
		{
			long t1, t2;
			
			// Generating a single dataset instance
			n_bad = (int) ((PERCENTAGE_BAD/100.)*i);
			n_real = i - n_bad;
			img_avger = new ImageAverager(REAL_IMG, BAD_IMG, n_real, n_bad, S_NOISE);
			
			
			// time measurement
			
			t1 = System.currentTimeMillis();	// milliseconds before start the sum operation
			
			img_avger.splitSubsetsGreedy(i);			
			
			t2 = System.currentTimeMillis();	// milliseconds after the sum operation
			
			System.out.printf("n = %d, TIME= %d microseconds, ZNCC: %f\n", i, t2-t1, img_avger.zncc());	
		}
	}
	
	
	

}