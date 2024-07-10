package com.valproate.sandapp.elements.liquid;

import com.valproate.sandapp.SandMatrix;
import com.valproate.sandapp.elements.Element;
import com.valproate.sandapp.elements.ElementType;

import java.util.Random;

public class Liquid extends Element {
    private final Random random = new Random();

    public Liquid(int posX, int posY, ElementType type) {
        super(posX, posY, type);
    }

    @Override
    public void step(SandMatrix matrix) {
        if(actOnNeighbor(matrix, this.posX, this.posY - 1)) {
            return;
        }

        int direction = random.nextBoolean() ? 1 : -1;

        if(actOnNeighbor(matrix, this.posX + direction, this.posY)) {
            return;
        }

        actOnNeighbor(matrix, this.posX - direction, this.posY);
    }

    private boolean actOnNeighbor(SandMatrix matrix, int x, int y) {
        Element e = matrix.getElementByPosition(x, y);

        if(e == null) {
            return false;
        }

        if(e.type == ElementType.EMPTY) {
            matrix.swapElements(this.posX, this.posY, x, y);
            return true;
        }

        if(e.type == ElementType.WATER) {
            matrix.swapElements(this.posX, this.posY, x, y);
            return true;
        }

        return false;
    }
}
