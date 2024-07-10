package com.valproate.sandapp.elements;

import com.badlogic.gdx.graphics.Color;

public class ColorConstants {
    private static final Color EMPTY = Color.BLACK;

    private static final Color SAND_1 = new Color(209/255f, 172/255f, 40/255f, 1);

    private static final Color STONE_1 = new Color(150/255f, 150/255f, 150/255f, 1);

    private static final Color WATER_1 = new Color(7/255f, 137/255f, 237/255f, 1);

    public static Color getColorByType(ElementType type) {
        switch(type) {
            case SAND:
                return SAND_1;
            case STONE:
                return STONE_1;
            case WATER:
                return WATER_1;
            default:
                return Color.BLACK;
        }
    }
}
