package ca.uqam.info.solanum.inf2050.f24halma.controller;

import ca.uqam.info.solanum.inf2050.f24halma.model.ModelReadOnly;
import java.util.List;

/**
 * Read only interface for model. Use this interface to safely pass concealed model instances
 * outside the controller, e.g. to a View subscribing to model state updates for visualization
 * purposes.
 *
 * @author Maximilian Schiedermeier
 */
public interface Controller {

  /**
   * Read only access to current version of model. Can be used e.g. by UI, to visualize state.
   * Cannot be used to directly modify state. All state modification must pass through other
   * controller methods.
   *
   * @return the immutable model.
   */
  ModelReadOnly getModel();

  /**
   * Generator for all valid actions of the current player. Each action object represents an atomic
   * move, that is, further actions by the same player may be possible. If the previous move was a
   * jump (figure places across another figure), only moves for the same figure can be performed,
   * ,the inverse jum (returning to jump origin) is not contained in the result set. If at least one
   * consecutive jump is possible, a null move (with original equal to target) must be contained in
   * the result list.
   *
   * @return list of action objects, each representing an atomic figure move.
   */
  List<Move> getPlayerMoves();

  /**
   * Modifies model state according to the action index of a given player's actions.
   *
   * @param move as the action to perform, i.e. the details of how the model state must be changed.
   */
  void performMove(Move move);

  /**
   * Tells whether the game is over.
   *
   * @return true when at least one player has all their figures on their target fields.
   */
  boolean isGameOver();

}
