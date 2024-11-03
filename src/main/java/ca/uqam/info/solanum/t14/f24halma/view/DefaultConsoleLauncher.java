package ca.uqam.info.solanum.t14.f24halma.view;

import ca.uqam.info.solanum.inf2050.f24halma.model.Board;
import ca.uqam.info.solanum.t14.f24halma.model.ModelImpl;
import ca.uqam.info.solanum.t14.f24halma.model.BoardImpl;
import ca.uqam.info.solanum.inf2050.f24halma.controller.Controller;
import ca.uqam.info.solanum.inf2050.f24halma.model.ModelReadOnly;
import ca.uqam.info.solanum.inf2050.f24halma.controller.ModelFactory;
import ca.uqam.info.solanum.inf2050.f24halma.view.TextualVisualizer;
import ca.uqam.info.solanum.inf2050.f24halma.view.MoveSelector;
import ca.uqam.info.solanum.inf2050.f24halma.view.InteractiveMoveSelector;
import ca.uqam.info.solanum.inf2050.f24halma.controller.Move;

// TODO: Add your imports for controller implementation and factory here.
import ca.uqam.info.solanum.t14.f24halma.controller.ControllerImpl;
import ca.uqam.info.solanum.t14.f24halma.controller.SquareModelFactory;
import java.util.Arrays;
import java.util.List;


/**
 * Sample console launcher, to start TP code.
 */
public class DefaultConsoleLauncher {
    static int baseSize;
    static String[] players;

    /**
     * Default Constructor.
     */
    public DefaultConsoleLauncher(int baseSize, String player1, String player2) {
        DefaultConsoleLauncher.baseSize = baseSize;
        players = new String[2];
        players[0] = player1;
        players[1] = player2;
        new BoardImpl(baseSize, players);
        new ModelImpl(baseSize, players);
    }

    public DefaultConsoleLauncher(int baseSize, String player1, String player2, String player3, String player4) {
        DefaultConsoleLauncher.baseSize = baseSize;
        players = new String[4];
        players[0] = player1;
        players[1] = player2;
        players[2] = player3;
        players[3] = player4;
        new BoardImpl(baseSize, players);
    }

    /**
     * Main class for the console launcher.
     *
     * @param args no arguments required.
     */
    public static void main(String[] args) {
        DefaultConsoleLauncher launcher = new DefaultConsoleLauncher(3, "Max", "Ryan");

        //    runTp01();
        runTp02(players);
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

    private static void runTp02(String[] players) {
        // Initialiser `baseSize` et `playerNames`
        String[] playerNames = Arrays.copyOfRange(players, 0, players.length);
        ModelFactory modelFactory = new SquareModelFactory();

        // Créer le modèle et vérifier qu'il n'est pas nul
        ModelReadOnly model = modelFactory.createModel(baseSize, playerNames);
        if (model == null) {
            throw new IllegalStateException("Le modèle créé par ModelFactory est nul.");
        }

        // Créer le contrôleur avec le modèle directement
        Controller controller = new ControllerImpl(model);
        if (controller.getModel() == null) {
            throw new IllegalStateException("Le modèle n'a pas été transmis correctement au contrôleur.");
        }
        TextualVisualizer visualizer = new TextualVisualizer(true);

        // Reste du code pour exécuter le jeu
        while (!controller.isGameOver()) {
            printAndRequestAndPerformAction(controller, visualizer, playerNamesToMoveSelectors(playerNames));
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

        ModelReadOnly model = controller.getModel();
        if (model == null) {
            throw new IllegalStateException("Le modèle du contrôleur est nul.");
        }

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



        // Continue with visualizations and moves
        System.out.println(visualizer.stringifyModel(model));

        // Perform selected action:
        controller.performMove(selectedMove);
        System.out.println("\n\n");
    }
}