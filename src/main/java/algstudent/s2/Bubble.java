package algstudent.s2;


/* This program can be used to sort n elements with 
 * a "bad" algorithm (quadratic). 
 * It is the BUBBLE or DIRECT EXCHANGE */
public class Bubble extends Vector {
	
	public Bubble(int nElements) 
	{
		super(nElements);
	}

	
	
	@Override
	public void sort()
	{
		
		for (int i = 0; i <= elements.length - 2; i++) 
		{
			for (int j = elements.length - 1; j > i; j--)
			{
				// in case the element on the left is greater than the element in the right
				if (elements[j-1] > elements[j])
					// we exchange them
					interchange(j-1, j);
			}
		}
		
		
	}  
	
	
	
	@Override
	public String getName() 
	{
		return "Bubble";
	} 
} 

