package com.valproate.sandapp.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.valproate.sandapp.SandMatrix;
import com.valproate.sandapp.elements.ElementType;

public class UserInputProcessor extends InputAdapter {
    private int viewportHeight;
    private final SandMatrix matrix;

    public UserInputProcessor(SandMatrix matrix, int viewportHeight) {
        this.matrix = matrix;
        this.viewportHeight = viewportHeight;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(button == Input.Buttons.LEFT) {
            matrix.createElementAtIndex(screenX, this.viewportHeight - screenY, ElementType.SAND);
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        matrix.createElementAtIndex(screenX, this.viewportHeight - screenY, ElementType.SAND);
        return true;
    }
}
