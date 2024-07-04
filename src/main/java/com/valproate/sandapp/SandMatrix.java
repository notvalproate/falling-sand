package com.valproate.sandapp;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.valproate.sandapp.elements.ColorConstants;
import com.valproate.sandapp.elements.Element;
import com.valproate.sandapp.elements.ElementType;
import com.valproate.sandapp.elements.EmptyElement;
import com.valproate.sandapp.elements.solids.Sand;

public class SandMatrix {
    private final int width, height;
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

        this.matrix[width / 2][height / 2] = new Sand(width / 2, height / 2);
    }

    public void step() {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.matrix[i][j].step(this);
            }
        }
    }

    public void render(ShapeRenderer sr) {
        sr.begin(ShapeRenderer.ShapeType.Point);
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                Color color = ColorConstants.getColorByType(this.matrix[i][j].type);
                sr.setColor(color);
                sr.point(i, j, 0);
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

    public void replaceWithEmpty(int x, int y) {
        this.matrix[x][y] = EmptyElement.getInstance();
    }

    public void swapElements(int x1, int y1, int x2, int y2) {
        Element temp = this.matrix[x1][y1];
        this.matrix[x1][y1] = this.matrix[x2][y2];
        this.matrix[x2][y2] = temp;
    }

    public void createElementAtIndex(int x, int y, ElementType type) {
        this.matrix[x][y] = type.createElementAtIndex(x, y);
    }
}
