package ca.uqam.info.solanum.t14.f24halma.view;


import ca.uqam.info.solanum.inf2050.f24halma.controller.Controller;
import ca.uqam.info.solanum.inf2050.f24halma.controller.ModelFactory;
import ca.uqam.info.solanum.inf2050.f24halma.controller.Move;
import ca.uqam.info.solanum.inf2050.f24halma.model.ModelInitializationException;
import ca.uqam.info.solanum.inf2050.f24halma.model.ModelReadOnly;
import ca.uqam.info.solanum.inf2050.f24halma.view.InteractiveMoveSelector;
import ca.uqam.info.solanum.inf2050.f24halma.view.MoveSelector;
import ca.uqam.info.solanum.inf2050.f24halma.view.TextualVisualizer;
import ca.uqam.info.solanum.t14.f24halma.ai.MadMaxMoveSelector;
import ca.uqam.info.solanum.t14.f24halma.controller.ControllerImpl;
import ca.uqam.info.solanum.t14.f24halma.controller.SquareModelFactory;
import ca.uqam.info.solanum.t14.f24halma.model.BoardImpl;
import ca.uqam.info.solanum.t14.f24halma.model.ModelImpl;
import java.util.Arrays;
import java.util.List;


/**
 * Sample console launcher, to start TP code.
 */
public class DefaultConsoleLauncher {
  static int baseSize;
  static String[] players;
  static String player1;
  static String player2;
  static String player3;
  static String player4;

  /**
   * Constructeur de la classe {@code DefaultConsoleLauncher}.
   * Ce constructeur initialise la taille de la base du jeu et les noms des joueurs,
   * puis crée une instance du plateau de jeu {@code BoardImpl} et du modèle de jeu
   * {@code ModelImpl} avec les paramètres fournis.
   *
   * @param baseSize La taille de la zone de base pour chaque joueur. Cette taille
   *                 détermine la dimension du plateau de jeu.
   * @param player1  Le nom du premier joueur.
   * @param player2  Le nom du deuxième joueur.
   */
  public DefaultConsoleLauncher(int baseSize, String player1, String player2) {
    DefaultConsoleLauncher.baseSize = baseSize;
    players = new String[2];
    players[0] = player1;
    players[1] = player2;
    new BoardImpl(baseSize, players);
    new ModelImpl(baseSize, players);
  }
  /**
   * Constructeur pour la classe DefaultConsoleLauncher.
   * Initialise la taille de base du plateau et les noms des joueurs.
   * Ce constructeur crée également les instances de BoardImpl et ModelImpl
   * en utilisant la taille de base et les noms des joueurs fournis.
   *
   * @param baseSize la taille de base du plateau de jeu.
   * @param player1  le nom du premier joueur.
   * @param player2  le nom du deuxième joueur.
   * @param player3  le nom du troisième joueur.
   * @param player4  le nom du quatrième joueur.
   */

  public DefaultConsoleLauncher(int baseSize, String player1, String player2,
                                String player3, String player4) {
    DefaultConsoleLauncher.baseSize = baseSize;
    players = new String[4];
    players[0] = player1;
    players[1] = player2;
    players[2] = player3;
    players[3] = player4;
    new BoardImpl(baseSize, players);
    new ModelImpl(baseSize, players);
  }

  /**
   * Main class for the console launcher.
   *
   * @param args no arguments required.
   */
  public static void main(String[] args) {
    if (args.length < 3) {
      System.out.println("Commande: \"baseSize\" \"player1\" \"player2\" \"player3\" \"player4\"");
      return;
    }

    baseSize = Integer.parseInt(args[0]);
    players = new String[args.length - 1];
    for (int i = 1; i < args.length; i++) {
      players[i - 1] = args[i];
    }

    if (players.length == 2) {
      DefaultConsoleLauncher launcher
          = new DefaultConsoleLauncher(baseSize, players[0], players[1]);
    } else if (players.length == 4) {
      DefaultConsoleLauncher launcher
          = new DefaultConsoleLauncher(baseSize, players[0], players[1], players[2],
          players[3]);
    }

    //runTp01();
    //runTp02(players);
    try {
      runTp03(args);
    } catch (Exception e) {
      System.out.println("Erreur : " + e.getMessage());
    }
  }



  private static void runTp01() {
    String[] playerNames = {"Joueur1", "Joueur2"};

    // Create a model (read only access) for the provided game parameters
    ModelFactory modelFactory = new SquareModelFactory(); // TODO: Create this class and import it.
    ModelReadOnly model = modelFactory.createModel(baseSize, playerNames);

    //Visualize initial model state
    boolean useColours = false;
    TextualVisualizer visualizer = new TextualVisualizer(useColours);
    System.out.println(visualizer.stringifyModel(model));
  }

