import java.math.BigInteger;

public class Calculator {

	private static final boolean PRINT_INFO = true;
	
	public static void main(String[] args) {
		// try for some values of n
		System.out.println("Trying for some values of n (exact calculation this time)");
		System.out.println("I display |V|*VCdim - |E|. We are happy if it's negative");

		for (int n = 2; n < 10; n += 1) {
			System.out.print(2*n + " - ");
			cannotCompress(n, false);
		}
	}
	
	/**
	 * This function returns whether we have proof that there is no compression.
	 * 
	 * @param n	HALF the number of numbers we are considering total orders on
	 * @param approx if true, we use the lower bound on the number of edges
	 * @return true iff we proved there is no compression, ie |V|*VCdim < |E|
	 */
	public static boolean cannotCompress(int n, boolean approx) {
		BigInteger numEdges = (approx) ? getNumEdgesL(n) : getNumEdges(n);
		BigInteger numVerts = getNumVertices(n);
		BigInteger diff = numVerts.multiply(BigInteger.valueOf(2*n-1)).subtract(numEdges);
		boolean proved = (diff.signum() == -1);
		
		if (PRINT_INFO) {
			System.out.print(proved ? "YES!" : "nope");
			System.out.print(": " + diff);
//			System.out.print(": " + numVerts + "*" + (n-1));
//			System.out.print(proved ? ">=" : "<");
//			System.out.print(numEdges);
			System.out.println();			
		}
		
		return proved;
	}
	
	/**
	 * This function returns (a lower bound on) the number of edges in the one 
	 * inclusion graph of the concept classes to the sample we talked about.
	 * 
	 * @param n	HALF the number of numbers we are considering total orders on
	 */
	public static BigInteger getNumEdgesL(int n) {
		BigInteger eLower = new BigInteger("0");
		for (int k = 1; k < n; ++k) {
			// this corresponds to one summand in the sum
			BigInteger prod = factorial(k).pow(2);
			prod = prod.multiply(BigInteger.valueOf(4*k+1));
			prod = prod.multiply(Stirling.stirling(n-1, k));
			prod = prod.multiply(Stirling.stirling(n, k+1));
			eLower = eLower.add(prod);
		}
		
		return eLower.multiply(BigInteger.valueOf(n*n)); 
	}

	/**
	 * This function returns the (exact) number of edges in the one 
	 * inclusion graph of the concept classes to the sample we talked about.
	 * 
	 * @param n	HALF the number of numbers we are considering total orders on
	 */
	public static BigInteger getNumEdges(int n) {
		BigInteger edges = factorial(n-1).pow(2);
		BigInteger factor = BigInteger.valueOf(4*n-3); // 4n-3
		edges = edges.multiply(factor);
		
		for (int k = 1; k < n-1; ++k) {
			// this corresponds to one summand in the sum
			BigInteger prod = factorial(k).pow(2);
			prod = prod.multiply(  Stirling.stirling(n-1, k).pow(2) );
			prod = prod.multiply(BigInteger.valueOf(4*k*k + 11*k + 4));
			
			edges = edges.add(prod);
		}
		
		return edges.multiply(BigInteger.valueOf(n*n)); 
	}
	
	
//	/**
//	 * This function returns the number of edges in the one inclusion graph
//	 * of the concept classes to the sample we talked about.
//	 * 
//	 * @param n	HALF the number of numbers we are considering total orders on
//	 */
//	public static BigInteger getNumEdges(int n) {
//		BigInteger eLower = new BigInteger("0");
//		for (int k = 1; k < n; ++k) {
//			// this corresponds to one summand in the sum
//		}
//		
//		return eLower.multiply(BigInteger.valueOf(n*n)); 
//	}
	
	
	/**
	 * This function returns the number of vertices in the one inclusion graph
	 * of the concept classes to the sample we talked about.
	 * 
	 * @param n	HALF the number of numbers we are considering total orders on
	 */
	public static BigInteger getNumVertices(int n) {
		BigInteger verts = new BigInteger("0");
		for (int k = 1; k <= n; ++k) {
			// this corresponds to one summand in the sum
			BigInteger prod = factorial(k).pow(2);
			prod = prod.multiply(Stirling.stirling(n, k));
			prod = prod.multiply(Stirling.stirling(n+1, k+1));
			verts = verts.add(prod);
		}
		
		return verts.multiply(BigInteger.valueOf(2)); 
	}
	
	
	
	/**
	 * The standard factorial function.
	 */
	public static BigInteger factorial(int n) {
		// n is probably not so big, so it's ok not to cache.
		// though we construct a lot of BigIntegers... TODO ?
		if (n == 0) return BigInteger.valueOf(1);
		BigInteger ret = BigInteger.valueOf(n);
		while (--n > 0) {
			ret = ret.multiply(BigInteger.valueOf(n));
		}
		return ret;
	}
	
}
