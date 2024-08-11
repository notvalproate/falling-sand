package com.valproate.sandapp.elements.liquid;

import com.badlogic.gdx.math.Vector2;
import com.valproate.sandapp.elements.ElementType;

public class Water extends Liquid {
    public Water(int posX, int posY) {
        super(posX, posY, ElementType.WATER);
        this.velocity = new Vector2(0f, -256f);
        this.dispersionRate = 4;
    }
}
