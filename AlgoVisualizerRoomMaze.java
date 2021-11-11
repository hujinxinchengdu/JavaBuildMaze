/**
 * @author Jinxin Hu
 * @version 1.0
 */
import java.awt.*;
import java.util.List;

public class AlgoVisualizerRoomMaze {

    private static int DELAY = 200;
    private static int blockSide = 8;


//    private  PerfectMaze data;
        private RoomMaze data;
//    private  WrappingRoomMaze data;
    private AlgoFrame frame;

    public AlgoVisualizerRoomMaze(int N, int M){
        Player player = new PlayerImp();
        Position goalLoc = new Position(N - 2,M - 1);
        // 初始化数据
        data = new RoomMaze(N, M,player, goalLoc,10000);
//        data = new WrappingRoomMaze(N, M,player, goalLoc,320);
//        data = new PerfectMaze(N, M,player, goalLoc);
        int sceneHeight = data.getN() * blockSide;
        int sceneWidth = data.getM() * blockSide;

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Random Maze Generation Visualization", sceneWidth, sceneHeight);

            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run(){
        Player player = new PlayerImp();
        setData();
        data.builtMaze(data);
        data.setPlayer(new Position(5,5));
        for(int i = 0; i < 10000; i++) {
            List<Direction> possibleMoves;
            possibleMoves = data.getPossibleMoves();
            if (possibleMoves == null) {
                System.out.println("win");
                break;
            }
            data.moveRandom(possibleMoves,data,player);

            setData();
        }
        setData();
    }

    private void setData(){
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }
}
