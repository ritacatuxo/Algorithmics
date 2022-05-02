package algstudent.s7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ImageAverager
{
	
	private Image real_img, bad_img; //to store the main good and main bad image
	private Image avg_img, half1_img, half2_img; //to store the final tests to see if we improve the previous results
	private Image[] dataset; //dataset to store all the images (good and bad ones)
	private int[] sol; //to store the partial results (where I am putting the pictures? 0->not assigned, 1->first half, 2->second half
	// final solution where we store the results. The sol is the array with the best solution
	private int[] bestSol; //to store the best solution
	public int width, height; //to store the width and height of the image
	//n� pixels
	
	//backtracking variables
	private int counter; //to store the number of times we assign an image to half1, half2 or no group
	private double max_zncc; //to store the best ZNCC
	
	
	public ImageAverager(ImageAverager imgAverager)
	{
		this.sol = imgAverager.getSol().clone();
		this.bestSol = imgAverager.getBestSol().clone();
		this.dataset = imgAverager.getDataset();
		this.width = imgAverager.getWidth();
		this.height = imgAverager.getHeight();
		
	}
	
	
	
	/** Constructor
	* @real_path  path to the real image (pattern to find) on disk
	* @bad_path  path to the bad image on disk
	* @n_real  number of real images in the dataset (>= 1)
	* @n_bad  number of bad images in the dataset 
	* @s_noise  standard deviation for noise 
	*/
	public ImageAverager(String real_path, String bad_path, int n_real, int n_bad, double s_noise) {
		assert (n_real >= 1) && (n_bad < n_real);//assert at least one reference image
		
		//load reference and bad images
		this.real_img = new Image(real_path);
		this.bad_img = new Image(bad_path);
		this.width = this.real_img.getWidth();
		this.height = this.real_img.getHeight();
		
		//create the dataset as an array of unordered randomly chosen real and bad images
		int total_imgs = n_real + n_bad; //the total number of images are the good + the bad ones
		this.dataset = new Image[total_imgs]; //the data set for the total of images
		this.sol = new int[total_imgs]; //we will use this variable during the process 0->not assigned, 1->first half, 2->second half 
		this.bestSol = new int[total_imgs]; //we will use this variable to store the best results
		int[] rand_index = this.randomIndexes(total_imgs); //random array of positions to mix images
		Image hold_img; //temp images
		int region = 0; // 0-up, 1-down, 2-left, 3-right
		for (int i=0; i<n_real; i++) { //to save good images
			hold_img = new Image(this.width, this.height); //generate image
			hold_img.addSignal(this.real_img); //save the image
			hold_img.suppressRegion(region); //a half part of the image is deleted
			hold_img.addNoise(s_noise); //add some noise 
			this.dataset[rand_index[i]] = hold_img; //save image
			if (region == 3) region = 0;
			else region++;
		}
		region = 0;
		for (int i=n_real; i<n_real+n_bad; i++) { //to save bad images
			hold_img = new Image(this.width, this.height); //generate image
			hold_img.addSignal(this.bad_img); //save the image
			hold_img.invertSignal(); //corrupt the image
			hold_img.suppressRegion(region); //the fourth part of the image is deleted
			hold_img.addNoise(s_noise); //add some noise  
			this.dataset[rand_index[i]] = hold_img; //save image
			if (region == 3) region = 0;
			else region++;
		}
	}
	
	/**
	 * To generate a random array of positions
	 * @param n Length of the array
	 * @return 
	 */
	public int[] randomIndexes(int n) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++)
			list.add(i);
		Collections.shuffle(list);
		int[] array = new int[n];
		for (int i = 0; i < n; i++)
			array[i] = list.get(i);
		return array;
	}
	
	/**
	 * Store resulting images for testing
	 * @out_dir directory save the output images
	 */
	public void saveResults(String out_dir) {
		this.avg_img.save(out_dir + "/img_avg.png");
		this.half1_img.save(out_dir + "/img_half1_avg.png");
		this.half2_img.save(out_dir + "/img_half2_avg.png");
		for(int i=0; i<this.dataset.length; i++) {
			this.dataset[i].save(out_dir + "/img_" + i + "_klass_" + this.bestSol[i] + ".png");
		}
	}
	
	/**
	 * @return the number of steps carried out by the algorithm to solve the problem
	 */
	public int getCounter() {
		return counter;
	}
	
	/** Computes the ZNCC between both image dataset halves
	 * @return the computed ZNCC
	 */
	public double zncc() {
		return this.half1_img.zncc(this.half2_img);
	}
	
	/**
	 * Greedy algorithm: random instances for each half, the best one is the final solution    
	 * @n_tries number of random tries     
	 */
	public void splitSubsetsGreedy(int n_tries) {	

		// worst min value we can have is -1. Then, at the initialization max_anc must set to -1 (this.max_zncc = -1)
		this.max_zncc = -1;
		for (int i = 0; i < n_tries; i++)
		{
			// 1. assign all the images to 0, 1 or 2 (bad images, half1 or half2) in a randomly way
			
			Random random = new Random();
			for (int j = 0; j < sol.length; j++)
			{
				sol[j] = random.nextInt(3);
			}
			
			
			// 2. get the avg adding all the images of the corresponding array
			
			for (int j = 0; j < sol.length; j++)
			{
				
				if (sol[j] == 1)
				{
					if (half1_img == null)
						half1_img = new Image(dataset[0].getWidth(), dataset[0].getHeight());
					
					this.half1_img.addSignal(dataset[j]);
					counter++;
				}
				
				else if (sol[j] == 2)
				{
					if (half2_img == null)
						half2_img = new Image(dataset[0].getWidth(), dataset[0].getHeight());
						
					this.half2_img.addSignal(dataset[j]);
					counter++;
				}
			}
			
			// best sol should keep the best one (final result)
		
			double zncc = zncc();
			if (zncc > this.max_zncc)
				this.max_zncc = zncc;
			
		}
		
		// add the final halves
		
		half1_img.addSignal(half2_img);
		avg_img = half1_img;
		
		
	}
	
	/**
	 * Backtracking algorithm 
	 * @max_unbalancing: (pruning condition) determines the maximum difference between 
	 * the number of images on each half set               
	 */
	public void splitSubsetsBacktracking(int max_unbalancing)
	{
		
		this.max_zncc = -1;
		badtrackingWithBadtracking(0, max_unbalancing);
		
		
	}
		
	

	/**
	 * Backtracking algorithm without balancing. Using a larger than the number of images in the dataset ensures no prunning          
	 */
	public void splitSubsetsBacktracking() 
	{

		this.max_zncc = -1;
		badtracking(0);
		
	}

	private void badtracking(int level) 
	{
		
		
		if ( level == dataset.length )
		{
			
			// calculate the zncc and update if it is better
			
			Image half1_imgCopy = new Image(dataset[0].getWidth(), dataset[0].getHeight());
			Image half2_imgCopy = new Image(dataset[0].getWidth(), dataset[0].getHeight());
			
			// take pictures from the sol array and calculate the half
			for (int j = 0; j < sol.length; j++)
			{
				if (sol[j] == 1)
				{
					half1_imgCopy.addSignal(dataset[j]);
					counter++;
				}
				
				else if (sol[j] == 2)
				{
					half2_imgCopy.addSignal(dataset[j]);
					counter++;
				}
			}
			
			
			double zncc = half1_imgCopy.zncc(half2_imgCopy);
			if (zncc > this.max_zncc)
			{
				this.max_zncc = zncc;
				
				half1_img = half1_imgCopy;
				half2_img = half2_imgCopy;
				
				//add the final halves
				
				avg_img = new Image(dataset[0].getWidth(), dataset[0].getHeight());
				avg_img.addSignal(half1_img);
				avg_img.addSignal(half2_img);
				
			}

			
		}
		else
		{
			// group 1 ------------------
			
			// insert picture in group 1 and recursive call
			sol[level] = 1;
			badtracking(level+1);
			
			
			// 	group 2 ------------------
			
			
			// insert picture in group 1 and recursive call
			sol[level] = 2;
			badtracking(level+1);
			
			
			// group 0 ---------------------
			
			//recursive call
			badtracking(level+1);
			
		}
		
	}
	
	
	private void badtrackingWithBadtracking(int level, int max_unbalancing) {
		

		if ( level == dataset.length )
		{
			
			// calculate the zncc and update if it is better
			
			half1_img = new Image(dataset[0].getWidth(), dataset[0].getHeight());
			half2_img = new Image(dataset[0].getWidth(), dataset[0].getHeight());
			
			// take pictures from the sol array and calculate the half
			for (int j = 0; j < sol.length; j++)
			{
				if (sol[j] == 1)
				{
					this.half1_img.addSignal(dataset[j]);
					counter++;
				}
				
				else if (sol[j] == 2)
				{
					this.half2_img.addSignal(dataset[j]);
					counter++;
				}
			}
			
			double zncc = zncc();
			if (zncc > this.max_zncc)
			{
				this.max_zncc = zncc;
				
				//add the final halves
				
				avg_img = new Image(dataset[0].getWidth(), dataset[0].getHeight());
				avg_img.addSignal(half1_img);
				avg_img.addSignal(half2_img);
				
			}

			
		}
		else
		{
			// group 1 ------------------
			
			// make sure that we can insert picture in group 1 (because of the balancing) and recursive call
			if ( insertImageInGroup1(max_unbalancing) )
				sol[level] = 1;
			
			badtracking(level+1);
			
			
			// 	group 2 ------------------
			
			
			// make sure that we can insert picture in group 2 (because of the balancing) and recursive call
			if (insertImageInGroup2(max_unbalancing))
				sol[level] = 2;
			
			badtracking(level+1);
			
			
			// group 0 ---------------------
			
			//recursive call
			badtracking(level+1);
			
		}
		
	}

	
	/**
	 * Method to make sure that we can insert a new image in the group 1 taking into 
	 * account the balancing factor
	 * @param max_unbalancing the maximum difference of images we can have between the two groups of images
	 * @return true if we can insert a new image n group 1. Otherwise, return false
	 */
	private boolean insertImageInGroup1(int max_unbalancing) 
	{
		boolean insert = false;
		int counterGroup1 = 0;
		int counterGroup2 = 0;
		
		for (int i = 0; i < sol.length; i++)
		{
			if (sol[i] == 1)
				counterGroup1++;
			else if (sol[i] == 2)
				counterGroup2++;
			
		}
		
		// not possible because group1 has more images than group2
		if (counterGroup1 > counterGroup2 && Math.abs(counterGroup1 - counterGroup2) >= max_unbalancing)
			insert = false; 
		
		// possible because group1 has less images than group2 and if we insert we reduce the difference
		else if (counterGroup1 < counterGroup2 && Math.abs(counterGroup1 - counterGroup2) >= max_unbalancing)
			insert = true;
		
		// possible because the max_umbalancing is right
		else 
			insert = true;
		
		return insert;
	}
	
	
	/**
	 * Method to make sure that we can insert a new image in the group 2 taking into 
	 * account the balancing factor
	 * @param max_unbalancing the maximum difference of images we can have between the two groups of images
	 * @return true if we can insert a new image n group 2. Otherwise, return false
	 */
	private boolean insertImageInGroup2(int max_unbalancing) 
	{
		boolean insert = false;
		int counterGroup1 = 0;
		int counterGroup2 = 0;
		
		for (int i = 0; i < sol.length; i++)
		{
			if (sol[i] == 1)
				counterGroup1++;
			else if (sol[i] == 2)
				counterGroup2++;
			
		}
		
		// possible because group2 has less images than group1 and if we insert we reduce the difference
		if (counterGroup1 > counterGroup2 && Math.abs(counterGroup1 - counterGroup2) >= max_unbalancing)
			insert = true; 
		
		// not possible because group2 has more images than group1
		else if (counterGroup1 < counterGroup2 && Math.abs(counterGroup1 - counterGroup2) >= max_unbalancing)
			insert = false;
		
		// possible because the max_umbalancing is right
		else 
			insert = true;
		
		return insert;
	}
	
	
	
	
	
	public double getMax_zncc()
	{
		return max_zncc;
	}
	
	
	public void setSol(int[] newSol)
	{
		this.sol = newSol;
	}
	
	public void setBestSol(int[] newBestSol)
	{
		this.bestSol = newBestSol;
	}
	
	public int[] getSol()
	{
		return sol;
	}
	
	public int[] getBestSol()
	{
		return bestSol;
	}
	
	public Image getHalf2_img()
	{
		return half2_img;
	}
	
	public Image getHalf1_img()
	{
		return half1_img;
	}
	
	public void setHalf2_img(Image img)
	{
		this.half2_img = img;
	}
	
	public void setHalf1_img(Image img)
	{
		this.half1_img = img;
	}
	
	public void setAvg_img(Image img)
	{
		this.avg_img = img;
	}
	
	public Image[] getDataset()
	{
		return dataset;
	}
	
	
	public int getWidth()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}
	
	
	//to string: en g1 tengo estas imag y en gr2 estas otras

}
