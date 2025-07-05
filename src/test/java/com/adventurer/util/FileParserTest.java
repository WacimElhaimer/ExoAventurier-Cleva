package com.adventurer.util;

import com.adventurer.model.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileParserTest {

    @TempDir
    Path tempDir;

    @Test
    void parseMapFile_shouldReadValidMap() throws IOException {
        // Given
        String mapContent = String.join("\n",
            "###    ######    ###",
            "###      ##      ###",
            "##     ##  ##     ##"
        );
        Path mapPath = createTempFile("test_map.txt", mapContent);

        // When
        Map map = FileParser.parseMapFile(mapPath);

        // Then
        assertNotNull(map, "Map should not be null");
        assertTrue(map.isValidPosition(4, 0), "Position (4,0) should be valid (empty space)");
        assertFalse(map.isValidPosition(0, 0), "Position (0,0) should be invalid (wall)");
    }

    @Test
    void parseMovementsFile_shouldReadValidMovements() throws IOException {
        // Given
        String movementsContent = String.join("\n",
            "3,0",
            "SSSSEEEEEENN"
        );
        Path movementsPath = createTempFile("test_movements.txt", movementsContent);

        // When
        String[] result = FileParser.parseMovementsFile(movementsPath);

        // Then
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.length, "Should return array with 2 elements");
        assertEquals("3,0", result[0], "First line should contain initial position");
        assertEquals("SSSSEEEEEENN", result[1], "Second line should contain movements");
    }

    @Test
    void parseMapFile_shouldThrowException_whenFileNotFound() {
        // Given
        Path nonExistentPath = tempDir.resolve("non_existent.txt");

        // Then
        assertThrows(IOException.class, () -> {
            FileParser.parseMapFile(nonExistentPath);
        }, "Should throw IOException when file not found");
    }

    private Path createTempFile(String fileName, String content) throws IOException {
        Path filePath = tempDir.resolve(fileName);
        Files.write(filePath, content.getBytes());
        return filePath;
    }
} 