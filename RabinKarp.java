/**This algorithm demonstrates the Rabin Karp algorithm and fully usable in any application
 * @author subhojit.mukherjee
 * 9/10/2015
 */
public class RabinKarp {

	/**
	 * @param args
	 */
	static void rabinKarp(String source,String pattern,int primeNumber)
	{
		final int D= 256; //Number characters possible in alphabet
		int N=source.length();
		int M=pattern.length();
		int sourceHash=0;
		int patternHash=0;
		int h=1;
		
		// h will strip the higher order bits
		for (int i = 0; i < M-1; i++)
	        h = (h*D)%primeNumber;
		//Pre counting the hash value of pattern and first block of source
		 for (int i = 0; i < M; i++)
		    {
			 //System.out.println(i);
			 //System.out.println("source:"+source.charAt(4));
		        patternHash = (D*patternHash + pattern.charAt(i))%primeNumber;
		        sourceHash = (D*sourceHash + source.charAt(i))%primeNumber;
		    }
		 
		 for(int i=0;i<N-M;i++)
		 {
			 
			 if( patternHash==sourceHash)
			 {
				 int j;
				 for(j=0;j<M;j++)
				 {
					 if(pattern.charAt(j)!=source.charAt(i+j))
					  break;
				 }
				 if(j==M)
				 {
					 System.out.println("Pattern found at:"+i);
				 }
					 
			 }
			 if(i<N-M)
			 { 
				 //Higher order bits are being stripped and lower order bits' new hash being added
				 sourceHash=(D*(sourceHash-source.charAt(i)*h)+source.charAt(i+M))%primeNumber;
				 if(sourceHash<0)
				 {
					 sourceHash+=primeNumber;
				 }
			 }
		 }
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String txt = " Fibonacci heap are mainly called so because Fibonacci numbers are used in the running time analysis. Also, every node in Fibonacci Heap has degree at most O(log n) and the size of a subtree rooted in a node of degree k is at least Fk+2, where Fk is the kth Fibonacci number";
	    String pat = "is";
	    //System.out.println(pat.charAt(1));
	    int q = 101;  // A prime number
	    rabinKarp(txt,pat, q);
	    

	}

}
