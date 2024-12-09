package ca.uqam.info.solanum.inf2050.f24halma.controller;

import static ca.uqam.info.solanum.inf2050.f24halma.controller.Move.deserializeMoveList;
import static ca.uqam.info.solanum.inf2050.f24halma.model.Field.deserializeSetOfFields;

import ca.uqam.info.solanum.inf2050.f24halma.model.Field;
import ca.uqam.info.solanum.inf2050.f24halma.model.FieldException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Reusable generic tests for Square Controller.
 *
 * @author Maximilian Schiedermeier
 */
public abstract class AbstractSquareControllerTest {
  public abstract Controller getSquareController(int baseSize, String[] players);

  /**
   * Test if moves for blank test game are correctly produced.
   */
  @Test
  public void testGetMovesNewGame() {
    Controller controller = getSquareController(2, new String[] {"Max", "Hafedh"});
    // At start Max must have 6 valid moves and hafedh none.
    List<Move> moves = controller.getPlayerMoves();
    Assert.assertEquals(9, moves.size());
  }
  // ADVANCED

  /**
   * Access on in existent board field must throw exception.
   */
  @Test(expected = FieldException.class)
  public void testRejectInvalidClearQuery() {
    Controller controller = getSquareController(2, new String[] {"Max", "Hafedh"});
    controller.getModel().isClear(new Field(-1, -1));
  }
  // ADVANCED

  /**
   * Verify that home zones go unchanged by move.
   */
  @Test
  public void testHomeZonesUnchanged() {
    Controller controller = getSquareController(2, new String[] {"Max", "Hafedh"});
    Set<Field> originalHomeZones = controller.getModel().getBoard().getAllHomeFields();
    controller.performMove(new Move(new Field(0, 0), new Field(0, 2), true));
    Assert.assertEquals("Home-Zones must not evolve during gameplay.", originalHomeZones,
        controller.getModel().getBoard().getAllHomeFields());
  }
  // TP2

  /**
   * Helper method to compare a list of controller-generated solutions to a serialized list of
   * expected moves.
   */
  private void assertOfferedMovesEquals(String expectedMovesSerialized, Controller controller) {
    List<Move> expectedMoves = deserializeMoveList(expectedMovesSerialized);
    // Compare to actual offered list of moves:
    List<Move> offeredMoves = controller.getPlayerMoves();
    // Make deep copy before test
    offeredMoves = Move.deserializeMoveList(offeredMoves.toString());
    Collections.sort(offeredMoves);
    org.junit.Assert.assertEquals(
        "Controller returned list of player moves differs from expected result.", expectedMoves,
        offeredMoves);
  }

  private void assertPlayerPositionsEquals(String expectedPositionsSerialized,
                                           int preMovePlayerIndex, Controller controller) {
    Set<Field> expectedPositionsSet = deserializeSetOfFields(expectedPositionsSerialized);
    Set<Field> positionsAfterMove = controller.getModel().getPlayerFields(preMovePlayerIndex);
    org.junit.Assert.assertEquals("Player figures are not at expected positions after move.",
        expectedPositionsSet, positionsAfterMove);
  }

  /**
   * Creates a square halma baord with zone size 2, verifies initial state, attempts a simple move
   * and verifies resulting state again.
   */
  @Test
  public void testSimpleMove() {
    Controller squareController = getSquareController(2, new String[] {"Alice", "Bob"});
    // Construct the expected list of moves
    String expectedMovesSerialized =
        "[(00,00) -> (01,01), (00,00) => (00,02), (00,00) => (02,00), (00,01) -> (01,01), (00,01)"
            + " -> (01,02), (00,01) -> (00,02), (01,00) -> (01,01), (01,00) -> (02,00), (01,00) "
            + "-> (02,01)]";
    assertOfferedMovesEquals(expectedMovesSerialized, squareController);
    // Make a simple move (not a jump)
    Move simpleMove = new Move(new Field(0, 0), new Field(1, 1), false);
    String expectedSerializedFieldsSet = "[(01,01), (00,01), (01,00)]";
    String expectedFollowUpMovesSerialized =
        "[(05,05) -> (04,04), (05,05) => (03,05), (05,05) => (05,03), (04,05) -> (04,04), (04,05)"
            + " -> (03,04), (04,05) -> (03,05), (05,04) -> (04,04), (05,04) -> (05,03), (05,04) "
            + "-> (04,03)]";
    makeMoveAndAssertOutcome(simpleMove, squareController, expectedSerializedFieldsSet,
        expectedFollowUpMovesSerialized);
  }

