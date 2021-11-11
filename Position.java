/**
 * @author Jinxin Hu
 * @version 1.0
 */
public class Position {

    private int x, y;

    public Position(int x, int y){
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("invalid input");
        }
        this.x = x;
        this.y = y;
    }

    public int getX(){return x;}
    public int getY(){return y;}

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
