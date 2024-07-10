package com.valproate.sandapp.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.valproate.sandapp.SandMatrix;

public abstract class Element {
    public int posX, posY;
    public Vector2 velocity;
    public ElementType type;
    public Color color;

    public Element(int posX, int posY, ElementType type) {
        this.posX = posX;
        this.posY = posY;
        this.type = type;
        this.color = ColorConstants.getColorByType(type);
    }

    public void updatePosition(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public abstract void step(SandMatrix matrix);
}
