package ca.uqam.info.solanum.inf2050.f24halma.model;

import java.util.Arrays;
import java.util.Set;
import junit.framework.Assert;
import org.junit.Test;


public abstract class AbstractModelTest {

  /**
   * Must return a valid model object. Board layout and players do not matter.
   *
   * @return a model object.
   */
  public abstract Model getModel();

  /**
   * Verifies the player names cannot be modified using the getter.
   */
  @Test
  public void testPlayersUnmodifiable() {

    ModelReadOnly model = getModel();
    String[] playerNames = model.getPlayerNames();
    playerNames[0] = "-";
    String[] unmodifiedPlayerNames = model.getPlayerNames();
    Assert.assertFalse(Arrays.equals(playerNames, unmodifiedPlayerNames));
  }

  /**
   * Tests if two separate model objects with identical state are recognized as equal.
   */
  @Test
  public void testModelEquals() {

    // Check if semantically equivalent model objects are considered equal.
    ModelReadOnly model1 = getModel();
    ModelReadOnly model2 = getModel();
    Assert.assertEquals("Model objects should be equal", model1, model2);
  }
}
