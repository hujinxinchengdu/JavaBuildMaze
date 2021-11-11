import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * @author Jinxin Hu
 * @version 1.0
 */
public class WrappingRoomMazeTest {
    AlgoVisualizerWrappingRoomMaze wrappingRoomMaze;

    @Test
    public void builtMaze() {
        int N = 21;
        int M = 31;
        wrappingRoomMaze = new AlgoVisualizerWrappingRoomMaze(N,M);
    }

    @Test (expected = IllegalArgumentException.class)
    public void builtMazeExp1() {
        int N = 20;
        int M = 31;
        wrappingRoomMaze = new AlgoVisualizerWrappingRoomMaze(N,M);
    }

    @Test (expected = IllegalArgumentException.class)
    public void builtMazeExp() {
        int N = 21;
        int M = 30;
        wrappingRoomMaze = new AlgoVisualizerWrappingRoomMaze(N,M);
    }
}