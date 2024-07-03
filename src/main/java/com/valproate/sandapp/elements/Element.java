package com.valproate.sandapp.elements;

public abstract class Element {
    public int posX, posY;
    public ElementType type;

    public Element(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public abstract void step();

    public enum ElementType {
        EMPTY,
        SAND
    }
}
