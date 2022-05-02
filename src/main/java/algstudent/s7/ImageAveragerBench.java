package algstudent.s7;

import java.nio.file.Paths;


public class ImageAveragerBench {
	
	// Benchmarking settings
	private static String REAL_IMG = Paths.get("").toAbsolutePath().toString() + "/src/main/java/algstudent/s7/einstein_1_256.png";
	private static String BAD_IMG = Paths.get("").toAbsolutePath().toString() + "/src/main/java/algstudent/s7/einstein_1_256.png";
	private static String OUT_BAB = Paths.get("").toAbsolutePath().toString() + "./src/main/java/algstudent/s7/out_bab/";
	private static int N_IMGS = 15; 
	private static double PERCENTAGE_BAD = 25; // %
	private static double S_NOISE = 5.0; 
		
	public static void main(String[] args) {
		
		int n_real, n_bad;
		ImageAverager img_avger;
		
		// Generating and testing a single dataset instance
		n_bad = (int) ((PERCENTAGE_BAD/100.)*N_IMGS);
		n_real = N_IMGS - n_bad;
		img_avger = new ImageAverager(REAL_IMG, BAD_IMG, n_real, n_bad, S_NOISE);
				

		
		// Measurements
		
		
		
		System.out.print("TESTING BRANCH AND BOUND:\n");
		NodeAvg node = new NodeAvg(img_avger);
		BranchAndBoundAvg problem = new BranchAndBoundAvg(node);
		problem.branchAndBound(problem.getRootNode());
		problem.printSolutionTrace();
		img_avger = ((NodeAvg) problem.getBestNode()).getImageAverager();
		
		System.out.printf("  -ZNCC: %f\n",  img_avger.zncc());
		System.out.printf("  -Counter: %d\n",  img_avger.getCounter());
		
		System.out.printf("  -Generated nodes: %d\n", problem.getGeneratedNodes());
		System.out.printf("  -Processed nodes: %d\n", problem.getProcessedNodes());
		System.out.printf("  -Trimmed nodes: %d\n", problem.getTrimmedNodes());
		img_avger.saveResults(OUT_BAB);
		
		
		measureBnB();		
		measureBacktracking();
		
		
		
	}
	
	
	/**
	 * Method to measure the branchand bound algorithm
	 */
	private static void measureBnB()
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
			
			NodeAvg node = new NodeAvg(img_avger);
			BranchAndBoundAvg problem = new BranchAndBoundAvg(node);
			problem.branchAndBound(problem.getRootNode());
			
			
			
			// time measurement
			
			t1 = System.currentTimeMillis();	// milliseconds before start the sum operation
			
			problem.branchAndBound(problem.getRootNode());		
			
			t2 = System.currentTimeMillis();	// milliseconds after the sum operation
			
			img_avger = ((NodeAvg) problem.getBestNode()).getImageAverager();
			System.out.printf("n = %d, TIME= %d microseconds, ZNCC: %f\n", i, t2-t1, img_avger.zncc());
			System.out.printf("  -Processed nodes: %d\n", problem.getProcessedNodes());
			
		}
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
			
			n_bad = (int) ((PERCENTAGE_BAD / 100.)*i);
			n_real = i - n_bad;
			img_avger = new ImageAverager(REAL_IMG, BAD_IMG, n_real, n_bad, S_NOISE);
			

			t1 = System.currentTimeMillis();	// milliseconds before start the sum operation
			
			img_avger.splitSubsetsBacktracking();			
			
			t2 = System.currentTimeMillis();	// milliseconds after the sum operation
			
			System.out.printf("n = %d, TIME= %d microseconds, ZNCC: %f\n", i, t2-t1, img_avger.zncc());
		}
		
		
	}
	
	

}
