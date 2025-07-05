package com.adventurer.model;

/**
 * Représente le personnage aventurier et gère ses déplacements
 */
public class Adventurer {
    private int x;
    private int y;

    /**
     * Crée un nouvel aventurier à la position spécifiée
     * @param x coordonnée x initiale
     * @param y coordonnée y initiale
     */
    public Adventurer(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Déplace l'aventurier selon la direction donnée
     * @param direction la direction (N, S, E, O)
     * @param map la carte sur laquelle se déplace l'aventurier
     * @return true si le mouvement a réussi, false sinon
     * @throws IllegalArgumentException si la direction est invalide
     */
    public boolean move(char direction, Map map) {
        int newX = x;
        int newY = y;

        switch (direction) {
            case 'N':
                newY--;
                break;
            case 'S':
                newY++;
                break;
            case 'E':
                newX++;
                break;
            case 'O':
                newX--;
                break;
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }

        if (!map.isValidPosition(newX, newY) || !map.isFreeSpace(newX, newY)) {
            return false;
        }

        x = newX;
        y = newY;
        return true;
    }

    /**
     * Exécute une séquence de mouvements
     * @param movements la chaîne de mouvements (N, S, E, O)
     * @param map la carte sur laquelle se déplace l'aventurier
     * @return true si tous les mouvements ont réussi
     */
    public boolean executeMovements(String movements, Map map) {
        boolean allSucceeded = true;
        for (char direction : movements.toCharArray()) {
            if (!move(direction, map)) {
                allSucceeded = false;
            }
        }
        return allSucceeded;
    }

    /**
     * Retourne la coordonnée x actuelle
     */
    public int getX() {
        return x;
    }

    /**
     * Retourne la coordonnée y actuelle
     */
    public int getY() {
        return y;
    }

    /**
     * Définit la position de l'aventurier
     * @param x nouvelle coordonnée x
     * @param y nouvelle coordonnée y
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("Adventurer at position (%d,%d)", x, y);
    }
} 