package com.adventurer.integration;

import com.adventurer.engine.GameEngine;
import com.adventurer.model.Adventurer;
import com.adventurer.model.Map;
import com.adventurer.util.FileParser;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTest {
    
    @Test
    void testFirstScenario_withRealMap() throws Exception {
        // Load the real map from carte.txt
        Path mapPath = Paths.get("carte.txt");
        Map map = FileParser.parseMapFile(mapPath);
        
        // Create adventurer at position 3,0
        Adventurer adventurer = new Adventurer(3, 0);
        GameEngine engine = new GameEngine(map, adventurer);
        
        // Execute movements
        boolean result = engine.executeMovements("SSSSEEEEEENN");
        
        // Verify result
        assertTrue(result, "First scenario should succeed");
        int[] finalPosition = engine.getCurrentPosition();
        assertEquals(9, finalPosition[0], "Final X should be 9");
        assertEquals(2, finalPosition[1], "Final Y should be 2");
    }
    
    @Test
    void testSecondScenario_withRealMap() throws Exception {
        // Load the real map from carte.txt
        Path mapPath = Paths.get("carte.txt");
        Map map = FileParser.parseMapFile(mapPath);
        
        // Try to create adventurer at position 6,9
        try {
            Adventurer adventurer = new Adventurer(6, 9);
            GameEngine engine = new GameEngine(map, adventurer);
            
            // Execute movements
            boolean result = engine.executeMovements("OONOOOSSO");
            
            // If we get here, the position was valid
            assertTrue(result, "Second scenario should succeed");
            int[] finalPosition = engine.getCurrentPosition();
            assertEquals(7, finalPosition[0], "Final X should be 7");
            assertEquals(5, finalPosition[1], "Final Y should be 5");
            
        } catch (IllegalArgumentException e) {
            fail("Position (6,9) is not valid in the provided map: " + e.getMessage());
        }
    }

    @Test
    void testCollisionWithBorders() throws Exception {
        // Load the real map
        Path mapPath = Paths.get("carte.txt");
        Map map = FileParser.parseMapFile(mapPath);
        
        // Test collision with left border
        Adventurer adventurer = new Adventurer(4, 4);
        GameEngine engine = new GameEngine(map, adventurer);
        assertFalse(engine.executeMovements("OOOO"), "Movement to the left border should fail");
        
        // Test collision with right border
        adventurer = new Adventurer(14, 4);
        engine = new GameEngine(map, adventurer);
        assertFalse(engine.executeMovements("EEEE"), "Movement to the right border should fail");
        
        // Test collision with top border
        adventurer = new Adventurer(10, 2);
        engine = new GameEngine(map, adventurer);
        assertFalse(engine.executeMovements("NN"), "Movement to the top border should fail");
        
        // Test collision with bottom border
        adventurer = new Adventurer(10, 17);
        engine = new GameEngine(map, adventurer);
        assertFalse(engine.executeMovements("SS"), "Movement to the bottom border should fail");
    }

    @Test
    void testCollisionWithWoods() throws Exception {
        Path mapPath = Paths.get("carte.txt");
        Map map = FileParser.parseMapFile(mapPath);
        
        // Test collision with woods from different directions
        // Test North collision (position libre à (3,3), bois à (3,2))
        Adventurer adventurer = new Adventurer(3, 3);
        GameEngine engine = new GameEngine(map, adventurer);
        assertFalse(engine.executeMovements("N"), "Movement into woods from south should fail");
        
        // Test South collision (position libre à (3,3), bois à (3,4))
        adventurer = new Adventurer(3, 3);
        engine = new GameEngine(map, adventurer);
        assertFalse(engine.executeMovements("S"), "Movement into woods from north should fail");
        
        // Test East collision (position libre à (3,3), bois à (4,3))
        adventurer = new Adventurer(3, 3);
        engine = new GameEngine(map, adventurer);
        assertFalse(engine.executeMovements("E"), "Movement into woods from west should fail");
        
        // Test West collision (position libre à (3,3), bois à (2,3))
        adventurer = new Adventurer(3, 3);
        engine = new GameEngine(map, adventurer);
        assertFalse(engine.executeMovements("O"), "Movement into woods from east should fail");
    }

    @Test
    void testInvalidInitialPosition() throws Exception {
        Path mapPath = Paths.get("carte.txt");
        Map map = FileParser.parseMapFile(mapPath);
        
        // Test starting in woods
        assertThrows(IllegalArgumentException.class, () -> {
            new Adventurer(0, 0);  // Position with woods
            new GameEngine(map, new Adventurer(0, 0));
        }, "Should not allow starting in woods");
        
        // Test starting out of bounds
        assertThrows(IllegalArgumentException.class, () -> {
            new GameEngine(map, new Adventurer(-1, 0));
        }, "Should not allow starting with negative X");
        
        assertThrows(IllegalArgumentException.class, () -> {
            new GameEngine(map, new Adventurer(0, -1));
        }, "Should not allow starting with negative Y");
        
        assertThrows(IllegalArgumentException.class, () -> {
            new GameEngine(map, new Adventurer(20, 0));
        }, "Should not allow starting beyond map width");
        
        assertThrows(IllegalArgumentException.class, () -> {
            new GameEngine(map, new Adventurer(0, 20));
        }, "Should not allow starting beyond map height");
    }

    @Test
    void testInvalidMovements() throws Exception {
        Path mapPath = Paths.get("carte.txt");
        Map map = FileParser.parseMapFile(mapPath);
        Adventurer adventurer = new Adventurer(4, 4);
        GameEngine engine = new GameEngine(map, adventurer);
        
        // Test invalid movement characters
        assertThrows(IllegalArgumentException.class, () -> {
            engine.executeMovements("X");
        }, "Should not allow invalid movement character");
        
        assertThrows(IllegalArgumentException.class, () -> {
            engine.executeMovements("NSx");
        }, "Should not allow invalid character in movement sequence");
        
        // Test null and empty movements
        assertTrue(engine.executeMovements(""), "Empty movement sequence should succeed");
        assertTrue(engine.executeMovements(null), "Null movement sequence should succeed");
    }

    @Test
    void testInvalidMapFile() {
        // Test non-existent file
        assertThrows(IOException.class, () -> {
            FileParser.parseMapFile(Paths.get("nonexistent.txt"));
        }, "Should throw exception for non-existent file");
        
        // Test invalid map content would go here, but requires creating temporary files
        // which is beyond the scope of this test
    }
} 