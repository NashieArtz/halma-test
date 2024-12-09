package ca.uqam.info.solanum.inf2050.f24halma.model;

import java.util.Set;

/**
 * Read only representation of model. Beyond the controller the model should only be accessible
 * using this interface. It must not be possible to change any state of the model instance behind
 * this interface using any of the methods offered by this interface.
 *
 * @author Maximilian Schiedermeier
 */
public interface ModelReadOnly {

  @Override
  String toString();

  /**
   * Returns the plain string names of all players in order participating in this model instance.
   *
   * @return copy of player string names in order.
   */
  String[] getPlayerNames();

  /**
   * Returns all fields currently occupied by the requested player, identified by player index.
   *
   * @param playerIndex as the player for whom the occupied fields are to be identified.
   * @return unmodifiable set of all fields currently occupied by a player.
   */
  Set<Field> getPlayerFields(int playerIndex);

  /**
   * Getter for the index of the current player.
   *
   * @return int representing the index of the current player.
   */
  int getCurrentPlayer();

  /**
   * Returns read only representation of board, including position of all game elements.
   *
   * @return board instance of this model.
   */
  Board getBoard();

  /**
   * Returns true if the specified field is unoccupied.
   *
   * @param field the field to look up. Must be a valid field on the board.
   * @return true if the provided field exists and is unoccupied.
   * @throws FieldException if the provided field is not a valid board position
   */
  boolean isClear(Field field) throws FieldException;
}
