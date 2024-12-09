package ca.uqam.info.solanum.t14.f24halma.integration;

import ca.uqam.info.solanum.inf2050.f24halma.controller.Controller;
import ca.uqam.info.solanum.t14.f24halma.controller.ControllerImpl;
import ca.uqam.info.solanum.t14.f24halma.model.ModelImpl;
import ca.uqam.info.solanum.t14.f24halma.view.MadMaxMoveSelector;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

public class MadMaxIntegrationTest {

    @Test
    public void testMadMaxVsMadMax() {
        // Initialisation des joueurs et de la configuration
        String[] players = {"AI1", "AI2"};
        int baseSize = 3;

        // Création du modèle
        ModelImpl model = new ModelImpl(baseSize, players);
        assertNotNull("Le modèle ne doit pas être nul.", model);

        // Création du contrôleur
        Controller controller = new ControllerImpl(model);
        assertNotNull("Le contrôleur ne doit pas être nul.", controller);

        // Configuration des IA pour chaque joueur
        MadMaxMoveSelector ai1 = new MadMaxMoveSelector();
        MadMaxMoveSelector ai2 = new MadMaxMoveSelector();

        // Simulez une partie complète
        while (!controller.isGameOver()) {
            int currentPlayer = controller.getModel().getCurrentPlayer();
            MadMaxMoveSelector currentAI = (currentPlayer == 0) ? ai1 : ai2;

            // Sélection du mouvement par l'IA
            List moves = controller.getPlayerMoves();
            assertNotNull("La liste des mouvements possibles ne doit pas être nulle.", moves);

            if (!moves.isEmpty()) {
                var selectedMove = currentAI.selectMove(moves);

                // Exécution du mouvement
                controller.performMove(selectedMove);
            }
        }

        // Vérifiez que le jeu s'est terminé
        assertTrue("Le jeu devrait être terminé.", controller.isGameOver());
    }
}
