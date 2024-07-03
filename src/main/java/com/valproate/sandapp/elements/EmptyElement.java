package com.valproate.sandapp.elements;

import com.valproate.sandapp.SandMatrix;

public class EmptyElement extends Element {
    private static EmptyElement empty = null;

    private EmptyElement(int posX, int posY) {
        super(posX, posY, ElementType.EMPTY);
    }

    public static EmptyElement getInstance() {
        if(empty == null) {
            empty = new EmptyElement(0, 0);
        }
        return empty;
    }

    @Override
    public void step(SandMatrix matrix) {
        // Do nothing
    }
}
