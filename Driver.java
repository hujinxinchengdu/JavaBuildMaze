/**
 * @author Jinxin Hu
 * @version 1.0
 */
public class Driver {
    public static void main(String[] args) {
        int N = 101;
        int M = 101;
        AlgoVisualizerPerfectMaze perfectMaze = new AlgoVisualizerPerfectMaze(N, M);
        AlgoVisualizerRoomMaze roomMaze = new AlgoVisualizerRoomMaze(N, M);
        AlgoVisualizerWrappingRoomMaze wrappingRoomMaze = new AlgoVisualizerWrappingRoomMaze(N, M);
    }
}
