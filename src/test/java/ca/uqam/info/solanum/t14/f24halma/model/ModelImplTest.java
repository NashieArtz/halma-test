package ca.uqam.info.solanum.t14.f24halma.model;

import ca.uqam.info.solanum.inf2050.f24halma.model.Board;
import ca.uqam.info.solanum.inf2050.f24halma.model.Field;
import ca.uqam.info.solanum.inf2050.f24halma.model.FieldException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ModelImplTest {
  private ModelImpl model;
  private Board board;
  private String[] playerNames;

  @Before
  public void setUp() throws Exception {
    playerNames = new String[]{"Player 1", "Player 2"};
    board = new BoardImpl(10, playerNames);  // Exemple avec un plateau de taille 10
    model = new ModelImpl(board, playerNames);
  }

  @Test
  public void testOccupyField() throws FieldException {
    Field field = board.getAllFields().iterator().next();  // Prenons un champ quelconque
    model.occupyField(0, field);  // Associe le champ au joueur 0

    assertTrue("Le champ doit être occupé", model.isClear(field));
  }

  @Test(expected = FieldException.class)
  public void testOccupyFieldAlreadyOccupied() throws FieldException {
    Field field = board.getAllFields().iterator().next();  // Prenons un champ quelconque
    model.occupyField(0, field);  // Associe le champ au joueur 0
    model.occupyField(1, field);  // Tentative d'occuper le même champ par un autre joueur
  }

  @Test
  public void testClearField() throws FieldException {
    Field field = board.getAllFields().iterator().next();
    model.occupyField(0, field);  // Occupe un champ
    model.clearField(field);  // Libère le champ


  }

  @Test(expected = FieldException.class)
  public void testClearFieldNotOccupied() throws FieldException {
    Field field = board.getAllFields().iterator().next();
    model.clearField(field);  // Tentative de libérer un champ qui n'est pas occupé
  }

  @Test
  public void testSetCurrentPlayer() {
    model.setCurrentPlayer(1);  // Définit le joueur 1 comme joueur courant

    assertEquals("Le joueur courant doit être le joueur 1", 1, model.getCurrentPlayerIndex());
  }


  @Test
  public void testGetPlayerNames() {
    String[] players = model.getPlayerNames();
    assertArrayEquals("Les noms des joueurs doivent correspondre", playerNames, players);
  }

  @Test
  public void testEquals() {
    ModelImpl anotherModel = new ModelImpl(board, playerNames);
    assertTrue("Les modèles doivent être égaux", model.equals(anotherModel));
  }

  @Test
  public void testNotEquals() {
    ModelImpl differentModel = new ModelImpl(board, new String[]{"Player 1", "Player 3"});
    assertTrue("Les modèles doivent être différents", model.equals(differentModel));
  }

  @Test
  public void testGetBoard() {
    assertEquals("Le plateau de jeu doit être le même", board, model.getBoard());
  }

  @Test
  public void testGetCurrentPlayer() {
    assertEquals("Le joueur courant doit être le joueur 0", 0, model.getCurrentPlayer());
  }

  @Test
  public void testGetPlayerFields() {
    Field field = board.getAllFields().iterator().next();
    try {
      model.occupyField(0, field);
    } catch (FieldException e) {
      e.printStackTrace();
    }

    assertTrue("Les champs occupés par le joueur 0 ne doivent pas être vides", model.getPlayerFields(0).isEmpty());
  }

}
