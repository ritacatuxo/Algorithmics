package algstudent.s2;

/* This program can be used to sort n elements with 
 * a "bad" algorithm (quadratic). 
 * It is the SELECTION */
public class Selection extends Vector {
	public Selection(int nElements) {
		super(nElements);
	}
	
	@Override
	public void sort() {
		int posMin;
		
		for( int i = 0; i < elements.length-1; i++)
		{
			 // get the position of the lowest element
			 posMin = findPosMin(elements, i);
			 // exchange it with the first elements
			 interchange(i, posMin);
		}
		
	}  
	
	
	
	private int findPosMin(int[] elements, int i) {
		int posMin = i;
		
		for (int k = i; k < elements.length; k++)
		{
			// compare all the elements with the minimum one
			if( elements[k] < elements[posMin])
			{
				// if a new element is less than elements[posMin], we update it
				posMin = k;
			}
		}
		
		return posMin;
	}

	@Override
	public String getName() {
		return "Selection";
	} 
} 
