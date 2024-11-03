package ca.uqam.info.solanum.t14.f24halma.f24halma.view;

import ca.uqam.info.solanum.t14.f24halma.controller.SquareModelFactory;
import ca.uqam.info.solanum.t14.f24halma.model.BoardImpl;
import ca.uqam.info.solanum.t14.f24halma.model.ModelImpl;
import ca.uqam.info.solanum.t14.f24halma.view.InteractiveMoveSelector;
import ca.uqam.info.solanum.t14.f24halma.view.MoveSelector;
import ca.uqam.info.solanum.t14.f24halma.view.TextualVisualizer;

// TODO: Add your imports for controller implementation and factory here.
// import ca.uqam.info.solanum.tXX.halma.controller.ControllerImpl;
// import ca.uqam.info.solanum.tXX.halma.controller.SquareModelFactory;
import java.util.Arrays;
import java.util.List;


/**
 * Sample console launcher, to start TP code.
 */
public class DefaultConsoleLauncher {


  /**
   * Default Constructor.
   */
  public DefaultConsoleLauncher() {

  }


  /**
   * Main class for the console launcher.
   *
   * @param args no arguments required.
   */
  public static void main(String[] args) {

    //    runTp01();
    runTp02(args);
    //    runTp03();
  }


  private static void runTp01() {
    // Set default parameters
    int baseSize = 3;
    String[] playerNames = new String[] {"Max", "Ryan"};

    // Create a model (read only access) for the provided game parameters
    ModelFactory modelFactory = new SquareModelFactory(); // TODO: Create this class and import it.
    ModelReadOnly model = modelFactory.createModel(baseSize, playerNames);

    // Visualize initial model state
    boolean useColours = false;
    TextualVisualizer visualizer = new TextualVisualizer(useColours);
    System.out.println(visualizer.stringifyModel(model));
  }

  private static void runTp02(String[] args) {

    // Parse runtime parameters
    int baseSize = Integer.parseInt(args[0]);
    String[] playerNames = Arrays.copyOfRange(args, 1, args.length);
    ModelFactory modelFactory = new SquareModelFactory();

    // Set move selectors
    MoveSelector[] moveSelectors = playerNamesToMoveSelectors(playerNames);

    // Initialize controller
    Controller controller = new ControllerImpl(modelFactory, baseSize, playerNames);

    // Initialize visualizer
    boolean useColours = true; // Set to false if you're on windows and textual output looks weird.
    TextualVisualizer visualizer = new TextualVisualizer(useColours);

    // Proceed until game end
    while (!controller.isGameOver()) {
      printAndRequestAndPerformAction(controller, visualizer, moveSelectors);
    }

    System.out.println(visualizer.stringifyModel(controller.getModel()));
    System.out.println("GAME OVER!");
  }

  private static void runTp03() {
    // Will be released with TP02 instructions.
  }

  private static MoveSelector[] playerNamesToMoveSelectors(String[] playerNames) {
    MoveSelector[] moveSelectors = new MoveSelector[playerNames.length];

    for (int i = 0; i < moveSelectors.length; i++) {
      moveSelectors[i] = new InteractiveMoveSelector();
    }
    return moveSelectors;
  }


  /**
   * Prints mode, possible moves and requests player interaction.
   */
  private static void printAndRequestAndPerformAction(Controller controller,
                                                      TextualVisualizer visualizer,
                                                      MoveSelector[] moveSelectors) {

    // Clear the screen (Works only on native ANSI terminals, not in IDE / windows)
    visualizer.clearScreen(); // Comment out this line if you're on windows.

    // Retrieve and visualize model
    System.out.println(visualizer.stringifyModel(controller.getModel()));

    // Print all possible actions:
    System.out.println(visualizer.getCurrentPlayerAnnouncement(controller.getModel()));
    System.out.println(visualizer.announcePossibleMoves(controller.getPlayerMoves()));

    // Request action and visualize choice (for current player)
    int currentPlayer = controller.getModel().getCurrentPlayer();
    List<Move> availableMoves = controller.getPlayerMoves();
    // if more than one move, ask selector
    Move selectedMove = null;
    if (availableMoves.size() > 1) {
      selectedMove = moveSelectors[currentPlayer].selectMove(availableMoves);
    } else {
      // If only one move available, directly apply it.
      selectedMove = availableMoves.getFirst();
    }
    System.out.println(visualizer.getChoseMoveAnnouncement(selectedMove, currentPlayer));

    // Perform selected action:
    controller.performMove(selectedMove);
    System.out.println("\n\n");
  }
}