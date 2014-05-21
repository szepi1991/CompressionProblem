import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.Random;

import org.junit.Test;


public class TestStirling {

	@Test
	public void testStirling() {
		// do a bunch of tests... read expected values out from this table:
		int [][] vals = new int [][] {
			{1, 0,   0,    0,     0,     0,     0,    0,   0,  0, 0},
			{0, 1,   0,    0,     0,     0,     0,    0,   0,  0, 0},
			{0, 1,   1,    0,     0,     0,     0,    0,   0,  0, 0},
			{0, 1,   3,    1,     0,     0,     0,    0,   0,  0, 0},
			{0, 1,   7,    6,     1,     0,     0,    0,   0,  0, 0},
			{0, 1,  15,   25,    10,     1,     0,    0,   0,  0, 0},
			{0, 1,  31,   90,    65,    15,     1,    0,   0,  0, 0},
			{0, 1,  63,  301,   350,   140,    21,    1,   0,  0, 0},
			{0, 1, 127,  966,  1701,  1050,   266,   28,   1,  0, 0},
			{0, 1, 255, 3025,  7770,  6951,  2646,  462,  36,  1, 0},
			{0, 1, 511, 9330, 34105, 42525, 22827, 5880, 750, 45, 1}};
		int rows = vals.length;
		int cols = vals[0].length;
		Random r = new Random(13);
		for (int t = 0; t < 222; ++t) {
			// run 222 'random' tests.
			// I prefer random tests, because then sometimes we should get
			// memoized examples, other times we'd have to go through a sequence
			// of recursive function calls.
			int i = r.nextInt(rows);
			int j = r.nextInt(cols);
			assertEquals(BigInteger.valueOf(vals[i][j]), Stirling.stirling(i, j));
		}
	}

}
