package com.adventurer.model;

/**
 * Représente le personnage aventurier et gère ses déplacements
 */
public class Adventurer {
    private int x;
    private int y;

    public Adventurer(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Déplace l'aventurier selon la direction donnée
     * @param direction la direction (N, S, E, O)
     */
    public void move(char direction) {
        // TODO: Implémenter le déplacement
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
} 