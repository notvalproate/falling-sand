package com.valproate.sandapp.elements;

public class EmptyElement extends Element {
    public EmptyElement(int posX, int posY) {
        super(posX, posY);
    }

    @Override
    public void step() { }
}
