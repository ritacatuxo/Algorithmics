package algstudent.s12;


/**
 * Complexity of O(n^4)
 * 
 * @author Rita
 */
public class Loop4 {
	public static long loop4(int n) {
		int cont = 0;
		for (int i = 1; i <= n*n; i++)
			for (int j = 1; j <= n; j++)
				for (int k = j; k <= n; k++)
					cont ++;
		return cont;
	}
	
	public static void main(String arg []){
		long t1, t2;
		long cont = 0;
		int repetitions = Integer.parseInt(arg[0]);
	
		for (int n=1; n<=Integer.MAX_VALUE; n*=2) {
			t1 = System.currentTimeMillis();
	 
			for (int repetition=1; repetition<=repetitions; repetition++) {
				cont = loop4(n);
			} 
	 
			t2 = System.currentTimeMillis();
			System.out.println("n="+n+ "\tTIME="+(t2-t1)+"\tCONT="+cont+"\trepetitions="+repetitions);	
	 }  // for
	
	} // main
} //class