package algstudent.s32;

public class Tromino 
{	
	
	// attributes
	
	private int board[][];
	private int blackCell = -1;
	private int trominoNumber = 1;	// representative number of each tromino (the first tromino 
									// placed has number 1, the second number 2... and so on)
	
	
	// constructor

	public Tromino(int size, int row, int column)
	{
		// initialize and fill the board with all empty squares
		
		this.board = new int[size][size];
		
		for (int i = 0; i < size; i++) 
		{
			for (int j = 0; j < size; j++) 
			{
				board[i][j] = 0;
			}
		}
				
		// place the black cell
		this.board[row][column] = blackCell;		
	}
	
	// methods

	/**
	 * Prints the tromino's board
	 */
	public void print() 
	{
		
		for (int i = 0; i < board.length; i++) 
		{
			for (int j = 0; j < board[i].length; j++)
			{
				
				if(board[i][j] == -1)
					System.out.print("|" + board[i][j] + "|");
				else if (board[i][j] >= 10)
					System.out.print("|" + board[i][j] + "|");
				else
					System.out.print("| " + board[i][j] + "|");
			}
			System.out.println();
		}
	}
	
	
	
	public void fillInTable(int n, int startingX, int startingY, int blankX, int blankY)
	{		
		// find out which type of tromino we have to place 
		
		int kindOfTromino = -1;
		
		// black cell is places in the upper left quadrant
		if (blankX < startingX + n/2 && blankY < startingY + n/2) 
			kindOfTromino = 1;
		
		// black cell is placed in the upper right quadrant
		else if (blankX < startingX + n/2 && blankY >= startingY + n/2) 
			kindOfTromino = 2;
		
		// black cell is placed in the lower left quadrant
		else if (blankX >= startingX + n/2 && blankY < startingY + n/2) 
			kindOfTromino = 3;
			
		// black cell is in the lower right quadrant
		else
			kindOfTromino = 4;
		

		switch(kindOfTromino)
		{
			// quadrant 1
			case 1: 
				// place the tromino in the other 3 quadrants
				board[startingX+n/2][startingY+n/2-1] = trominoNumber;		// part of the tromino placed in the lower left quadrant
				board[startingX+n/2][startingY+n/2] = trominoNumber;		// part of the tromino placed in the lower right quadrant
				board[startingX+n/2-1][startingY+n/2] = trominoNumber;		// part of the tromino placed in the upper right quadrant
				trominoNumber++;
				
				if (n == 2) 
					break;
				
				else
				{
					// 4 recursive calls to the other quadrants
					fillInTable(n/2, startingX, startingY, blankX, blankY); //quadrant 1
					fillInTable(n/2, startingX+n/2, startingY, startingX+n/2, startingY+n/2-1); //quadrant 3
					fillInTable(n/2, startingX, startingY+n/2, startingX+n/2-1, startingY+n/2); //quadrant 2
					fillInTable(n/2, startingX+n/2, startingY+n/2, startingX+n/2, startingY+n/2); //quadrant 4
				}
				break;
				
			// quadrant 2
			case 2: 				
				// place the tromino in the other 3 quadrants
				board[startingX+n/2][startingY+n/2-1] = trominoNumber;		// part of the tromino placed in the lower left quadrant
				board[startingX+n/2][startingY+n/2] = trominoNumber;		// part of the tromino placed in the lower right quadrant
				board[startingX+n/2-1][startingY+n/2-1] = trominoNumber;	// part of the tromino placed in the upper left quadrant
				trominoNumber++;
				
				if (n == 2) 
					break;
				else
				{
					// 4 recursive calls to the other quadrants
					fillInTable(n/2, startingX, startingY+n/2, blankX, blankY); //quadrant 2
					fillInTable(n/2, startingX, startingY, startingX+n/2-1, startingY+n/2-1); //quadrant 1
					fillInTable(n/2, startingX+n/2, startingY, startingX+n/2, startingY+n/2-1); //quadrant 3
					fillInTable(n/2, startingX+n/2, startingY+n/2, startingX+n/2, startingY+n/2); //quadrant 4
				}
				break;
				
			// quadrant 3
			case 3:
				// place the tromino in the other 3 quadrants
				board[startingX+n/2-1][startingY+n/2]  = trominoNumber;		// part of the tromino placed in the upper right quadrant
				board[startingX+n/2][startingY+n/2] = trominoNumber;		// part of the tromino placed in the lower right quadrant
				board[startingX+n/2-1][startingY+n/2-1] = trominoNumber;	// part of the tromino placed in the upper left quadrant
				trominoNumber++;
				
				if (n == 2) 
					break;
				else
				{
					// 4 recursive calls to the other quadrants
					fillInTable(n/2, startingX+n/2, startingY, blankX, blankY); //quadrant 3
					fillInTable(n/2, startingX, startingY, startingX+n/2-1, startingY+n/2-1); //quadrant 1
					fillInTable(n/2, startingX, startingY+n/2, startingX+n/2-1, startingY+n/2); //quadrant 2
					fillInTable(n/2, startingX+n/2, startingY+n/2, startingX+n/2, startingY+n/2); //quadrant 4
				}
				break;
				
			// quadrant 4
			case 4:
				
				// place the tromino in the other 3 quadrants
				board[startingX+n/2-1][startingY+n/2] = trominoNumber;			// part of the tromino placed in the upper right quadrant
				board[startingX+n/2][startingY+n/2-1] = trominoNumber;			// part of the tromino placed in the lower left quadrantv
				board[startingX+n/2-1][startingY+n/2-1] = trominoNumber;		// part of the tromino placed in the upper left quadrant
				trominoNumber++;
				
				
				if (n == 2) 
					break;
				else
				{
					// 4 recursive calls to the other quadrants
					fillInTable(n/2, startingX+n/2, startingY+n/2, blankX, blankY); //quadrant 4
					fillInTable(n/2, startingX, startingY, startingX+n/2-1, startingY+n/2-1); //quadrant 1
					fillInTable(n/2, startingX+n/2, startingY, startingX+n/2, startingY+n/2-1); //quadrant 3
					fillInTable(n/2, startingX, startingY+n/2, startingX+n/2-1, startingY+n/2); //quadrant 2
				}
				break;
				
		}
	}
	
	
}
