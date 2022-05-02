package algstudent.s7;

import java.util.ArrayList;
import java.util.UUID;

import algstudent.s7.utils.Node;

public class NodeAvg extends Node
{
	
	
	private ImageAverager manager;
	
	public NodeAvg(ImageAverager img_averager) 
	{
		this.manager = img_averager;
	}
	
	
	public NodeAvg(ImageAverager img_averager, int depth, UUID parentId) 
	{
		this.manager = img_averager;
		this.depth = depth;
		this.parentID = parentId;
		calculateHeuristicValue();
	}

	@Override
	public void calculateHeuristicValue()
	{
		
		//in each nose we have a copy of the parent and we add the three childs in  separate way
		//in each of the levels we change the array
		//we clone (not a reference) the parent and with the thre possibilities are added separadamente
		
		//the smaller the better and the closer we are to the sol
		// i want to force my alg to reach all the solutions. 
		//USE depth and similarity
		
		// get the zncc --> the smaller the better
		
    	if(prune())
    		heuristicValue = Integer.MAX_VALUE;
    	else
    	{
    		Image half1;
    		Image half2;
    		Image[] dataset = manager.getDataset();
    		int[] sol = manager.getSol();
    		
    		if (manager.getHalf1_img() == null)
    			half1 = new Image(dataset[0].getWidth(), dataset[0].getHeight());
    		else
    			half1 = manager.getHalf1_img();
    		
    		if (manager.getHalf1_img() == null)
    			half2 = new Image(dataset[0].getWidth(), dataset[0].getHeight());
    		else
    			half2 = manager.getHalf1_img();

    		
    		
    		for (int i = 0; i < manager.getSol().length; i++)
    		{
    			if (sol[i] == 1)
    				half1.addSignal(dataset[i]);
    			else if (sol[i] == 2)
    				half2.addSignal(dataset[i]);
    		}
    		
    		manager.setHalf1_img(half1);
    		manager.setHalf2_img(half2);
    		heuristicValue =  -manager.zncc();
    		
    		Image avg_img = new Image(dataset[0].getWidth(), dataset[0].getHeight());
    		avg_img.addSignal(half1);
    		avg_img.addSignal(half2);
    		manager.setAvg_img(avg_img);
    	}
		
		
	}

	@Override
	public ArrayList<Node> expand()
	{
		//generate 3 nodes and copy the 3 nodes in the result variable
		//create 3 nodes by changing the solution
		ArrayList<Node> nodes = new ArrayList<Node>();
		
		// creating the 3 child
		//sol and bestSol shouldn't be the same. We clone them in the constructor of ImageAverager
		
		for (int i = 0; i < 3; i++)
		{
			ImageAverager averager = new ImageAverager(manager);
			
			// set the group
			int[] sol = averager.getSol();
			sol[depth] = i;
			averager.setSol(sol);
			//averager.getSol()[depth] = i;
			
			NodeAvg node = new NodeAvg(averager, depth+1, this.ID);
			
			nodes.add(node);
		}
		
		
		return nodes;
	}

	@Override
	public boolean isSolution() 
	{
		if (depth == manager.getDataset().length) //depth = image number in the dataset
			return true;
		return false;
	}
	
	
	

	public ImageAverager getImageAverager() {
		return manager;
	}

	
	/**
	 * Checks if we should prune when the conditions are not longer met
	 * @return True if we should prune. False otherwise
	 */
	private boolean prune() 
	{
		return false;		
	}
	
	
}
