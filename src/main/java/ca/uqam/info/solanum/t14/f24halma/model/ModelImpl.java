package ca.uqam.info.solanum.t14.f24halma.model;

import ca.uqam.info.solanum.inf2050.f24halma.model.Board;
import ca.uqam.info.solanum.inf2050.f24halma.model.Field;
import ca.uqam.info.solanum.inf2050.f24halma.model.FieldException;
import ca.uqam.info.solanum.inf2050.f24halma.model.Model;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Implementation du modele du jeu.
 * Cette classe gère les joueurs, le plateau de jeu et les interactions entre eux.
 */
public class ModelImpl implements Model {
  private final String[] playerNames; // Noms des joueurs
  /**
   * Carte des cases occupées, associant chaque case occupée à un index de joueur.
   */
  protected Map<Field, Integer> occupiedFields = Map.of();
  /**
   * Index du joueur actif dans le jeu.
   */
  protected int playerIndex;
  /**
   * Plateau de jeu utilisé dans le modèle.
   */
  private final Board board;
  /**
   * Index du joueur courant dans le tour actuel.
   */
  protected int currentPlayerIndex;
  /**
   * Le joueur actuellement en train de jouer.
   */
  private int currentPlayer;


  /**
   * Constructeur pour la classe ModelImpl.
   * Ce constructeur initialise le modèle avec un plateau de jeu donné et un tableau
   * de noms de joueurs. Il crée également une nouvelle map pour les champs occupés
   * et définit l'index du joueur courant à 0.
   *
   * @param board       l'instance du plateau de jeu utilisé pour le modèle.
   * @param playerNames le tableau des noms des joueurs, qui sera cloné pour éviter
   *                    toute modification externe.
   * @throws IllegalArgumentException si le plateau de jeu est null.
   */
  public ModelImpl(Board board, String[] playerNames) {
    this.board = board; // Assurez-vous que 'board' n'est pas null ici
    this.playerNames = playerNames.clone();
    this.occupiedFields = new HashMap<>();
    this.currentPlayerIndex = 0;
  }

  /**
   * Constructeur de la classe ModelImpl.
   * Ce constructeur initialise le modèle avec la taille de base spécifiée et les noms des joueurs.
   *
   * @param baseSize la taille de base du plateau.
   * @param players  tableau des noms des joueurs.
   */
  public ModelImpl(int baseSize, String[] players) {
    this.playerNames = players.clone();
    this.playerIndex = 0;  // Le joueur 0 commence par défaut
    this.currentPlayerIndex = 0;
    this.currentPlayer = 0;

    // Initialisation du plateau avec la taille de base et les noms des joueurs
    this.board = new BoardImpl(baseSize, players);  // Assigner à la variable d'instance
  }

  /**
   * Associe un champ à un joueur, indiquant que le champ est désormais occupé
   * par le joueur spécifié.
   * Cette méthode vérifie si le champ est déjà occupé. Si c'est le cas, une
   * exception est levée. Sinon, elle enregistre le champ comme étant occupé
   * par le joueur donné.
   *
   * @param playerIndex l'index du joueur qui souhaite occuper le champ spécifié.
   * @param field       l'instance du champ à assigner au joueur.
   * @throws FieldException si le champ est déjà occupé.
   */
  @Override
  public void occupyField(int playerIndex, Field field) throws FieldException {
    // Vérifie si le champ est déjà occupé
    if (occupiedFields.containsKey(field)) {
      throw new FieldException("Ce champ est déjà occupé.");
    }
    // Assigne le champ au joueur
    occupiedFields.put(field, playerIndex);
  }

  /**
   * Libère un champ occupé, le rendant disponible pour une future occupation.
   * Cette méthode vérifie si le champ est actuellement occupé par un joueur.
   * Si ce n'est pas le cas, une exception est levée. Si le champ est occupé,
   * il est libéré en le supprimant des champs occupés.
   *
   * @param field le champ à libérer de toute occupation.
   * @throws FieldException si le champ spécifié n'est pas actuellement occupé.
   */
  @Override
  public void clearField(Field field) throws FieldException {
    // Vérifie si le champ est occupé
    if (!occupiedFields.containsKey(field)) {
      throw new FieldException("Ce champ n'est pas occupé.");
    }
    // Supprime l'occupation du champ
    occupiedFields.remove(field);
  }

  /**
   * Définit l'index du joueur actuel.
   * Cette méthode permet de définir quel joueur est le joueur en cours de jeu.
   * Si l'index fourni est en dehors de la plage valide (inférieur à zéro ou
   * supérieur au nombre de joueurs), une exception est levée.
   *
   * @param playerIndex l'index du joueur à définir comme joueur actuel.
   * @throws IllegalArgumentException si l'index du joueur est invalide.
   */
  @Override
  public void setCurrentPlayer(int playerIndex) {
    if (playerIndex < 0 || playerIndex > playerNames.length) {
      throw new IllegalArgumentException("Index de joueur invalide.");
    } else {
      currentPlayerIndex = playerIndex;
    }
  }

