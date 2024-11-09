package ca.uqam.info.solanum.t14.f24halma.controller;

import ca.uqam.info.solanum.inf2050.f24halma.controller.Controller;
import ca.uqam.info.solanum.inf2050.f24halma.controller.ModelFactory;
import ca.uqam.info.solanum.t14.f24halma.model.ModelImpl;
import ca.uqam.info.solanum.inf2050.f24halma.model.Field;
import ca.uqam.info.solanum.inf2050.f24halma.model.Model;
import ca.uqam.info.solanum.inf2050.f24halma.controller.Move;
import ca.uqam.info.solanum.inf2050.f24halma.model.ModelReadOnly;
import ca.uqam.info.solanum.inf2050.f24halma.model.FieldException;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ControllerImpl implements Controller {
    private final ModelImpl model;

    public ControllerImpl(ModelImpl model) {
        if (model == null) {
            throw new IllegalArgumentException("Le modèle fourni ne doit pas être nul.");
        }
        this.model = model;
    }

    /**
     * Read only access to current version of model. Can be used e.g. by UI, to visualize state.
     * Cannot be used to directly modify state. All state modification must pass through other
     * controller methods.
     *
     * @return the immutable model.
     */
    @Override
    public ModelReadOnly getModel() {
        return this.model;
    }

    // Méthodes de la classe


    /**
     * Generator for all valid actions of the current player. Each action object represents an atomic
     * move, that is, further actions by the same player may be possible. If the previous move was a
     * jump (figure places across another figure), only moves for the same figure can be performed,
     * ,the inverse jum (returning to jump origin) is not contained in the result set. If at least one
     * consecutive jump is possible, a null move (with original equal to target) must be contained in
     * the result list.
     *
     * @return list of action objects, each representing an atomic figure move.
     */
    @Override
    public List<Move> getPlayerMoves() {
        int currentPlayerIndex = model.getCurrentPlayer();
        Set<Field> playerFields = model.getPlayerFields(currentPlayerIndex);
        List<Move> possibleMoves = new ArrayList<>();
        for (Field field : playerFields) {
            Set<Field> neighbours = model.getBoard().getNeighbours(field);
            for (Field neighbour : neighbours) {
                if (model.isClear(neighbour)) {
                    possibleMoves.add(new Move(field, neighbour, false));
                }
            }
        }
        return possibleMoves;
    }

    /**
     * Modifies model state according to the action index of a given player's actions.
     *
     * @param move as the action to perform, i.e. the details of how the model state must be changed.
     */
    @Override
    public void performMove(Move move) {
        try {
            model.occupyField(model.getCurrentPlayer(), move.target());
            model.clearField(move.origin());
        } catch (FieldException e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    /**
     * Tells whether the game is over.
     *
     * @return true when at least one player has all their figures on their target fields.
     */
    @Override
    public boolean isGameOver() {
        for (int playerIndex = 0; playerIndex < model.getPlayerNames().length; playerIndex++) {
            Set<Field> targetFields = model.getBoard().getTargetFieldsForPlayer(playerIndex);
            Set<Field> playerFields = model.getPlayerFields(playerIndex);
            boolean allInTarget = true;
            for (Field field : playerFields) {
                if (!targetFields.contains(field)) {
                    allInTarget = false;
                    break;
                }
            }
            if (allInTarget) {
                return true;
            }
        }
        return false;
    }
}
