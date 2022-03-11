package algstudent.s32;

import java.util.Random;

public class TrominoTimes {
	
	public static void main(String[] args) 
	{
		Random random = new Random();
		Tromino tromino;
		int x, y;
		
		for (int i = 2; i < 9; i*= 2) 
		{
			x = random.nextInt(i);
			y = random.nextInt(i);
			tromino = new Tromino(i, x, y);
			 
			// time measurement
			long  t1 = System.currentTimeMillis();  
			tromino.fillInTable(i, 0, 0, x, y);
	        long  t2 = System.currentTimeMillis(); 
	        
	        System.out.println("n=" + i + "**TIME=" + (t2 - t1));
		}
		
		

		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
