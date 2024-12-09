package ca.uqam.info.solanum.inf2050.f24halma.view;

import ca.uqam.info.solanum.inf2050.f24halma.controller.Move;
import java.util.List;
import java.util.Scanner;

/**
 * Interactive move selector that uses scanner to request selected option from user.
 */
public class InteractiveMoveSelector implements MoveSelector {

  /**
   * Default constructor.
   */
  public InteractiveMoveSelector() {
  }

  @Override
  public Move selectMove(List<Move> allPossibleMoves) {

    int maxMoveId = allPossibleMoves.size() - 1;
    int userSelectionId = -1;

    // Keep asking for inputs until a valid move selection provided
    while (userSelectionId < 0 || userSelectionId > maxMoveId) {
      System.out.println("Enter move ID: ");
      Scanner scanner = new Scanner(System.in);
      String userInput = scanner.nextLine();

      // try to parse user selection as int.
      try {
        userSelectionId = Integer.parseInt(userInput.trim());
      } catch (NumberFormatException ignored) {
        ;
      }
    }
    return allPossibleMoves.get(userSelectionId);
  }
}