  /**
   * Creates a square halma baord with zone size 2, verifies initial state, attempts a simple jump
   * move and verifies resulting state again.
   */
  @Test
  public void testJumpMove() {
    Controller squareController = getSquareController(2, new String[] {"Alice", "Bob"});
    // Construct the expected list of moves
    String expectedMovesSerialized =
        "[(00,00) -> (01,01), (00,00) => (00,02), (00,00) => (02,00), (00,01) -> (01,01), (00,01)"
            + " -> (01,02), (00,01) -> (00,02), (01,00) -> (01,01), (01,00) -> (02,00), (01,00) "
            + "-> (02,01)]";
    assertOfferedMovesEquals(expectedMovesSerialized, squareController);
    // Make a simple move (not a jump)
    Move jumpMove = new Move(new Field(0, 0), new Field(2, 0), true);
    String expectedSerializedFieldsSet = "[(02,00), (00,01), (01,00)]";
    makeMoveAndAssertOutcome(jumpMove, squareController, expectedSerializedFieldsSet,
        "[(05,04) -> (04,03), (05,04) -> (05,03), (05,04) -> (04,04), (04,05) -> (03,04), (04,05)"
            + " -> (04,04), (04,05) -> (03,05), (05,05) => (05,03), (05,05) -> (04,04), (05,05) "
            + "=> (03,05)]]");
  }

  /**
   * Programmed series of player moves that ends a simple square game (base size 1) without jumps.
   */
  @Test
  public void testJumplessGameEnd() {
    Controller squareController = getSquareController(1, new String[] {"Alice", "Bob"});
    // Player 0 moves diagonally in direction of target
    Move move01 = new Move(new Field(0, 0), new Field(1, 1), false);
    // There's only one figure so the expected figure positions are simply the one field th
    // player moved to.
    makeMoveAndAssertOutcome(move01, squareController, "[(01,01)]",
        "[(02,02) => (00,00), (02,02) -> (02,01), (02,02) -> (01,02)]");
    // Player 1 frees home zone
    Move move02 = new Move(new Field(2, 2), new Field(2, 1), false);
    makeMoveAndAssertOutcome(move02, squareController, "[(02,01)]",
        "[(01,01) -> (00,00), (01,01) -> (01,00), (01,01) -> (02,00), (01,01) -> (00,01), (01,01)"
            + " -> (00,02), (01,01) -> (01,02), (01,01) -> (02,02)]");
    // Player 0 reaches target by moving diagonally again. -> Game over.
    Move move03 = new Move(new Field(1, 1), new Field(2, 2), false);
    makeMoveAndAssertOutcome(move03, squareController, "[(02,02)]",
        "[(02,01) -> (01,00), (02,01) -> (02,00), (02,01) -> (01,01), (02,01) -> (01,02)]");
    // Verify if game over detected
    org.junit.Assert.assertTrue(
        "Game should be identified as over, but controller says it is still going on.",
        squareController.isGameOver());
  }

  /**
   * Programmed series of player moves that ends a simple square game (base size 1) with jumps.
   */
  @Test
  public void testJumpGameEnd() {
    Controller squareController = getSquareController(1, new String[] {"Alice", "Bob"});
    // Player 0 moves diagonally in direction of target
    Move move01 = new Move(new Field(0, 0), new Field(1, 1), false);
    // There's only one figure so the expected figure positions are simply the one field th
    // player moved to.
    makeMoveAndAssertOutcome(move01, squareController, "[(01,01)]",
        "[(02,02) => (00,00), (02,02) -> (02,01), (02,02) -> (01,02)]");
    // Player 1 jumps across player 0, to reach target zone.
    Move move02 = new Move(new Field(2, 2), new Field(0, 0), true);
    makeMoveAndAssertOutcome(move02, squareController, "[(00,00)]",
        "[(01,01) -> (01,00), (01,01) -> (02,00), (01,01) -> (00,01),"
            + " (01,01) -> (02,01), (01,01) -> (00,02), (01,01) -> (01,02), (01,01) -> (02,02)]");
    // Verify if game over detected
    org.junit.Assert.assertTrue(
        "Game should be identified as over, but controller says it is still going on.",
        squareController.isGameOver());
  }

