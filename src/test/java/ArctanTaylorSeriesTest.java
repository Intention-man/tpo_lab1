import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ArctanTaylorSeriesTest {

    private static final double MAX_DELTA = 0.0001;

    @Test
    public void testArctanZero() {
        assertEquals(0.0, ArctanTaylorSeries.arctan(0.0), MAX_DELTA);
    }

    @Test
    public void testArctanPositiveX() {
        assertEquals(0.4636, ArctanTaylorSeries.arctan(0.5), MAX_DELTA);
    }

    @Test
    public void testArctanNegativeX() {
        assertEquals(-0.3187475640666714, ArctanTaylorSeries.arctan(-0.33), MAX_DELTA);
    }

    @Test
    public void testArctanOne() {
        assertEquals(Math.PI / 4, ArctanTaylorSeries.arctan(1.0), MAX_DELTA);
    }

    @Test
    public void testArctanMinusOne() {
        assertEquals(-Math.PI / 4, ArctanTaylorSeries.arctan(-1.0), MAX_DELTA);
    }

    @Test
    public void testArctanSmallPositiveX() {
        assertEquals(0.0996, ArctanTaylorSeries.arctan(0.1), MAX_DELTA);
    }

    @Test
    public void testArctanSmallNegativeX() {
        assertEquals(-0.0996, ArctanTaylorSeries.arctan(-0.1), MAX_DELTA);
    }

    @Test
    public void testArctanEdgeCasePositive() {
        assertEquals(0.6305, ArctanTaylorSeries.arctan(0.73), MAX_DELTA);
    }

    @Test
    public void testArctanEdgeCaseNegative() {
        assertEquals(-0.6305, ArctanTaylorSeries.arctan(-0.73), MAX_DELTA);
    }

    @Test
    public void testInvalidXTooLarge() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ArctanTaylorSeries.arctan(1.1);
        });
        assertEquals("x must be in the range [-1, 1]", exception.getMessage());
    }

    @Test
    public void testInvalidXTooSmall() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ArctanTaylorSeries.arctan(-1.1);
        });
        assertEquals("x must be in the range [-1, 1]", exception.getMessage());
    }

    @Test
    public void testAgainstMathAtanPositive() {
        double x = 0.6;
        double expected = Math.atan(x);
        double actual = ArctanTaylorSeries.arctan(x);
        assertEquals(expected, actual, MAX_DELTA);
    }

    @Test
    public void testAgainstMathAtanNegative() {
        double x = -0.6;
        double expected = Math.atan(x);
        double actual = ArctanTaylorSeries.arctan(x);
        assertEquals(expected, actual, MAX_DELTA);
    }

    @Test
    public void testAgainstMathAtanNearOne() {
        double x = 0.99;
        double expected = Math.atan(x);
        double actual = ArctanTaylorSeries.arctan(x);
        assertEquals(expected, actual, MAX_DELTA);
    }

    @Test
    public void testAgainstMathAtanNearMinusOne() {
        double x = -0.99;
        double expected = Math.atan(x);
        double actual = ArctanTaylorSeries.arctan(x);
        assertEquals(expected, actual, MAX_DELTA);
    }
}
