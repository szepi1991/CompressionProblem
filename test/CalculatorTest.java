import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;


public class CalculatorTest {

	@Test
	public void testGetNumEdges() {
		fail("Not yet implemented");
	}

	@Test
	public void testFactorial() {
		int [] exp = new int [] {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
		for (int i = 0; i < exp.length; ++i)
			assertEquals(BigInteger.valueOf(exp[i]), Calculator.factorial(i));
	}

}
