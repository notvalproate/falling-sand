package com.valproate.sandapp.elements.solids.movable;

import com.valproate.sandapp.SandMatrix;
import com.valproate.sandapp.elements.Element;
import com.valproate.sandapp.elements.ElementType;
import com.valproate.sandapp.elements.solids.Solid;

public abstract class MovableSolid extends Solid {
    public MovableSolid(int posX, int posY, ElementType type) {
        super(posX, posY, type);
    }

    @Override
    public void step(SandMatrix matrix) {
        if(actOnNeighbour(matrix, this.posX, this.posY - 1)) {
            return;
        }

        if(actOnNeighbour(matrix, this.posX - 1, this.posY - 1)) {
            return;
        }

        actOnNeighbour(matrix, this.posX + 1, this.posY - 1);
    }

    private boolean actOnNeighbour(SandMatrix matrix, int x, int y) {
        Element e = matrix.getElementByPosition(x, y);

        if(e == null) {
            return true;
        }

        if(e.type == ElementType.EMPTY) {
            matrix.swapWithEmpty(this.posX, this.posY, x, y);
            return true;
        }

        return false;
    }
}
