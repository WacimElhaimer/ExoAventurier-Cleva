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
        List<String> lines = Files.readAllLines(mapPath);
        if (lines.isEmpty()) {
            throw new IOException("Map file is empty");
        }

        int height = lines.size();
        int width = lines.get(0).length();
        char[][] grid = new char[height][width];

        for (int y = 0; y < height; y++) {
            String line = lines.get(y);
            if (line.length() != width) {
                throw new IOException("Inconsistent map width at line " + (y + 1));
            }
            grid[y] = line.toCharArray();
        }

        return new Map(grid);
    }

    /**
     * Lit le fichier des mouvements
     * @param movementsPath chemin vers le fichier des mouvements
     * @return un tableau contenant les coordonnées initiales [x,y] et les mouvements
     * @throws IOException en cas d'erreur de lecture
     */
    public static String[] parseMovementsFile(Path movementsPath) throws IOException {
        List<String> lines = Files.readAllLines(movementsPath);
        if (lines.size() != 2) {
            throw new IOException("Movements file must contain exactly 2 lines");
        }

        String position = lines.get(0);
        String movements = lines.get(1);

        // Validation du format des coordonnées (x,y)
        if (!position.matches("\\d+,\\d+")) {
            throw new IOException("Invalid position format. Expected 'x,y'");
        }

        // Validation des mouvements (N,S,E,O uniquement)
        if (!movements.matches("[NSEO]+")) {
            throw new IOException("Invalid movements. Only N,S,E,O are allowed");
        }

        return new String[]{position, movements};
    }
} 