  /**
   * Verifies if the controller rejects incorrect standard moves (invalid target).
   */
  @Test(expected = IllegalMoveException.class)
  public void testIllegalMoveRejected() {
    Controller controller = getSquareController(2, new String[] {"Alice", "Bob"});
    Move illegalMove = new Move(new Field(0, 0), new Field(3, 3), false);
    controller.performMove(illegalMove);
  }

  /**
   * Verifies if the controller rejects incorrect jump moves (not labeled as jump).
   */
  @Test(expected = IllegalMoveException.class)
  public void testIllegalJumpRejected() {
    Controller controller = getSquareController(2, new String[] {"Alice", "Bob"});
    Move illegalMove = new Move(new Field(0, 0), new Field(2, 2), false);
    controller.performMove(illegalMove);
  }

  /**
   * Verifies if the controller rejects incorrect jump moves (illegal target).
   */
  @Test(expected = IllegalMoveException.class)
  public void testIllegalJumpTargetRejected() {
    Controller controller = getSquareController(2, new String[] {"Alice", "Bob"});
    Move illegalMove = new Move(new Field(0, 0), new Field(3, 3), true);
    controller.performMove(illegalMove);
  }

  /**
   * Verifies if the controller rejects moving outside of game board.
   */
  @Test(expected = IllegalMoveException.class)
  public void testIllegalNegativeMove() {
    Controller controller = getSquareController(2, new String[] {"Alice", "Bob"});
    Move illegalMove = new Move(new Field(0, 0), new Field(0, -1), false);
    controller.performMove(illegalMove);
  }

  /**
   * Creates a square halma baord with zone size 2, verifies initial state, attempts a simple jump
   * move and verifies resulting state again.
   */
  @Test
  public void testJumpSeries() {
    Controller controller = getSquareController(2, new String[] {"Alice", "Bob"});
    // Construct the expected list of moves
    String expectedMovesSerialized =
        "[(00,00) -> (01,01), (00,00) => (00,02), (00,00) => (02,00), (00,01) -> (01,01), (00,01)"
            + " -> (01,02), (00,01) -> (00,02), (01,00) -> (01,01), (01,00) -> (02,00), (01,00) "
            + "-> (02,01)]";
    assertOfferedMovesEquals(expectedMovesSerialized, controller);
    // Make a simple move preparation for later jumps
    controller.performMove(new Move(new Field(0, 1), new Field(1, 1), false));
    // Some benign move for other player
    controller.performMove(controller.getPlayerMoves().iterator().next());
    // Make a simple move preparation for later jumps
    controller.performMove(new Move(new Field(1, 1), new Field(2, 1), false));
    // Some benign move for other player
    controller.performMove(controller.getPlayerMoves().iterator().next());
    System.out.println(controller.getPlayerMoves());
    // Verify expected list of possible moves:
    String expectedMovesSerialized2 =
        "[(00,00) -> (01,01), (00,00) -> (00,01), (00,00) => (02,00), (01,00) -> (01,01), (01,00)"
            + " -> (00,01), (01,00) -> (02,00), (01,00) => (03,02), (02,01) -> (01,01), (02,01) "
            + "-> (02,02), (02,01) -> (01,02), (02,01) -> (03,00), (02,01) -> (02,00), (02,01) ->"
            + " (03,01), (02,01) -> (03,02)]";
    assertOfferedMovesEquals(expectedMovesSerialized2, controller);
    // Do the first jump:
    Move firstJumpMove = new Move(new Field(0, 0), new Field(2, 0), true);
    String expectedFieldsAfterFirstJump = "[(02,00), (01,00), (02,01)]";
    makeMoveAndAssertOutcome(firstJumpMove, controller, expectedFieldsAfterFirstJump,
        "[(02,00) == (02,00), (02,00) => (02,02)]");
    // Do the second jump
    Move secondJumpMove = new Move(new Field(2, 0), new Field(2, 2), true);
    String expectedFieldsAfterSecondJump = "[(02,02), (01,00), (02,01)]";
    makeMoveAndAssertOutcome(secondJumpMove, controller, expectedFieldsAfterSecondJump,
        "[(02,02) == (02,02), (02,02) => (04,04)]");
    // Assert that after this jump the game has moved on to next player
    // Or should I also accept a list with only NULL jump ??
  }

