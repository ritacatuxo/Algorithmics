package algstudent.s7.utils;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main class to solve problems using the Branch and Bound technique
 * We need to extend it for any specific problem
 * @author viceg
 */
public abstract class BranchAndBound {
	private static Logger log = LoggerFactory.getLogger(BranchAndBound.class);
	protected Heap ds; //Nodes to be explored (not used nodes)
	protected Node bestNode; //To save the final node of the best solution
	protected Node rootNode; //Initial node
	protected double pruneLimit; //To prune nodes above this value
	private int generatedNodes = 0;
	private int processedNodes = 0;
	private int trimmedNodes = 0;
	       
	/**
	 * Constructor for BrancAndBount objects
	 */
	public BranchAndBound() {
		ds = new Heap(); //We create an instance of the Heap class to save the nodes
	}
	      
	/**
	 * Manages all the process, from the beginning to the end
	 * @param rootNode Starting state of the problem
	 */
	public void branchAndBound(Node rootNode) { 
		ds.insert(rootNode); //First node to be explored
	
		pruneLimit = rootNode.initialValuePruneLimit();

		//inf loopwhile we have nodes and noseque is less than pruneLimit
		while (!ds.empty() && ds.estimateBest() < pruneLimit) {
			Node node = ds.extractBestNode();	//get first node from   priority queue and remove it
			processedNodes++;
			trimmedNodes += ds.getTrimmedNodes();
			//the first should have the smallest value (the best)
			
			
			ArrayList<Node> children = node.expand(); 
			generatedNodes += 2;
			//implement expand
			
			//for each children we check
			for (Node child : children)
				if (child.isSolution()) {
					double cost = child.getHeuristicValue(); //implement this
					if (cost < pruneLimit) {
						pruneLimit = cost;
						bestNode = child;
					} 
				}
				else
					if (child.getHeuristicValue() < pruneLimit) {
						ds.insert(child);
					}
		} //while
	}
		
	/**
	 * Gets the root node
	 * @return The root node
	 */
    public Node getRootNode() {
    	return rootNode;
    }
    
	/**
	 * Gets the best node
	 * @return The best node
	 */
    public Node getBestNode() {
    	return bestNode;
    }

    /**
     * Prints the solution from the root node to the best node
     */
    public void printSolutionTrace() {
    	if (bestNode == null) {
			log.debug("Original:");
			log.debug(rootNode.toString());
    		log.debug("THERE IS NO SOLUTION");
    	} 
    	else {
    		//Extract the path of the used nodes from bestNode to the rootNode
            ArrayList<Node> result = ds.extractUsedNodesFrom(bestNode);

            for (int i = 0; i < result.size();  i++) {
    			if (i == 0) 
    				log.debug("Original:");
    			else 
    				log.debug("Step " + i + ":");
				log.debug(result.get(result.size()-i-1).toString());
    	    }
            log.debug("\nSolution with " + bestNode.getDepth() + " step(s).");	
    	}
    }

	public int getGeneratedNodes()
	{
		return generatedNodes;
	}

	public int getProcessedNodes() 
	{
		return processedNodes;
	}

	public int getTrimmedNodes() 
	{
		return trimmedNodes;
	}
    
    
    
}
