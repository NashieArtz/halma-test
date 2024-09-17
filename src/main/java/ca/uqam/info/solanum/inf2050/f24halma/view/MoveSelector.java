package ca.uqam.info.solanum.inf2050.f24halma.view;

import ca.uqam.info.solanum.inf2050.f24halma.controller.Move;
import java.util.List;

/**
 * Common interface for Halma AIs and human actor command line interfaces. Whatever implements this
 * class, it can make choices on how to proceed the game.
 *
 * @author Maximilian Schiedermeier
 */
public interface MoveSelector {

  /**
   * Returns a specific one move of the provided list.
   *
   * @param allPossibleMoves as a list of all options to chose from.
   * @return the chosen move, which will be passed to the controller.
   */
  Move selectMove(List<Move> allPossibleMoves);
}
