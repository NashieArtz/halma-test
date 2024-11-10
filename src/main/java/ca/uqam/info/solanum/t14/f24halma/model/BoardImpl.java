package ca.uqam.info.solanum.t14.f24halma.model;

import ca.uqam.info.solanum.inf2050.f24halma.model.Board;
import ca.uqam.info.solanum.inf2050.f24halma.model.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe qui implémente l'interface Board pour représenter le plateau de jeu.
 */
public class BoardImpl implements Board {
  private final Field[][] fields; // Plateau de jeu
  private final Field[][] playerBaseFields; // Cases de base pour les joueurs

  /**
   * Constructeur de la classe BoardImpl.
   *
   * @param baseSize La taille de la zone de base pour chaque joueur.
   * @param players  Un tableau de chaînes représentant les joueurs.
   */
  public BoardImpl(int baseSize, String[] players) {
    int difference = 0;

    for (int l = 1; l == baseSize; l++) {
      difference = l++;
    }
    int boardSize = baseSize * 3; // Calculer la taille des dimensions du plateau

    fields = new Field[boardSize][boardSize]; // Allocation du plateau de jeu
    //Allocation de la zone du joueur
    playerBaseFields = new Field[players.length][baseSize * baseSize];

    // Initialisation des cases sur le plateau
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        fields[i][j] = new Field(i, j); // Initialiser tous les champs du plateau
      }
    }

    // Initialisation des cases de base pour chaque joueur
    for (int i = 0; i < players.length; i++) {
      // Assurez-vous que vous attribuez correctement les champs de base
      for (int j = 0; j < baseSize; j++) { // Parcours des lignes
        for (int k = 0; k < baseSize; k++) { // Parcours des colonnes
          if (i == 0) {
            // Coordonnée x pour le joueur
            int xcoordinate = j;
            int ycoordinate = k - j;
            if (xcoordinate < 0 || xcoordinate > boardSize - 1) {
              xcoordinate = 0;
            } else if (ycoordinate < 0 || ycoordinate > boardSize - 1) {
              ycoordinate = 0; // Coordonnée y pour éviter le depassement
            }
            // Vérifier les limites pour éviter un débordement
            if (xcoordinate < boardSize && ycoordinate < boardSize) {
              playerBaseFields[i][j * baseSize + k] = new Field(xcoordinate, ycoordinate);
            }

          } else if (i == 1) {
            int xcoordinate = (boardSize - 1) - j; // Coordonnée x pour le joueur
            int ycoordinate = (boardSize - 1) - k + j; // Coordonnée y pour éviter le chevauchement
            if (ycoordinate < 0 || ycoordinate > boardSize - 1) {
              ycoordinate = boardSize - 1;
            } else if (xcoordinate < 0 || xcoordinate > boardSize - 1) {
              xcoordinate = boardSize - 1;
            }

            // Vérifier les limites pour éviter un débordement
            if ((xcoordinate < boardSize && ycoordinate < boardSize)) {
              playerBaseFields[i][j * baseSize + k] = new Field(xcoordinate, ycoordinate);
            }
          } else if (i == 2) {
            int xcoordinate = j; // Coordonnée x pour le joueur
            int ycoordinate = (boardSize - 1) - k + j; // Coordonnée y pour éviter le chevauchement
            if (xcoordinate < 0 || xcoordinate > boardSize - 1) {
              xcoordinate = 0;
            } else if (ycoordinate < 0 || ycoordinate > boardSize - 1) {
              ycoordinate = boardSize - 1; // Coordonnée y pour éviter le depassement
            }
            // Vérifier les limites pour éviter un débordement
            if (xcoordinate < boardSize && ycoordinate < boardSize) {
              playerBaseFields[i][j * baseSize + k] = new Field(xcoordinate, ycoordinate);
            }
          } else if (i == 3) {
            int xcoordinate = (boardSize - 1) - j; // Coordonnée x pour le joueur
            int ycoordinate = k - j; // Coordonnée y pour éviter le chevauchement
            if (xcoordinate < 0 || xcoordinate > boardSize - 1) {
              xcoordinate = boardSize - 1;
            } else if (ycoordinate < 0 || ycoordinate > boardSize - 1) {
              ycoordinate = 0; // Coordonnée y pour éviter le depassement
            }

            // Vérifier les limites pour éviter un débordement
            if (xcoordinate < boardSize && ycoordinate < boardSize) {
              playerBaseFields[i][j * baseSize + k] = new Field(xcoordinate, ycoordinate);
            }
          }
        }
      }
    }
  }

  /**
  * Méthode pour initialiser la zone du joueur.
  *
  * @param playerIndex L'index du joueur pour lequel initialiser la zone.
  * @param baseFieldCount Le nombre de champs de base à initialiser.
  */
  private void initializeBaseFieldsForPlayer(int playerIndex, int baseFieldCount) {
    playerBaseFields[playerIndex] = new Field[baseFieldCount]; // Initialisation

    for (int j = 0; j < baseFieldCount; j++) {
      // Utilise playerIndex et j pour la position
      playerBaseFields[playerIndex][j] = new Field(playerIndex, j);
    }
  }

  /**
   * Methode heritee.
   */
  @Override
  public Set<Field> getAllFields() {
    Set<Field> allFieldsSet = new HashSet<>();
    for (Field[] row : fields) {
      allFieldsSet.addAll(Arrays.asList(row));
    }
    return Set.copyOf(allFieldsSet); // Retourner un ensemble immuable de tous les champs
  }

  /**
   * Methode heritee.
   */
  @Override
  public Set<Field> getHomeFieldsForPlayer(int playerIndex) {
    // Retourne les cases de base pour le joueur
    return Set.copyOf(Arrays.asList(playerBaseFields[playerIndex]));
  }

  /**
   * Methode heritee.
   */
  @Override
  public Set<Field> getAllHomeFields() {
    Set<Field> allHomeFieldsSet = new HashSet<>();
    for (Field[] playerFields : playerBaseFields) {
      allHomeFieldsSet.addAll(Arrays.asList(playerFields));
    }
    return Set.copyOf(allHomeFieldsSet); // Retourner un ensemble immuable de toutes les bases
  }

  /**
   * Methode heritee.
   */
  @Override
  public Set<Field> getTargetFieldsForPlayer(int playerIndex) {
    Set<Field> targetFieldsForPlayerSet = new HashSet<>();
    if (playerIndex == 0) {
      targetFieldsForPlayerSet = Set.copyOf(Arrays.asList(playerBaseFields[1]));
    } else if (playerIndex == 1) {
      targetFieldsForPlayerSet = Set.copyOf(Arrays.asList(playerBaseFields[0]));
    } else if (playerIndex == 2) {
      targetFieldsForPlayerSet = Set.copyOf(Arrays.asList(playerBaseFields[3]));
    } else if (playerIndex == 3) {
      targetFieldsForPlayerSet = Set.copyOf(Arrays.asList(playerBaseFields[2]));
    }
    return Set.copyOf(targetFieldsForPlayerSet);
  }

  /**
   * Methode heritee.
   */
  @Override
  public Set<Field> getNeighbours(Field field) {
    Set<Field> neighbours = new HashSet<>();
    // Implémentez la logique pour récupérer les voisins ici
    return Set.copyOf(neighbours);
  }

  /**
   * Methode heritee.
   */
  @Override
  public Field getExtendedNeighbour(Field origin, Field neighbour) {
    return null; // Logique à implémenter si nécessaire
  }

  /**
   * Methode heritee.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof BoardImpl other)) {
      return false;
    }

    return Arrays.deepEquals(this.fields, other.fields); // Comparez les champs que vous souhaitez
  }

  /**
   * Methode heritee.
   */
  @Override
  public int hashCode() {
    return Arrays.deepHashCode(fields); // Utilise deepHashCode pour un tableau 2D
  }
}




