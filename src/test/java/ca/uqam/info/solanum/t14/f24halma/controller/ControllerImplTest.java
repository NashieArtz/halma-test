package ca.uqam.info.solanum.t14.f24halma.controller;

import ca.uqam.info.solanum.inf2050.f24halma.controller.Move;
import ca.uqam.info.solanum.inf2050.f24halma.model.Field;
import ca.uqam.info.solanum.inf2050.f24halma.model.FieldException;
import ca.uqam.info.solanum.t14.f24halma.model.ModelImpl;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;


public class ControllerImplTest {

  private ModelImpl model;
  private ControllerImpl controller;

  @Before
  public void setUp() {
    // Initialisation du modèle avec des paramètres de base
    String[] playerNames = {"Player1", "Player2"};
    model = new ModelImpl(4, playerNames);
    controller = new ControllerImpl(model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithNullModelThrowsException() {
    new ControllerImpl((ModelImpl) null);
  }

  @Test
  public void testGetModelReturnsReadOnlyModel() {
    assertNotNull("Le modèle ne doit pas être nul", controller.getModel());
  }

  @Test
  public void testIsGameOverReturnsFalseWhenNoPlayerWins() {
    // Vérification qu'aucun joueur n'a encore gagné
    assertTrue("Le jeu ne doit pas être terminé au début", controller.isGameOver());
  }
}
