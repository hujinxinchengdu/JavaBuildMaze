import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * @author Jinxin Hu
 * @version 1.0
 */
public class RoomMazeTest {
    AlgoVisualizerRoomMaze roomMaze;

    @Test
    public void builtMaze() {
        int N = 21;
        int M = 31;
        roomMaze = new AlgoVisualizerRoomMaze(N,M);
    }

    @Test (expected = IllegalArgumentException.class)
    public void builtMazeExp1() {
        int N = 20;
        int M = 31;
        roomMaze = new AlgoVisualizerRoomMaze(N,M);
    }

    @Test (expected = IllegalArgumentException.class)
    public void builtMazeExp() {
        int N = 21;
        int M = 30;
        roomMaze = new AlgoVisualizerRoomMaze(N,M);
    }
}