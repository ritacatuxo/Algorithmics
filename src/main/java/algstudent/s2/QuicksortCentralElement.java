package algstudent.s2;


/* This program can be used to sort n elements with 
 * the best algorithm of this lab. It is the QUICKSORT */
public class QuicksortCentralElement extends Vector {
	
	public QuicksortCentralElement(int nElements) {
		super(nElements);
	}
	
	private void quickSort(int left, int right) 
	{
		int pivot;
		int i = left;
		int j = right - 1;
		
		
		if (left < right)
		{
			// compute the center element doing the median of three
			int center = median_of_three(left, right);
			
			// in case there are 3 or less elements they already ordered
			if ( (right - left) >= 3 ) 
			{
				// set the pivot to the element of the center
				pivot = elements[center];
				// exchange it with the element on the right
				interchange(center, right);
				
				
				do
				{
					while (elements[i] <= pivot && i < right)
						i++;
					while (elements[j] >= pivot && j > left)
						j--;
					if (i < j)
						interchange(i, j);
				}
				while (i < j);
				
				// position of the pivot
				interchange(i, right);
				quickSort(left, i-1);
				quickSort(i+1, right);
				
			}
		}
		
		
	}
	
	/**
	 * This method gets the position of the median of the three elements (left, center and right)
	 * Then these three elements are sorted
	 */
	private int median_of_three(int left, int right)
	{
		int center = (left + right) / 2;
		
		if (elements[left] > elements[center])
			interchange(left, center);
		
		if (elements[left] > elements[right])
			interchange(left, right);
		
		if (elements[center] > elements[right])
			interchange(center, right);
		
		return center;
	}
	
	
	

	@Override
	public void sort() {
		quickSort(0, elements.length-1);		
	}
	
	@Override
	public String getName() {
		return "Quicksort - Central element";
	} 
} 
