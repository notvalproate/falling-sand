package com.valproate.sandapp.elements.solids.immovable;

import com.badlogic.gdx.math.Vector2;
import com.valproate.sandapp.elements.ElementType;

public class Stone extends ImmovableSolid {
    public Stone(int posX, int posY) {
        super(posX, posY, ElementType.STONE);
        this.velocity = new Vector2(0f, 0f);
    }
}
