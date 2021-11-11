import java.util.List;

public interface Maze {
  // "The maze can produce the player's location and gold count"
  Position getPlayerLoc();
  int getGoldCount();

  /**
   * Set player at the start location.
   * @param loc start location.
   */
  void setPlayer(Position loc);

  /**
   * Get possible directions could move to.
   *
   * @return possible directions.
   */
  List<Direction> getPossibleMoves();

  /**
   * move to the direction.
   * @param direction the direction to move.
   */
  void move(Direction direction, MazeData data, Player player);
}
