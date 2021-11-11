import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Jinxin Hu
 * @version 1.0
 */
public class PositionTest {
    Position test;
    @Before
    public void setUp() throws Exception {
        test = new Position(1,2);
    }

    @Test
    public void getX() {
        assertEquals(1,test.getX());
    }

    @Test
    public void getY() {
        assertEquals(2,test.getY());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetTemperatureException() {
        Position exp = new Position(-1, -2);
    }
}