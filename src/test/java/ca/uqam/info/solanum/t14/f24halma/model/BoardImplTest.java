package ca.uqam.info.solanum.t14.f24halma.model;


import ca.uqam.info.solanum.inf2050.f24halma.model.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.Set;


class BoardImplTest {

  private BoardImpl board;
  private String[] players;

  @BeforeEach
  void setUp() {
    players = new String[]{"Player1", "Player2", "Player3", "Player4"};
    board = new BoardImpl(3, players); // Crée un plateau avec baseSize = 3 et 4 joueurs
  }

  @Test
  void testConstructor() {
    // Vérification de la taille du plateau
    assertEquals(81, board.getAllFields().size()); // Le plateau doit être de taille 9x9 = 81 cases (en supposant que les cases sont initialisées correctement)
    // Vérification de l'initialisation des cases de base pour les joueurs
    for (int i = 0; i < players.length; i++) {
      Set<Field> homeFields = board.getHomeFieldsForPlayer(i);
      assertNotNull(homeFields);
      assertEquals(6, homeFields.size()); // Vérifie que chaque joueur a 6 cases de base
    }
  }

  @Test
  void testGetAllFields() {
    Set<Field> allFields = board.getAllFields();
    assertNotNull(allFields);
    assertEquals(81, allFields.size()); // Le plateau doit avoir 81 cases, vérification du nombre total de cases
  }

  @Test
  void testGetHomeFieldsForPlayer() {
    Set<Field> homeFieldsForPlayer0 = board.getHomeFieldsForPlayer(0);
    assertNotNull(homeFieldsForPlayer0);
    assertEquals(6, homeFieldsForPlayer0.size()); // Chaque joueur a 6 cases dans sa zone de départ
  }

  @Test
  void testGetAllHomeFields() {
    Set<Field> allHomeFields = board.getAllHomeFields();
    assertNotNull(allHomeFields);
    assertEquals(24, allHomeFields.size()); // Tous les joueurs combinés devraient avoir 24 cases de base (4 joueurs * 6 cases par joueur)
  }

  @Test
  void testGetTargetFieldsForPlayer() {
    Set<Field> targetFieldsForPlayer0 = board.getTargetFieldsForPlayer(0);
    assertNotNull(targetFieldsForPlayer0);
    assertEquals(6, targetFieldsForPlayer0.size()); // Le joueur 0 doit avoir des cases cibles
  }

  @Test
  void testEquals() {
    BoardImpl board2 = new BoardImpl(3, players);
    assertTrue(board.equals(board2)); // Deux objets BoardImpl avec les mêmes dimensions et même état doivent être égaux

    board2 = new BoardImpl(4, players); // Plateau de taille différente
    assertFalse(board.equals(board2)); // Les plateaux de tailles différentes ne doivent pas être égaux
  }

  @Test
  void testHashCode() {
    BoardImpl board2 = new BoardImpl(3, players);
    assertEquals(board.hashCode(), board2.hashCode()); // Si deux objets sont égaux, leur code de hachage doit être le même
  }

  @Test
  void testGetNeighbours() {
    // Test si la méthode getNeighbours fonctionne (pour l'instant, on s'attend à un ensemble vide)
    Field field = new Field(0, 0); // Création d'un champ de test
    Set<Field> neighbours = board.getNeighbours(field);
    assertNotNull(neighbours);
    assertEquals(0, neighbours.size()); // Aucun voisin n'est défini dans l'implémentation actuelle
  }

  @Test
  void testGetExtendedNeighbour() {
    // Vérification du comportement de getExtendedNeighbour
    Field field1 = new Field(0, 0);
    Field field2 = new Field(1, 1);
    assertNull(board.getExtendedNeighbour(field1, field2)); // Pour l'instant, cette méthode retourne null
  }
}
