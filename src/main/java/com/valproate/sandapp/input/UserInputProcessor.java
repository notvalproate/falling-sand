package com.valproate.sandapp.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.valproate.sandapp.SandMatrix;
import com.valproate.sandapp.elements.ElementType;

public class UserInputProcessor extends InputAdapter {
    private int viewportHeight;
    private final SandMatrix matrix;
    private int brushSize;

    public UserInputProcessor(SandMatrix matrix, int viewportHeight) {
        this.matrix = matrix;
        this.viewportHeight = viewportHeight;
        this.brushSize = 10;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(button == Input.Buttons.LEFT) {
            Bounds b = getSquareBounds(screenX, screenY);
            matrix.createElementsInBounds(b.x1, b.x2, this.viewportHeight - b.y2, this.viewportHeight - b.y1, ElementType.SAND);
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Bounds b = getSquareBounds(screenX, screenY);
        matrix.createElementsInBounds(b.x1, b.x2, this.viewportHeight - b.y2, this.viewportHeight - b.y1, ElementType.SAND);
        return true;
    }

    private Bounds getSquareBounds(int x, int y) {
        int halfSize = this.brushSize / 2;

        int x1 = x - halfSize;
        int x2 = x + halfSize;
        int y1 = y - halfSize;
        int y2 = y + halfSize;

        if(this.brushSize % 2 == 0) {
            x1++;
            y1++;
        }

        return new Bounds(x1, x2, y1, y2);
    }

    private class Bounds {
        public int x1, x2, y1, y2;

        public Bounds(int x1, int x2, int y1, int y2) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }
    }
}
