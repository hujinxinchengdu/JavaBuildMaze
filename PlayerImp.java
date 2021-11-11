/**
 * @author Jinxin Hu
 * @version 1.0
 */
public class PlayerImp implements Player {

    private int totalCoins;

    /**
     * Get current coins.
     *
     * @return current coins.
     */
    @Override
    public int getCoins() {
        return this.totalCoins;
    }

    @Override
    public void pickupCoins(int coins) {
        this.totalCoins = this.totalCoins + coins;
    }

    @Override
    public void loseCoins() {
        this.totalCoins *= (1 - COIN_LOSS_RATIO);
    }
}
