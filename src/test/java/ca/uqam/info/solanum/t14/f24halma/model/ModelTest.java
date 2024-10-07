package ca.uqam.info.solanum.t14.f24halma.model;

import ca.uqam.info.solanum.inf2050.f24halma.model.AbstractModelTest;
import ca.uqam.info.solanum.inf2050.f24halma.model.Model;

/**
 * Classe de test pour le modèle de jeu.
 *
 * Cette classe étend AbstractModelTest et fournit un modèle valide pour les tests.
 */
public class ModelTest extends AbstractModelTest {
    private Model model;  // Initialisation d'instance

    /**
     * Methode heritee
     *
     * Cette méthode doit retourner un objet modèle valide. La configuration du plateau et des joueurs n'a pas
     * d'importance pour ce test.
     *
     * @return un nouvel objet ModelImpl.
     */
    @Override
    public Model getModel() {
        String[] players = {"Alice", "Bob"}; // Tableau des noms de joueur
        return new ModelImpl(3, players); // nouveau ModelImpl
    }
}

