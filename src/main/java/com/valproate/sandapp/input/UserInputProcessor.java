package com.valproate.sandapp.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.valproate.sandapp.SandMatrix;
import com.valproate.sandapp.elements.ElementType;

public class UserInputProcessor extends InputAdapter {
    private int viewportHeight;
    private final SandMatrix matrix;
    private int brushSize;
    private MouseState mouseState;
    private Vector2 lastMousePosition;
    private ElementType brushType;

    public UserInputProcessor(SandMatrix matrix, int viewportHeight) {
        this.matrix = matrix;
        this.viewportHeight = viewportHeight;
        this.brushSize = 10;
        this.brushType = ElementType.SAND;
        mouseState = MouseState.UP;
    }

    public void handleInputs() {
        if(mouseState == MouseState.DOWN) {
            Bounds b = getSquareBounds((int) lastMousePosition.x, (int) lastMousePosition.y);
            matrix.createElementsInBounds(b.x1, b.x2, this.viewportHeight - b.y2, this.viewportHeight - b.y1, this.brushType);
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        mouseState = MouseState.DOWN;
        lastMousePosition = new Vector2(screenX, screenY);
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        lastMousePosition = new Vector2(screenX, screenY);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        mouseState = MouseState.UP;
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.NUM_1 -> this.brushType = ElementType.SAND;
            case Input.Keys.NUM_2 -> this.brushType = ElementType.STONE;
            case Input.Keys.NUM_3 -> this.brushType = ElementType.WATER;
            case Input.Keys.NUM_0 -> this.brushType = ElementType.EMPTY;
        }

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

    private enum MouseState {
        UP, DOWN
    }
}