  /**
   * Check larger board layout and list of allowed moves.
   */
  @Test
  public void testThreeByThreeMoves() {
    Controller controller = getSquareController(3, new String[] {"Alice", "Bob"});
    String expectedMovesSerialized =
        "[(00,00) => (02,02), (01,01) -> (02,02), (01,01) -> (01,02), (01,01) -> (02,01), (00,01)"
            + " => (02,01), (00,01) -> (01,02), (00,01) => (00,03), (00,02) -> (01,02), (00,02) "
            + "-> (01,03), (00,02) -> (00,03), (02,00) -> (03,00), (02,00) -> (03,01), (02,00) ->"
            + " (02,01), (01,00) => (01,02), (01,00) => (03,00), (01,00) -> (02,01)]";
    assertOfferedMovesEquals(expectedMovesSerialized, controller);
  }

  @Test
  public void testAntiJumpRejected() {
    Controller controller = getSquareController(2, new String[] {"Alice", "Bob"});
    Move initialJump = Move.deserializeMove("(00,00)=>(02,00)");
    Move antiJump = Move.deserializeMove("(02,00)=>(00,00)");
    // After initial jump, anti jump must not be allowed (no implicit forward to next player,
    // list contains only null-jump)
    controller.performMove(initialJump);
    org.junit.Assert.assertFalse(
        "Anti jump must not be contained in move list, after jumps has been performed.",
        controller.getPlayerMoves().contains(antiJump));
  }

  private void makeMoveAndAssertOutcome(Move move, Controller controller,
                                        String expectedSerializedFieldsSet,
                                        String expectedFollowupMoveOptionsSerialized) {
    // Index of current player, before move is made.
    int playerIndexPreMove = controller.getModel().getCurrentPlayer();
    // Request doing the given move
    int expectedFollowUpPlayer = analyzeMoveForPlayerMoveOn(playerIndexPreMove, move, controller);
    controller.performMove(move);
    // Automatically forward to next player if only one action possible
    if (controller.getPlayerMoves().size() == 1) {
      controller.performMove(controller.getPlayerMoves().getFirst());
      expectedFollowUpPlayer =
          (expectedFollowUpPlayer + 1) % controller.getModel().getPlayerNames().length;
    }
    // Verify if resulting player is correct
    assertCurrentPlayerEquals(expectedFollowUpPlayer, controller);
    // Verify if resulting figure positions are correct
    assertPlayerPositionsEquals(expectedSerializedFieldsSet, playerIndexPreMove, controller);
    // Verify if options for follow-up move are correct
    List<Move> followUpMovesExpected =
        Move.deserializeMoveList(expectedFollowupMoveOptionsSerialized);
    List<Move> followUpMovesComputedByController = controller.getPlayerMoves();
    // Potentially an immutable object... I create a deep copy before I sort
    followUpMovesComputedByController =
        Move.deserializeMoveList(followUpMovesComputedByController.toString());
    org.junit.Assert.assertEquals("Controller provided options for follow-up moves incorrect.",
        followUpMovesExpected, followUpMovesComputedByController);
  }

  /**
   * Analyzes a provided move and returns the expected next player.
   *
   * @param move       as the move to be executed
   * @param controller as the controller to access the halma game instance
   * @return index of either next player or current player, depending on move nature.
   */
  private int analyzeMoveForPlayerMoveOn(int playerIndexPreMove, Move move, Controller controller) {
    int nextPlayerInOrder =
        (playerIndexPreMove + 1) % controller.getModel().getPlayerNames().length;
    // If not a jump, next player is next in order
    if (!move.jump()) {
      return nextPlayerInOrder;
    }
    // If a null jump, next player is next in order
    if (move.jump() && (move.origin().equals(move.target()))) {
      return nextPlayerInOrder;
    }
    // Otherwise it was a jump the player remains the same
    return controller.getModel().getCurrentPlayer();
  }

  /**
   * Verifies if current player matches the expected index.
   *
   * @param expectedPlayer   as the expected player index.
   * @param squareController as the controller returning the current player.
   */
  private void assertCurrentPlayerEquals(int expectedPlayer, Controller squareController) {
    // Verify outcome (must be turn of next player)
    int currentPlayer = squareController.getModel().getCurrentPlayer();
    org.junit.Assert.assertEquals("Game did not pass on to next player after last possible move decision.",
        expectedPlayer, currentPlayer);
  }
}
