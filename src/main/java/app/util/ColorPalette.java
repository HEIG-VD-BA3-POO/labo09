package app.util;

import java.awt.Color;

public class ColorPalette {
    // Array of predefined colors
    private static final Color[] COLORS = {
            new Color(255, 0, 0), // Red
            new Color(0, 0, 255), // Blue
            new Color(0, 255, 0), // Green
            new Color(255, 165, 0), // Orange
            new Color(128, 0, 128), // Purple
            new Color(0, 255, 255), // Cyan
            new Color(255, 192, 203), // Pink
            new Color(165, 42, 42), // Brown
            new Color(0, 128, 0), // Dark Green
            new Color(75, 0, 130) // Indigo
    };

    private static int currentIndex = 0;

    // Get next color in rotation
    public static Color getNextColor() {
        Color color = COLORS[currentIndex];
        currentIndex = (currentIndex + 1) % COLORS.length;
        return color;
    }

    // Reset the color rotation
    public static void reset() {
        currentIndex = 0;
    }

    // Get color at specific index
    public static Color getColorAt(int index) {
        return COLORS[index % COLORS.length];
    }

    // Get total number of colors
    public static int getColorCount() {
        return COLORS.length;
    }

    // Private constructor to prevent instantiation
    private ColorPalette() {
        // Utility class should not be instantiated
    }
}
