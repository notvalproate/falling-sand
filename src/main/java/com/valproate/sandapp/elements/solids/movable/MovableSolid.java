package com.valproate.sandapp.elements.solids.movable;

import com.badlogic.gdx.Gdx;
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
        this.velocity.add(SandMatrix.gravity);

        int maxY = (int) (Math.abs(this.velocity.y) * Gdx.graphics.getDeltaTime());
        int sign = this.velocity.y > 0 ? 1 : -1;

        for(int i = 1; i <= maxY; i++) {
            boolean isLast = i == maxY;
            int newY = this.posY + (i * sign);

            if(newY >= 0 && newY < matrix.height) {
                Element neighbor = matrix.getElementByPosition(this.posX, newY);

                if (neighbor == this) continue;

                boolean stopped = actOnNeighbor(neighbor, matrix, newY, isLast);

                if(stopped) {
                    break;
                }
            } else {
                matrix.swapElements(this.posX, this.posY, this.posX, matrix.clampToViewportHeight(newY));
            }
        }
    }

    private boolean actOnNeighbor(Element neighbor, SandMatrix matrix, int newY, boolean isLast) {
        if(neighbor.type == ElementType.EMPTY) {
            if(isLast) {
                matrix.swapElements(this.posX, this.posY, this.posX, newY);
                return true;
            } else {
                return false;
            }
        }

        if(neighbor.type == ElementType.WATER) {
            matrix.swapElements(this.posX, this.posY, neighbor.posX, neighbor.posY);
            return true;
        }

        if(neighbor.type == ElementType.STONE || neighbor.type == ElementType.SAND) {
            matrix.swapElements(this.posX, this.posY, neighbor.posX, neighbor.posY + 1);
            velocity.y = -64f;
            return true;
        }

        return false;
    }
}
