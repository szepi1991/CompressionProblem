import java.math.BigInteger;
import java.util.HashMap;



public class Stirling {

	// we memoize stirling numbers
	static private final HashMap<NumPair, BigInteger> stirlingS2 =
			new HashMap<NumPair, BigInteger>();
	
	static {
		stirlingS2.put(new NumPair(0, 0), BigInteger.valueOf(1));
	}
	
	/** 
	 * This method calculates the Stirling number of second kind.
	 * 
	 * The returned value is the number of ways to partition a set of n objects
	 * into k non-empty subsets.
	 */
	public static BigInteger stirling(int n, int k) {
		// first check the table
		NumPair curPair = new NumPair(n, k);
		BigInteger toRet = null;
		if ( ( toRet = stirlingS2.get(curPair) ) != null)
			return toRet;
		
		// we calculate it recursively. See e.g. Wikipedia for a reference.
		// filling the table bottom-up would probably be better
		
		if (n == 0 || k == 0) {
			toRet = BigInteger.valueOf(0);
		} else {
			toRet = stirling(n-1, k).multiply(BigInteger.valueOf(k)).
														add(stirling(n-1, k-1));
		}
		stirlingS2.put(curPair, toRet);
		return toRet;
	}

	
	
	
	
	private static final class NumPair {
		final int n;
		final int k;
		public NumPair(int n, int k) {
			this.n = n;
			this.k = k;
		}
		
		@Override public int hashCode() {
			return n + 514229*k;
		}
		
		@Override public boolean equals(Object that) {
			if (this == that) return true;
			if (!(that instanceof NumPair)) return false;
			NumPair thatPair = (NumPair)that;
			return (thatPair.n == n && thatPair.k == k);
		}
	}
}
