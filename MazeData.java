import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Jinxin Hu
 * @version 1.0
 */
public abstract class MazeData implements Maze {
    public static final char ROAD = ' ';
    public static final char GOLD = 'G';
    public static final char LOST = 'L';
    public static final char WALL = '#';
    public static final char PLAYER = 'P';
    public static final char END = 'E';
    public static final double GOLD_PROBABILITY = 0.2f;
    public static final double THIEF_PROBABILITY = 0.1f;
    public static final int COIN = 5;

    private final Player player;
    private final Position goalLoc;
    private Position playerLoc;

    private int N, M;
    public char[][] maze;
    public boolean[][] visited;
    private List<Position> isRoom;
    protected List<Position> isWall;

    private int entranceX, entranceY;
    private int exitX, exitY;


    public MazeData(int N, int M, Player player, Position goalLoc) {
        this.player = player;
        this.goalLoc = goalLoc;

        if (N % 2 == 0 || M % 2 == 0)
            throw new IllegalArgumentException("" +
                    "Our Maze Generalization Algorihtm requires the width and height of the maze are odd numbers");
        this.N = N;
        this.M = M;

        maze = new char[N][M];
        visited = new boolean[N][M];
        isRoom = new ArrayList<Position>();
        isWall = new ArrayList<Position>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i % 2 == 1 && j % 2 == 1) {
                    maze[i][j] = ROAD;
                    isRoom.add(new Position(i, j));
                } else {
                    maze[i][j] = WALL;
                    isWall.add(new Position(i, j));
                }
                visited[i][j] = false;
            }
        }
        setCoinLocations(maze);
        setThiefLocation(maze);
        setEntranceX(1);
        setEntranceY(0);
        setExitX(goalLoc.getX());
        setExitY(goalLoc.getY());
        maze[exitX][exitY] = END;
    }

    public List<Position> getIsRoom() {
        return isRoom;
    }

    public List<Position> getIsWall() {
        return isWall;
    }

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    public Position getGoalLoc() {
        return goalLoc;
    }

    public void setN(int n) {
        N = n;
    }

    public void setEntranceX(int entranceX) {
        this.entranceX = entranceX;
    }

    public void setEntranceY(int entranceY) {
        this.entranceY = entranceY;
    }

    public void setExitX(int exitX) {
        this.exitX = exitX;
    }

    public void setExitY(int exitY) {
        this.exitY = exitY;
    }

    public int getEntranceX() {
        return entranceX;
    }

    public int getEntranceY() {
        return entranceY;
    }

    public int getExitX() {
        return exitX;
    }

    public int getExitY() {
        return exitY;
    }

    public boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public int getGoldCount() {
        return (int) ((int) isRoom.size() * GOLD_PROBABILITY);
    }

    public int getLostCount() {
        return (int) ((int) isRoom.size() * THIEF_PROBABILITY);
    }

    @Override
    public Position getPlayerLoc() {
        return this.playerLoc;
    }

    @Override
    public void setPlayer(Position current) {
        if (current.getX() < 0 || current.getX() >= N || current.getY() < 0 || current.getY() >= M) {
            throw new IllegalArgumentException("Invalid location");
        }
        this.playerLoc = current;
        maze[current.getX()][current.getY()] = PLAYER;
    }

    public void setCoinLocations(char[][] maze) {
        int totalGold = getGoldCount();
        int n = 0;
        while (n < totalGold) {
            Random random = new Random();
            int positon = random.nextInt(isRoom.size());
            char current = maze[isRoom.get(positon).getX()][isRoom.get(positon).getY()];
            if (current == ROAD) {
                maze[isRoom.get(positon).getX()][isRoom.get(positon).getY()] = GOLD;
                n++;
            }
        }
    }

    public void setThiefLocation(char[][] maze) {
        int totalLost = getLostCount();
        int n = 0;
        while (n < totalLost) {
            Random random = new Random();
            int positon = random.nextInt(isRoom.size());
            char current = maze[isRoom.get(positon).getX()][isRoom.get(positon).getY()];
            if (current == ' ') {
                maze[isRoom.get(positon).getX()][isRoom.get(positon).getY()] = LOST;
                n++;
            }
        }
    }

    /**
     * Get possible directions could move to.
     *
     * @return possible directions.
     */
    @Override
    public List<Direction> getPossibleMoves() {
        if (playerLoc.getX() < 0 || playerLoc.getX() >= N || playerLoc.getY() < 0 || playerLoc.getY() >= M) {
            throw new IllegalArgumentException("Invalid location");
        }
        char north = maze[playerLoc.getX() + 1][playerLoc.getY()];
        char south = maze[playerLoc.getX() - 1][playerLoc.getY()];
        char west = maze[playerLoc.getX()][playerLoc.getY() - 1];
        char east = maze[playerLoc.getX()][playerLoc.getY() + 1];

        List<Direction> possibleMoves = new ArrayList<>();

        if (north == END || south == END || east == END || west == END) {
            return null;
        }

        if ((playerLoc.getX() + 1) >= N
                || (playerLoc.getX() - 1) < 0
                || (playerLoc.getY() - 1) < 0
                || (playerLoc.getY() - 1) >= M) {
            possibleMoves.add(Direction.START);
        }

        if (north != WALL && (playerLoc.getX() + 1) < N - 1) {
            possibleMoves.add(Direction.NORTH);
        }
        if ((south != WALL) && (playerLoc.getX() - 1) > 0) {
            possibleMoves.add(Direction.SOUTH);
        }
        if (west != WALL && (playerLoc.getY() - 1) > 0) {
            possibleMoves.add(Direction.WEST);
        }
        if (east != WALL && (playerLoc.getY() + 1) < M - 1) {
            if (east == END) {
                return null;
            }
            possibleMoves.add(Direction.EAST);
        }

        return possibleMoves;
    }

    public void move(Direction direction, MazeData data, Player player) {
        int X = playerLoc.getX();
        int Y = playerLoc.getY();
        if (direction == Direction.EAST) {
            Y++;
        } else if (direction == Direction.WEST) {
            Y--;
        } else if (direction == Direction.NORTH) {
            X++;
        } else if (direction == Direction.SOUTH) {
            X--;
        } else if (direction == Direction.START) {
            X = data.entranceX;
            Y = data.entranceY;
        }
        if (X < 0 || Y < 0 || X >= N || Y >= M) {
            throw new IllegalArgumentException("Invalid move selection");
        }
        data.maze[playerLoc.getX()][playerLoc.getY()] = ROAD;
        this.playerLoc.setX(X);
        this.playerLoc.setY(Y);
        if (data.maze[playerLoc.getX()][playerLoc.getY()] == GOLD) {
            player.pickupCoins(COIN);
            System.out.println("player get Coin: " + COIN + " total Coin: " + player.getCoins());
        }
        if (data.maze[playerLoc.getX()][playerLoc.getY()] == LOST) {
            player.loseCoins();
            System.out.println("player lose Coin: 10%" + " total Coin: " + player.getCoins());
        }

        setPlayer(playerLoc);
        data.maze[playerLoc.getX()][playerLoc.getY()] = MazeData.PLAYER;
    }

    public void moveRandom(List<Direction> possibleMoves, MazeData data, Player player) {
        Random random = new Random();
        int randomNum = random.nextInt(possibleMoves.size());
        move(possibleMoves.get(randomNum), data, player);
    }
}
