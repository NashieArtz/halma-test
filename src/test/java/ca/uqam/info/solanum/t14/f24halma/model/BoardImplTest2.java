package ca.uqam.info.solanum.t14.f24halma.model;

import ca.uqam.info.solanum.inf2050.f24halma.model.Field;
import java.util.Set;
import junit.framework.TestCase;

public class BoardImplTest2 extends TestCase {

  private BoardImpl board;
  private String[] players;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    players = new String[]{"Player1", "Player2", "Player3", "Player4"};
    board = new BoardImpl(3, players); // Crée un plateau avec baseSize = 3 et 4 joueurs
  }

  @Override
  protected void tearDown() throws Exception {
    // Nettoyage si nécessaire
    super.tearDown();
  }

  public void testGetAllFields() {
    Set<Field> allFields = board.getAllFields();
    assertNotNull("Les champs du plateau ne doivent pas être nuls", allFields);
    assertEquals("Le nombre de champs du plateau doit être 81", 81, allFields.size()); // Le plateau doit avoir 81 cases
  }

  public void testGetHomeFieldsForPlayer() {
    Set<Field> homeFieldsForPlayer0 = board.getHomeFieldsForPlayer(0);
    assertNotNull("Les champs de départ pour le joueur 0 ne doivent pas être nuls", homeFieldsForPlayer0);
    assertEquals("Le joueur 0 doit avoir 6 champs de départ", 6, homeFieldsForPlayer0.size()); // Chaque joueur a 9 cases de base
  }

  public void testGetAllHomeFields() {
    Set<Field> allHomeFields = board.getAllHomeFields();
    assertNotNull("Les champs de départ pour tous les joueurs ne doivent pas être nuls", allHomeFields);
    assertEquals("Tous les joueurs doivent avoir 24 champs de départ (4 joueurs * 6 cases par joueur)", 24, allHomeFields.size());
  }

  public void testGetTargetFieldsForPlayer() {
    Set<Field> targetFieldsForPlayer0 = board.getTargetFieldsForPlayer(0);
    assertNotNull("Les champs cibles pour le joueur 0 ne doivent pas être nuls", targetFieldsForPlayer0);
    assertEquals("Le joueur 0 doit avoir des champs cibles", 6, targetFieldsForPlayer0.size()); // Le joueur 0 doit avoir des cases cibles
  }

  public void testGetNeighbours() {
    // Test si la méthode getNeighbours fonctionne (actuellement, elle retourne un ensemble vide)
    Field field = new Field(0, 0); // Création d'un champ de test
    Set<Field> neighbours = board.getNeighbours(field);
    assertNotNull("Les voisins ne doivent pas être nuls", neighbours);
    assertEquals("Il n'y a pas de voisins définis pour ce champ", 0, neighbours.size()); // Aucun voisin n'est défini dans l'implémentation actuelle
  }

  public void testGetExtendedNeighbour() {
    // Vérification du comportement de getExtendedNeighbour
    Field field1 = new Field(0, 0);
    Field field2 = new Field(1, 1);
    assertNull("La méthode getExtendedNeighbour doit retourner null", board.getExtendedNeighbour(field1, field2)); // La méthode retourne null pour l'instant
  }

  public void testEquals() {
    BoardImpl board2 = new BoardImpl(3, players);
    assertTrue("Deux plateaux identiques doivent être égaux", board.equals(board2)); // Les deux objets BoardImpl avec les mêmes dimensions doivent être égaux

    board2 = new BoardImpl(4, players); // Plateau de taille différente
    assertFalse("Les plateaux de tailles différentes ne doivent pas être égaux", board.equals(board2)); // Les plateaux de tailles différentes ne doivent pas être égaux
  }

  public void testHashCode() {
    BoardImpl board2 = new BoardImpl(3, players);
    assertEquals("Le code de hachage des plateaux égaux doit être le même", board.hashCode(), board2.hashCode()); // Les objets égaux doivent avoir le même code de hachage
  }
}
