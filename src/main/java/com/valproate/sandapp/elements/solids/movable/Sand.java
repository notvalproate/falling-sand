package com.valproate.sandapp.elements.solids.movable;

import com.badlogic.gdx.math.Vector2;
import com.valproate.sandapp.elements.ElementType;

public class Sand extends MovableSolid {
    public Sand(int posX, int posY) {
        super(posX, posY, ElementType.SAND);
        this.velocity = new Vector2(0, -256);
    }
}
