package ca.uqam.info.solanum.inf2050.f24halma.controller;


import ca.uqam.info.solanum.inf2050.f24halma.model.Model;
import ca.uqam.info.solanum.inf2050.f24halma.model.ModelReadOnly;
import junit.framework.Assert;
import org.junit.Test;

/**
 * These are tests to verify the implemented square model factory works correctly. Test classes
 * create test models with various input parameters and verify if the created model corresponds to
 * the requested layout. This abstract test must be implemented by a custom test class. The only
 * requirement is implementation of the getModelFactory method, to allow this class to test against
 * the interface rather than ModelFactory implementations. (No constructor call is required, as
 * instances are available over the extended getField implementation.)
 *
 * @author Maximilian Schiedermeier
 */
public abstract class AbstractSquareModelFactoryTest {

  /**
   * Helper method to obtain a ModelFactory instance without knowledge of the concrete field class
   * implementation.
   *
   * @return a factory abject capable of initializing halma models.
   */
  public abstract ModelFactory getModelFactory();

  /**
   * Test whether it is possible to create a 3x3 board.
   */
  @Test
  public void testThreeByThreeBoardCreation() {


    String[] players = new String[] {"Hafedh", "Quentin"};
    ModelReadOnly testModel = getModelFactory().createModel(3, players);
    Assert.assertNotNull("Factory most not create a null object", testModel);
    Assert.assertEquals("", players[0], testModel.getPlayerNames()[0]);
    Assert.assertEquals("", players[1], testModel.getPlayerNames()[1]);
    Assert.assertEquals("", players.length, testModel.getPlayerNames().length);
    Assert.assertEquals("Initial player must be index 0", 0, testModel.getCurrentPlayer());
  }



  /**
   * Tests whether the amount of base fields for a sample squared board correponds to what was
   * requested from model factory.
   */
  @Test
  public void verifyPlayerBases() {
    int baseSize = 2;

    String[] players = new String[] {"Alice", "Bob"};
    Model model = getModelFactory().createModel(baseSize, players);
    for (int i = 0; i < players.length; i++) {

      Assert.assertEquals("Incorrect number of base fields for player", 3,
          model.getBoard().getHomeFieldsForPlayer(i).size());
    }
  }
}

