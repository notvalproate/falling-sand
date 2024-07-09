package com.valproate.sandapp.elements.solids.immovable;

import com.valproate.sandapp.elements.ElementType;

public class Stone extends ImmovableSolid {
    public Stone(int posX, int posY) {
        super(posX, posY, ElementType.STONE);
    }
}
