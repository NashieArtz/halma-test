package ca.uqam.info.solanum.inf2050.f24halma.model;

/**
 * Model interface that must be implemented by actual model implementation. This is the version with
 * full write access. To respect MVC, instances of this model must not be passed unprotected beyond
 * the controller. The model associates an immutable Board (only the layout) with player
 * information, i.e. how many players are involved in the session, their names, and where their
 * units stand on the board. In addition to the listed interface methods, implementations of this
 * class must provide an equals method.
 *
 * @author Maximilian Schiedermeier
 */
public interface Model extends ModelReadOnly {

  /**
   * Occupies the given target field to the given player, as specified by their id.
   *
   * @param playerIndex as the index of the player who will occupy the field. Must be in valid
   *                    range, i.e. not below 0 and not exceeding player indexes.
   * @param field       as the field instance to assign to the player.
   * @throws FieldException in case the specified field is already occupied.
   */
  void occupyField(int playerIndex, Field field) throws FieldException;


  /**
   * Clears a given field from any potential player occupation.
   *
   * @param field as the target field to clear from any potential occupation.
   * @throws FieldException in case the specified field is not currently occupied.
   */
  void clearField(Field field) throws FieldException;

  /**
   * Changes the pointer to the current player.
   *
   * @param playerIndex as the index of the player who will be new current player. Must be in valid
   *                    range, i.e. not below 0 and not exceed player indexes.
   */
  void setCurrentPlayer(int playerIndex);


}
