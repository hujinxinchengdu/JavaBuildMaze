import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Jinxin Hu
 * @version 1.0
 */
public class PlayerImpTest {
    Player player;
    @Before
    public void setUp() throws Exception {
        player = new PlayerImp();
    }

    @Test
    public void getCoins() {
        assertEquals(0,player.getCoins());
    }

    @Test
    public void pickupCoins() {
        player.pickupCoins(5);
        assertEquals(5,player.getCoins());
    }

    @Test
    public void loseCoins() {
        player.pickupCoins(5);
        player.loseCoins();
        System.out.println(player.getCoins());
        assertEquals(4,player.getCoins());
    }
}