package com.valproate.sandapp.input;

import com.badlogic.gdx.InputAdapter;
import com.valproate.sandapp.SandMatrix;

public class InputProcessor extends InputAdapter {
    SandMatrix matrix;

    public InputProcessor(SandMatrix matrix) {
        this.matrix = matrix;
    }


}
