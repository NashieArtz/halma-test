package ca.uqam.info.solanum.t14.f24halma.integration;

import ca.uqam.info.solanum.inf2050.f24halma.controller.Controller;
import ca.uqam.info.solanum.t14.f24halma.controller.ControllerImpl;
import ca.uqam.info.solanum.t14.f24halma.model.ModelImpl;
import ca.uqam.info.solanum.t14.f24halma.view.MadMaxMoveSelector;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class MadMaxIntegrationTest {

  @Test
  public void testMadMaxVsMadMax() {
    // Initialisation des joueurs et de la configuration
    String[] players = {"AI1", "AI2"};
    int baseSize = 3;

    // Création du modèle
    ModelImpl model = new ModelImpl(baseSize, players);

    // Création du contrôleur
    Controller controller = new ControllerImpl(model);

    // Configuration des IA pour chaque joueur
    MadMaxMoveSelector ai1 = new MadMaxMoveSelector();
    MadMaxMoveSelector ai2 = new MadMaxMoveSelector();

    // Simulez une partie complète
    while (!controller.isGameOver()) {
      int currentPlayer = controller.getModel().getCurrentPlayer();
      MadMaxMoveSelector currentAI = (currentPlayer == 0) ? ai1 : ai2;

      // Sélection du mouvement par l'IA
      var moves = controller.getPlayerMoves();
      var selectedMove = currentAI.selectMove(moves);

      // Exécution du mouvement
      controller.performMove(selectedMove);
    }

    // Vérifiez que le jeu s'est terminé
    assertTrue("Le jeu devrait être terminé.", controller.isGameOver());
  }
}
