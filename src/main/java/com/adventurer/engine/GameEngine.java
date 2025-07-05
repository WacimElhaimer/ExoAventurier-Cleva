package com.adventurer.engine;

import com.adventurer.model.Adventurer;
import com.adventurer.model.Map;

/**
 * Moteur du jeu qui gère l'interaction entre la carte et l'aventurier
 */
public class GameEngine {
    private Map map;
    private Adventurer adventurer;

    public GameEngine(Map map, Adventurer adventurer) {
        this.map = map;
        this.adventurer = adventurer;
    }

    /**
     * Exécute une séquence de mouvements
     * @param movements la chaîne de caractères représentant les mouvements
     * @return true si tous les mouvements ont été exécutés avec succès
     */
    public boolean executeMovements(String movements) {
        // TODO: Implémenter l'exécution des mouvements
        return false;
    }

    /**
     * Tente de déplacer l'aventurier dans une direction
     * @param direction la direction du mouvement
     * @return true si le mouvement a été effectué avec succès
     */
    private boolean tryMove(char direction) {
        // TODO: Implémenter la tentative de déplacement
        return false;
    }
} 