  private static void runTp02(String[] playersNames) {

    int baseSize = 2;
    ModelFactory modelFactory = new SquareModelFactory();

    //Créer le modèle et vérifier qu'il n'est pas nul
    ModelImpl model = (ModelImpl) modelFactory.createModel(baseSize, playersNames);
    if (model == null) {
      throw new IllegalStateException("Le modèle créé par ModelFactory est nul.");
    }

    //Créer le contrôleur avec le modèle directement
    Controller controller = new ControllerImpl(model);
    if (controller.getModel() == null) {
      throw new IllegalStateException("Le modèle n'a pas été transmis correctement au contrôleur.");
    }
    TextualVisualizer visualizer = new TextualVisualizer(true);

    //Reste du code pour exécuter le jeu
    while (!controller.isGameOver()) {
      visualizer.clearScreen();
      System.out.println(visualizer.stringifyModel(controller.getModel()));
      }

    System.out.println("GAME OVER !");
  }


  private static void runTp03(String[] args) {
    //Analyse des paramètres de la ligne de commande
    int baseSize = parseBaseSize(args[0]);
    String[] playerNames = Arrays.copyOfRange(args, 1, args.length);

    //Vérifie le nombre de joueurs
    if (!isValidPlayerCount(playerNames.length)) {
      throw new IllegalArgumentException("Nombre de joueurs invalide. Il doit y avoir 2 ou 4 joueurs.");
    }

    try {
      //Initialisation pour modèles, sélecteurs de mouvements,le contrôleur et le visualiseur
      ModelFactory modelFactory = new SquareModelFactory();
      ModelImpl model = (ModelImpl) modelFactory.createModel(baseSize, playerNames);
      MoveSelector[] moveSelectors = setupMoveSelectors(playerNames);
      Controller controller = new ControllerImpl(model);
      TextualVisualizer visualizer = new TextualVisualizer(true);

      //Exécution du jeu
      while (!controller.isGameOver()) {
        ModelReadOnly modelReadOnly = controller.getModel();
        visualizer.clearScreen();
        System.out.println(visualizer.stringifyModel(modelReadOnly));

        int currentPlayerIndex = modelReadOnly.getCurrentPlayer();
        List<Move> availableMoves = controller.getPlayerMoves();

        if (availableMoves.isEmpty()) {
          System.out.println("Aucun mouvement disponible pour le joueur " + currentPlayerIndex);
          break;
        }

        Move selectedMove = moveSelectors[currentPlayerIndex].selectMove(availableMoves);
        controller.performMove(selectedMove);
      }

      System.out.println("GAME OVER !");
      System.out.println(visualizer.stringifyModel(controller.getModel()));

    } catch (ModelInitializationException e) {
      System.out.println("Erreur lors de l'initialisation du jeu : " + e.getMessage());
    }
  }

  private static int parseBaseSize(String baseSizeArg) {
    try {
      return Integer.parseInt(baseSizeArg);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("La taille de base doit être un entier valide.");
    }
  }

  private static boolean isValidPlayerCount(int playerCount) {
    return playerCount == 2 || playerCount == 4;
  }

  private static MoveSelector[] setupMoveSelectors(String[] playerNames) {
    MoveSelector[] moveSelectors = new MoveSelector[playerNames.length];

    for (int i = 0; i < playerNames.length; i++) {
      if ("AI".equalsIgnoreCase(playerNames[i])) {
        moveSelectors[i] = new MadMaxMoveSelector();
      } else {
        moveSelectors[i] = new InteractiveMoveSelector();
      }
    }
    return moveSelectors;
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

    //Clear the screen (Works only on native ANSI terminals, not in IDE / windows)
    visualizer.clearScreen(); // Comment out this line if you're on windows.

    //Retrieve and visualize model
    System.out.println(visualizer.stringifyModel(controller.getModel()));

    //Print all possible actions:
    System.out.println(visualizer.getCurrentPlayerAnnouncement(controller.getModel()));
    System.out.println(visualizer.announcePossibleMoves(controller.getPlayerMoves()));

    //Request action and visualize choice (for current player)
    int currentPlayer = controller.getModel().getCurrentPlayer();
    List<Move> availableMoves = controller.getPlayerMoves();
    //if more than one move, ask selector
    Move selectedMove = null;
    if (!availableMoves.isEmpty()) {
      if (availableMoves.size() > 1) {
        selectedMove = moveSelectors[currentPlayer].selectMove(availableMoves);
      } else {
        selectedMove = availableMoves.getFirst();
      }
    } else {
      System.out.println("Aucun mouvement disponible pour le joueur actuel.");
    }

    System.out.println(visualizer.getChoseMoveAnnouncement(selectedMove, currentPlayer));



    // Continue with visualizations and moves
    System.out.println(visualizer.stringifyModel(model));

    // Perform selected action:
    controller.performMove(selectedMove);
    System.out.println("\n\n");
  }
}