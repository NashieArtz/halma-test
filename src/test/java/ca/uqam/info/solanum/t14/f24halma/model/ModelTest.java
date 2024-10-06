package ca.uqam.info.solanum.t14.f24halma.model;

import ca.uqam.info.solanum.inf2050.f24halma.model.AbstractModelTest;
import ca.uqam.info.solanum.inf2050.f24halma.model.Model;

public class ModelTest extends AbstractModelTest {
    private Model model;  // L'objet Model

    /**
     * Must return a valid model object. Board layout and players do not matter.
     *
     * @return a model object.
     */
    @Override
    public Model getModel() {
        String[] players = {"Alice", "Bob"}; // Assurez-vous d'avoir un tableau de noms de joueurs valide.
        return new ModelImpl(3, players); // 3 est la taille de base, peut être modifié selon le besoin.
    }
}
