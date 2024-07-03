package com.valproate.sandapp.elements;

import com.valproate.sandapp.SandMatrix;

public abstract class Element {
    public int posX, posY;
    public ElementType type;

    public Element(int posX, int posY, ElementType type) {
        this.posX = posX;
        this.posY = posY;
        this.type = type;
    }

    public void updatePosition(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public abstract void step(SandMatrix matrix);

    public enum ElementType {
        EMPTY,
        SAND;
    }
}
