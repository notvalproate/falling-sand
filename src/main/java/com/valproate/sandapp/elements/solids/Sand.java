package com.valproate.sandapp.elements.solids;

import com.valproate.sandapp.SandMatrix;
import com.valproate.sandapp.elements.Element;
import com.valproate.sandapp.elements.ElementType;

public class Sand extends Solid {
    public Sand(int posX, int posY) {
        super(posX, posY, ElementType.SAND);
    }

    @Override
    public void step(SandMatrix matrix) {
        Element e = matrix.getElementByPosition(this.posX, this.posY - 1);

        System.out.println("Sand step");

        if(e == null) {
            //System.out.println("Replacing with empty");
            //matrix.replaceWithEmpty(this.posX, this.posY);
            return;
        }

        if(e.type == ElementType.EMPTY) {
            matrix.swapElements(this.posX, this.posY, this.posX, this.posY - 1);
            this.posY--;
        }
    }
}
