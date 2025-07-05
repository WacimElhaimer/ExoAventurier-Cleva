package com.adventurer.util;

import com.adventurer.model.Map;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Utilitaire pour lire et parser les fichiers d'entrée
 */
public class FileParser {
    
    /**
     * Lit le fichier de la carte et crée un objet Map
     * @param mapPath chemin vers le fichier de la carte
     * @return un objet Map représentant la carte
     * @throws IOException en cas d'erreur de lecture
     */
    public static Map parseMapFile(Path mapPath) throws IOException {
        // TODO: Implémenter le parsing du fichier de la carte
        return null;
    }

    /**
     * Lit le fichier des mouvements
     * @param movementsPath chemin vers le fichier des mouvements
     * @return un tableau contenant les coordonnées initiales [x,y] et les mouvements
     * @throws IOException en cas d'erreur de lecture
     */
    public static String[] parseMovementsFile(Path movementsPath) throws IOException {
        // TODO: Implémenter le parsing du fichier des mouvements
        return null;
    }
} 