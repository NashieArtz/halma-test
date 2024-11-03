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
        int boardSize = baseSize * 3; // Calculer la taille des dimensions du plateau
        fields = new Field[boardSize][boardSize]; // Allocation du plateau de jeu
        playerBaseFields = new Field[players.length][baseSize * baseSize]; // Allocation de la zone du joueur

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
                    int xCoordinate = i * baseSize + j; // Coordonnée x pour le joueur
                    int yCoordinate = (players.length - 1 - i) * baseSize + k; // Coordonnée y pour éviter le chevauchement

                    // Vérifier les limites pour éviter un débordement
                    if (xCoordinate < boardSize && yCoordinate < boardSize) {
                        playerBaseFields[i][j * baseSize + k] = new Field(xCoordinate, yCoordinate);
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
            playerBaseFields[playerIndex][j] = new Field(playerIndex, j); // Utilise playerIndex et j pour la position
        }
    }

    /**
     * Methode heritee
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
     * Methode heritee
     */
    @Override
    public Set<Field> getHomeFieldsForPlayer(int playerIndex) {
        return Set.copyOf(Arrays.asList(playerBaseFields[playerIndex])); // Retourne les cases de base pour le joueur
    }

    /**
     * Methode heritee
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
     * Methode heritee
     */
    @Override
    public Set<Field> getTargetFieldsForPlayer(int playerIndex) {
        return Set.of(); // Remplacer par la logique correcte
    }

    /**
     * Methode heritee
     */
    @Override
    public Set<Field> getNeighbours(Field field) {
        Set<Field> neighbours = new HashSet<>();
        // Implémentez la logique pour récupérer les voisins ici
        return Set.copyOf(neighbours);
    }

    /**
     * Methode heritee
     */
    @Override
    public Field getExtendedNeighbour(Field origin, Field neighbour) {
        return null; // Logique à implémenter si nécessaire
    }

    /**
     * Methode heritee
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BoardImpl other)) return false;

        return Arrays.deepEquals(this.fields, other.fields); // Comparez les champs que vous souhaitez
    }

    /**
     * Methode heritee
     */
    @Override
    public int hashCode() {
        return Arrays.deepHashCode(fields); // Utilise deepHashCode pour un tableau 2D
    }
}


