package com.valproate.sandapp.elements;

import com.badlogic.gdx.graphics.Color;

public class ColorConstants {
    private static final Color EMPTY = Color.BLACK;

    private static final Color SAND_1 = new Color(209/255f, 172/255f, 40/255f, 1);

    public static Color getColorByType(Element.ElementType type) {
        switch(type) {
            case SAND:
                return SAND_1;
            default:
                return Color.BLACK;
        }
    }
}
