import java.util.Random;

/**
 * @author Jinxin Hu
 * @version 1.0
 */
public class WrappingRoomMaze extends MazeData {

    private final int remainingWalls;
    private static final int d[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public WrappingRoomMaze(int N, int M, Player player, Position goalLoc, int remainingWalls) {
        super(N, M, player, goalLoc);
        this.remainingWalls = remainingWalls;
    }

    public void builtMaze(MazeData data) {
        RandomQueue<Position> queue = new RandomQueue<Position>();
        Position first = new Position(data.getEntranceX(), data.getEntranceY() + 1);
        queue.add(first);
        data.visited[first.getX()][first.getY()] = true;

        while (queue.size() != 0) {
            Position curPos = queue.remove();

            for (int i = 0; i < 4; i++) {
                int newX = curPos.getX() + d[i][0] * 2;
                int newY = curPos.getY() + d[i][1] * 2;

                if (data.inArea(newX, newY)
                        && !data.visited[newX][newY]
                        && (data.maze[newX][newY] == MazeData.ROAD
                        || data.maze[newX][newY] == MazeData.GOLD
                        || data.maze[newX][newY] == MazeData.LOST)) {
                    queue.add(new Position(newX, newY));
                    data.visited[newX][newY] = true;
                    setData(curPos.getX() + d[i][0], curPos.getY() + d[i][1], data);
                }
            }
        }
        int n = 0;
        int wallNeedToRemove = isWall.size() - getIsRoom().size() - remainingWalls;
        while (n < wallNeedToRemove) {
            Random random = new Random();
            int positon = random.nextInt(isWall.size());
            char current = maze[isWall.get(positon).getX()][isWall.get(positon).getY()];
            if (current == WALL) {
                maze[isWall.get(positon).getX()][isWall.get(positon).getY()] = ROAD;
                n++;
            }
        }
    }

    public void setData(int x, int y, MazeData data) {
        if (data.inArea(x, y))
        data.maze[x][y] = MazeData.ROAD;
    }
}