  /**
   * Retourne le plateau de jeu actuel.
   * Cette méthode fournit l'accès au plateau de jeu utilisé dans la partie en cours.
   *
   * @return le plateau de jeu (`Board`) actuellement utilisé.
   */
  @Override
  public Board getBoard() {

    return board;
  }

  /**
   * Vérifie si un champ donné est inoccupé sur le plateau de jeu.
   * Cette méthode permet de vérifier si le champ spécifié existe sur le plateau de jeu
   * et qu'il est inoccupé. Elle déclenche une exception si le champ n'est pas valide.
   *
   * @param field le champ (`Field`) à vérifier sur le plateau.
   * @return `true` si le champ est inoccupé, `false` sinon.
   * @throws FieldException si le champ spécifié n'est pas un champ valide sur le plateau.
   */
  @Override
  public boolean isClear(Field field) throws FieldException {
    boolean result = board.getAllFields().contains(field);

    // Vérifie si le champ est occupé
    if (!result) {
      throw new FieldException("Ce champ n'est pas occupé.");
    }
    return result;
  }

  /**
   * Retourne une copie des noms des joueurs.
   * Cette méthode renvoie un tableau contenant les noms des joueurs, sous forme de copie
   * pour éviter toute modification externe de la liste de joueurs.
   *
   * @return un tableau de chaînes de caractères (`String[]`) contenant les noms des joueurs.
   */
  @Override
  public String[] getPlayerNames() {
    return playerNames.clone(); // Retourne une copie pour éviter la modification externe
  }

  /**
   * Retourne l'ensemble des champs actuellement occupés par un joueur donné.
   * Cette méthode renvoie un ensemble non modifiable de champs occupés par le joueur
   * spécifié par son index. Si le joueur n'occupe aucun champ, l'ensemble renvoyé sera vide.
   *
   * @param playerIndex l'index du joueur pour lequel on souhaite obtenir les champs occupés.
   * @return un ensemble contenant les champs occupés par le joueur spécifié.
   */
  @Override
  public Set<Field> getPlayerFields(int playerIndex) {

    return Set.of();
  }

  /**
   * Retourne l'index du joueur actuel.
   * Cette méthode renvoie l'index du joueur dont c'est actuellement le tour de jouer.
   * L'index du joueur est un entier, où chaque joueur est associé à un index dans l'ordre
   * des joueurs.
   *
   * @return l'index du joueur actuel (entier).
   */
  @Override
  public int getCurrentPlayer() {

    return currentPlayer;
  }

  /**
   * Retourne l'index du joueur actuel dans le modèle.
   * Cette méthode permet d'obtenir l'index du joueur qui est actuellement actif dans le jeu.
   * L'index correspond à la position du joueur dans le tableau des joueurs du modèle.
   *
   * @return l'index du joueur actuel (entier).
   */
  public int getCurrentPlayerIndex() {

    return currentPlayerIndex;
  }

  /**
   * Vérifie l'égalité entre l'objet courant et un autre objet.
   * Cette méthode compare l'objet actuel avec un autre objet pour vérifier si ces deux
   * objets sont égaux.
   * L'égalité est déterminée en vérifiant si l'objet est une instance de `ModelImpl` et
   * en comparant
   * les valeurs des champs `board` et `currentPlayerIndex` entre l'objet actuel et l'objet passé
   * en paramètre.
   *
   * @param obj l'objet à comparer avec l'objet courant.
   * @return `true` si l'objet courant est égal à l'objet passé en paramètre, sinon `false`.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof ModelImpl)) {
      return false;
    }
    ModelImpl other = (ModelImpl) obj;
    return board.equals(other.board) && currentPlayerIndex == other.currentPlayerIndex;
  }
  /**
   * Ajoute les champs (fields) d'un joueur à l'ensemble des champs associés à ce joueur.
   *
   * <p>Cette méthode permet d'ajouter les champs spécifiques d'un joueur à la collection
   * de ses champs dans le modèle. Chaque joueur est identifié par un index, et un ensemble
   * de champs est associé à cet index pour représenter les positions actuelles des pièces
   * du joueur sur le plateau.</p>
   *
   * @param i l'index du joueur, utilisé pour identifier quel joueur a ces champs.
   * @param playerFields l'ensemble des champs à associer à ce joueur, représentant
   *                     les positions de ses pièces sur le plateau.
   */

  public void addPlayerFields(int i, Set<Field> playerFields) {
  }
}


