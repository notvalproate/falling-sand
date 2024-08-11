package com.valproate.sandapp;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.valproate.sandapp.elements.ColorConstants;
import com.valproate.sandapp.elements.Element;
import com.valproate.sandapp.elements.ElementType;
import com.valproate.sandapp.elements.EmptyElement;

public class SandMatrix {
    public static Vector2 gravity = new Vector2(0, -5f);

    public final int width, height;
    private final Element[][] matrix;

    SandMatrix(int width, int height) {
        this.width = width;
        this.height = height;
        this.matrix = new Element[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.matrix[i][j] = EmptyElement.getInstance();
            }
        }
    }

    public void step() {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.matrix[i][j].step(this);
            }
        }
    }

    // OPTIMIZE THIS THING IT EATS MY FPS
    public void render(ShapeRenderer sr) {
        sr.begin(ShapeRenderer.ShapeType.Filled);
        for (int j = 0; j < this.height; j++) {
            for (int i = 0; i < this.width; i++) {
                if(this.matrix[i][j].type == ElementType.EMPTY) {
                    continue;
                }

                Color currentColor = ColorConstants.getColorByType(this.matrix[i][j].type);
                int toIndex = i;
                for(int following = i; following < this.width; following++) {
                    Element followingElement = this.matrix[following][j];
                    if(!currentColor.equals(ColorConstants.getColorByType(followingElement.type))) {
                        break;
                    }
                    toIndex = following;
                }

                sr.setColor(currentColor);
                sr.rect(i, j, toIndex - i + 1 , 1);
                i = toIndex;
            }
        }
        sr.end();
    }

    public Element getElementByPosition(int x, int y) {
        if(x < 0 || x >= this.width || y < 0 || y >= this.height) {
            return null;
        }
        return this.matrix[x][y];
    }

    public void swapElements(int x1, int y1, int x2, int y2) {
        Element temp = this.matrix[x1][y1];
        this.matrix[x1][y1] = this.matrix[x2][y2];
        this.matrix[x2][y2] = temp;

        if(this.matrix[x1][y1].type != ElementType.EMPTY) {
            this.matrix[x1][y1].updatePosition(x1, y1);
        }
        if(this.matrix[x2][y2].type != ElementType.EMPTY) {
            this.matrix[x2][y2].updatePosition(x2, y2);
        }
    }

    public void createElementsInBounds(int xMin, int xMax, int yMin, int yMax, ElementType type) {
        xMin = this.clampToViewportWidth(xMin);
        xMax = this.clampToViewportWidth(xMax);
        yMin = this.clampToViewportHeight(yMin);
        yMax = this.clampToViewportHeight(yMax);

        for (int i = xMin; i <= xMax; i++) {
            for (int j = yMin; j <= yMax; j++) {
                this.matrix[i][j] = type.createElementAtIndex(i, j);
            }
        }
    }

    public int clampToViewportWidth(int x) {
        return Math.max(0, Math.min(this.width - 1, x));
    }

    public int clampToViewportHeight(int y) { return Math.max(0, Math.min(this.height - 1, y)); }
}
