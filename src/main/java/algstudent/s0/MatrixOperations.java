package algstudent.s0;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;


/**
 * Represents a two-dimension square matrix
 * @author Rita
 */
public class MatrixOperations {
	
	private int[][] matrix;
	private int size;
	
	
	// constructors
	
	/**
	 *  Creates a new matrix of size n x n and fills it with random values. 
	 *  These random values must be parameterizable between a maximum (max) and a minimum (min) value.
	 * @param min the minimum value for the elements of the matrix
	 * @param max the maximum value for the elements of the matrix
	 */
	public MatrixOperations(int n, int min, int max)
	{
		Random random = new Random();
		matrix = new int[n][n];
		size = n;
		
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				// fill the matrix with random values between min and max (both inclusive)
				matrix[i][j] = random.nextInt(max) + min;
			}
		}
		
	}

	
	/**
	 * Creates a matrix using data of the file provided as parameter
	 * @param fileName
	 */
	public MatrixOperations(String fileName)
	{
		this.matrix = createMatrixFromFile(fileName);
	}	
	
	/**
	 * Creates a new matrix with the values inside the file
	 * File format: first line --> size of the matrix. Rest of lines --> matrix rows 
	 * @param fileName the file where the matrix is
	 */
	private int[][] createMatrixFromFile(String fileName)
	{
	
		BufferedReader reader = null;
		String line = null;
        int[][] theMatrix = null;
		
        try 
        {
        	// open the file
			reader = new BufferedReader(new FileReader(fileName));
			
			// the first line is the size of the matrix
			line = reader.readLine();
			this.size = Integer.parseInt(line);
			
			// the rest are the elements
			theMatrix = new int[size][size];
			
			for ( int i = 0; i < size; i++)
			{
				// get the elements row by row
				line = reader.readLine();
				String elementsRow[] = line.split("\t");
				
				for (int j = 0; i < size; j++)
				{
					theMatrix[i][j] = Integer.parseInt(elementsRow[j]);
				}
				
			}
			
        } 
        catch( IOException e)
        {
        	System.out.println("Error trying to read the file " + fileName);
        }
        finally
        {
        	try {
				reader.close();
			} catch (IOException e) {
				System.out.println("Error trying to close the file " + fileName);
			}      
        }
		
		return theMatrix;
	}
	
	
	/**
	 * @return the matix size
	 */
	public int getSize()
	{
		return this.size;
	}
	
	/**
	 * Prints in the console all the matrix elements
	 */
	public void write()
	{
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
			{
				// print the element
				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}
	}
	
	
	/**
	 *  Computes the summation of all the elements of the matrix diagonal.
	 *  Iterate over all the matrix elements --> Complexity quadratic O(n^2)
	 */
	public int sumDiagonal1()
	{
		int counter = 0;
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
			{
				// if it is a diagonal element, we add it
				if (i == j)
					counter += matrix[i][j];
			}
		}
		
		return counter;
	}
	
	
	/**
	 *  Computes the addition of all the elements of the matrix diagonal
	 *  Iterate over the elements of the main diagonal --> Complexity linear O(n)
	 */
	public int sumDiagonal2()
	{
		int counter = 0; 
		for (int i = 0; i < size; i++)
		{
			counter += matrix[i][i];
		}
		
		return counter;
	}
	
	
	/**
	 * Iterates through the matrix starting at position (i, j) according to the following 
	 * number meanings: 1 – move up; 2 – move right; 3 – move down; 4 – move left
	 * 
	 * Traversed elements would be set to -1 value
	 * 
	 * @param i the row from where we start
	 * @param j the  column from where we start
	 */
	public void travelPath(int i, int j)
	{
		// CREATE METHOD TO SAVE TO FILE AND END (LO DE number of movements = 32)
		
		
		boolean end = false;

		while(!end)
		{
			int value = matrix[i][j];
			matrix[i][j]= -1;
			
			// move in the corresponding direction
			
			switch(value)
			{
				case 1:
					//one row up, same column
					i -= 1;
					break;
					
				case 2:
					// same row, one column right
					j += 1;
					break;
					
				case 3:
					// one row down, same column
					i += 1;
					break;
					
				case 4:
					// same row, one column left
					j -= 1;
					break;
			}
			
			//check if we have to end the iteration - if it goes beyond the limits of the matrix OR
			// an already traversed postition is reached
			if ( i < 0 || i >= size || j < 0 || j >= size || matrix[i][j] == -1)
				end = true;
		}

	}
	
	
	
	
	
	
	
	
	
	
}
