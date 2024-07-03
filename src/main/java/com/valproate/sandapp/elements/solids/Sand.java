package com.valproate.sandapp.elements.solids;

public class Sand extends Solid {
    public Sand(int posX, int posY) {
        super(posX, posY);
    }

    @Override
    public void step() {
        if(this.type == ElementType.EMPTY) {
            return;
        }
    }
}
