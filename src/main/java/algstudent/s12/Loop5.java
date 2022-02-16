package algstudent.s12;


/**
 * Complexity of  O(n^3 logn)
 * 
 * @author Rita
 */
public class Loop5 {
	public static long loop5(int n) {
		int cont = 0;
		for (int i = 1; i <= n*n; i++)
			for (int j = 1; j <= n; j++)
				for (int k = j; k <= n; k *= 2)
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
				cont = loop5(n);
			} 
	 
			t2 = System.currentTimeMillis();
			System.out.println("n="+n+ "\tTIME="+(t2-t1)+"\tCONT="+cont+"\trepetitions="+repetitions);	
	 }  // for
	
	} // main
} //class