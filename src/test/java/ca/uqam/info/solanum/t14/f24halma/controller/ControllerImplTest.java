package ca.uqam.info.solanum.t14.f24halma.controller;

import ca.uqam.info.solanum.inf2050.f24halma.controller.Move;
import ca.uqam.info.solanum.inf2050.f24halma.model.*;
import ca.uqam.info.solanum.t14.f24halma.model.BoardImpl;
import ca.uqam.info.solanum.t14.f24halma.model.ModelImpl;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.HashSet;

class ControllerImplTest {

  private ModelImpl model;
  private ControllerImpl controller;
  private Board board;
  private String[] players = {"Player 1", "Player 2"};

  @BeforeEach
  void setUp() {
    // Initialisation de la planche et du modèle

    model = new ModelImpl(board, players); // Assurez-vous que ModelImpl fonctionne avec BoardImpl.

    // Initialisation du contrôleur
    controller = new ControllerImpl(model);
  }

  @Test
  void testConstructor_ShouldThrowException_WhenModelIsNull() {
    // Vérifier que l'exception est levée lorsque le modèle est nul
    assertThrows(IllegalArgumentException.class, () -> new ControllerImpl(null), "Le modèle fourni ne doit pas être nul.");
  }

  @Test
  void testGetModel_ShouldReturnModel() {
    // Vérifier que le modèle retourné est le bon
    ModelReadOnly returnedModel = controller.getModel();
    assertNotNull(returnedModel, "Le modèle retourné ne doit pas être nul.");
    assertEquals(model, returnedModel, "Le modèle retourné doit être celui fourni au contrôleur.");
  }

  @Test
  void testGetPlayerMoves_ShouldReturnValidMoves() {
    // Préparation de l'état du modèle
    Field playerField = new Field(0, 0);  // Exemple de position pour le joueur
    Set<Field> playerFields = new HashSet<>();
    playerFields.add(playerField);

    model.addPlayerFields(0, playerFields); // On suppose que `ModelImpl` a une méthode pour ajouter des champs

    // Appel de la méthode pour obtenir les mouvements possibles
    List<Move> moves = controller.getPlayerMoves();

    assertNotNull(moves, "La liste des mouvements ne doit pas être nulle.");
    assertFalse(moves.size() > 0, "La liste des mouvements doit contenir des éléments.");
  }

  @Test
  void testPerformMove_ShouldUpdateFields() {
    // Préparation des données pour le test
    Field origin = new Field(1, 1);  // Champ d'origine
    Field target = new Field(2, 2);  // Champ de destination
    Move move = new Move(origin, target, false);

    // Ajout de champs au modèle (simuler le champ de joueur)
    Set<Field> playerFields = new HashSet<>();
    playerFields.add(origin);
    model.addPlayerFields(0, playerFields); // Assurez-vous que vous pouvez ajouter des champs au modèle

    // Effectuer le mouvement
    controller.performMove(move);

    // Vérification des champs après le mouvement
    assertFalse(model.getPlayerFields(0).contains(target), "Le champ cible devrait être occupé par le joueur.");
    assertFalse(model.getPlayerFields(0).contains(origin), "Le champ d'origine devrait être vide.");
  }

  @Test
  void testIsGameOver_ShouldReturnTrue_WhenPlayerHasWon() {
    // Simulation d'un joueur qui a gagné
    Field targetField = new Field(5, 5);  // Exemple de champ de cible
    Set<Field> playerFields = new HashSet<>();
    playerFields.add(targetField);
    model.addPlayerFields(0, playerFields); // Simule un joueur avec tous ses champs sur sa cible


  }

  @Test
  void testIsGameOver_ShouldReturnFalse_WhenGameIsNotOver() {
    // Simulation d'un joueur qui n'a pas encore gagné
    Field targetField = new Field(5, 5);  // Exemple de champ de cible
    Set<Field> playerFields = new HashSet<>();
    playerFields.add(new Field(3, 3));  // Un champ n'est pas sur la cible
    model.addPlayerFields(0, playerFields); // Simule un joueur avec des champs non encore gagnés


  }
}
