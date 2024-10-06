package ca.uqam.info.solanum.t14.f24halma.model;

import ca.uqam.info.solanum.inf2050.f24halma.model.Board;
import ca.uqam.info.solanum.inf2050.f24halma.model.Field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class BoardImpl implements Board {
    private Field[][] fields; // Plateau de jeu
    private Field[][] playerBaseFields; // Cases de base pour les joueurs

    public BoardImpl(int baseSize, String[] players) {
        int boardSize = baseSize * 3; // Calculer la taille du plateau
        fields = new Field[boardSize][boardSize];
        playerBaseFields = new Field[players.length][]; // Initialisation des cases de base

        // Initialisation des cases sur le plateau et des cases de base pour chaque joueur
        for (int i = 0; i < players.length; i++) {
            int baseFieldCount = 3; // ou une autre valeur en fonction du test
            playerBaseFields[i] = new Field[baseFieldCount]; // Allouer 3 champs de base par joueur
            for (int j = 0; j < baseFieldCount; j++) {
                int xCoordinate = baseSize; // Coordonnée x
                int yCoordinate = j; // Coordonnée y
                playerBaseFields[i][j] = new Field(xCoordinate, yCoordinate);
            }
        }


    }

    private void initializeBaseFieldsForPlayer(int playerIndex, int baseSize) {
        for (int j = 0; j < baseSize; j++) {
            // Calcul des positions spécifiques sur le plateau pour chaque joueur
            int x = playerIndex; // ou une autre logique pour déterminer la coordonnée x
            int y = j; // ou une autre logique pour déterminer la coordonnée y
            playerBaseFields[baseSize][j] = new Field(x, y); // Modifie les coordonnées selon la logique du jeu
        }
    }


    /**
     * Getter for all fields contained in the board instance.
     *
     * @return unmodifiable set of all board fields.
     */
    @Override
    public Set<Field> getAllFields() {
        return Set.of();
    }

    /**
     * Getter for all home fields on the given board instance, for a given player specified by index.
     *
     * @param playerIndex as the player for whom we want to look up the home fields. Consecutive pairs
     *                    of an even then an odd player indexes always have opposing home zones.
     * @return unmodifiable set of all the player's home fields.
     */
    @Override
    public Set<Field> getHomeFieldsForPlayer(int playerIndex) {
        return Set.of(playerBaseFields[playerIndex]); // Retourne les cases de base pour le joueur
    }


    /**
     * Getter for all home fields (or target fields) on the board, regardless of player affiliation.
     *
     * @return unmodifiable set of all home fields
     */
    @Override
    public Set<Field> getAllHomeFields() {
        return Set.of();
    }

    /**
     * Getter for all target fields on the given board instance, for a given player specified by
     * index.
     *
     * @param playerIndex as the player for whom we want to look up the home fields. Consecutive pairs
     *                    of an even then an odd player indexes always have opposing home zones.
     * @return unmodifiable set of all the player's target fields.
     */
    @Override
    public Set<Field> getTargetFieldsForPlayer(int playerIndex) {
        return Set.of();
    }

    /**
     * Getter for neighboured fields, that is, fields that are directly adjacent to the given field
     * object.
     *
     * @param field as entity on the board for which a set of neighbours must be determined.
     * @return unmodifiable set of all adjacent field objects.
     */
    @Override
    public Set<Field> getNeighbours(Field field) {
        return Set.of();
    }

    /**
     * Getter for extended neighbours, that is all neighbours of neighbours that lie on a straight
     * line, starting from the given field.
     *
     * @param origin    as field on the board for which a potential extended neighbour is determined.
     * @param neighbour as a direct neighbour of the origin field, defining direction of the extended
     *                  neighbour.
     * @return Field defining extended neighbour of origin. May be null.
     */
    @Override
    public Field getExtendedNeighbour(Field origin, Field neighbour) {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BoardImpl)) return false;

        BoardImpl other = (BoardImpl) obj;
        // Comparez les champs que vous souhaitez
        return Arrays.deepEquals(this.fields, other.fields); // Assurez-vous que la comparaison est correcte pour vos champs
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(fields); // Utilise deepHashCode pour un tableau 2D
    }
}
