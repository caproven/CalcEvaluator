package calc.main;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the implementation of both the in-fix and post-fix expression evaluators.
 * @author caproven
 */
public class InFixEvalTest {

    /**
     * Tests the _-fix evaluators.
     */
    @Test
    public void testInfix() {
        // Directly tests the InFixEval class' evaluation method, which indirectly tests PostFixEval.
        assertTrue(1.0 == InFixEval.eval("3 - 2"));
        assertTrue(18.0 == InFixEval.eval("27 - 9"));
        assertTrue(65.0 == InFixEval.eval("5 * 13"));
        assertTrue(9.0 == InFixEval.eval("99 / 11"));
        assertTrue(16.0 == InFixEval.eval("2 ^ 4"));
        assertTrue(0.0 == InFixEval.eval(""));
        assertTrue(0.0 == InFixEval.eval("( )"));
        assertTrue(5.5 == InFixEval.eval("( 3 + 2 * 4 ) / 2"));
        assertTrue(-0.25 == InFixEval.eval("2 - 4 ^ ( 16 / 4 / 2 - 8 * 0.5 + 1 ) - 2"));
        assertTrue(63.0 == InFixEval.eval("( ( 100 - 5 * 6 ) + ( ( 4 * 3 ) + 1 ) ) - 20"));
        assertTrue(-3115 == InFixEval.eval("5 + 5 - 5 / 5 * 5 ^ 5"));
        assertTrue(10.0 == InFixEval.eval("9 ^ ( 1 / 2 ) / 3 + ( 1 + ( 1 / 4 ) ^ ( -1 ) * 2 )"));
        assertTrue(1.0 == InFixEval.eval("( 9 + ( 2 ^ ( 1 + 100 - 25 ^ ( 1 / 2 ) * 20 ) ) ) / 11"));
    }

}
