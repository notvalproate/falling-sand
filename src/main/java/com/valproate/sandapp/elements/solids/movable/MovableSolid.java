package com.valproate.sandapp.elements.solids.movable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.valproate.sandapp.SandMatrix;
import com.valproate.sandapp.elements.Element;
import com.valproate.sandapp.elements.ElementType;
import com.valproate.sandapp.elements.solids.Solid;

public abstract class MovableSolid extends Solid {
    private final float velocityThreshold = -128f;

    public MovableSolid(int posX, int posY, ElementType type) {
        super(posX, posY, type);
    }

    @Override
    public void step(SandMatrix matrix) {
        this.velocity.add(SandMatrix.gravity);

        int maxY = (int) (Math.abs(this.velocity.y) * Gdx.graphics.getDeltaTime());
        int sign = this.velocity.y > 0 ? 1 : -1;
        Vector2 lastValidPosition = new Vector2(this.posX, this.posY);
        Vector2 newPosition = new Vector2(this.posX, this.posY);

        for(int i = 1; i <= maxY; i++) {
            int newY = this.posY + (i * sign);
            newPosition.y = newY;

            if(newY >= 0 && newY < matrix.height) {
                Element neighbor = matrix.getElementByPosition(this.posX, newY);

                if (neighbor == this) continue;

                boolean stopped = actOnNeighbor(neighbor, matrix, newPosition, i == maxY, i == 1, lastValidPosition);

                if(stopped) {
                    break;
                }

                lastValidPosition.x = this.posX;
                lastValidPosition.y = newY;
            } else {
                matrix.swapElements(this.posX, this.posY, (int) lastValidPosition.x, (int) lastValidPosition.y);
            }
        }
    }

    private boolean actOnNeighbor(Element neighbor, SandMatrix matrix, Vector2 newPosition, boolean isLast, boolean isFirst, Vector2 lastValidPosition) {
        if(neighbor.type == ElementType.EMPTY) {
            if(isLast) {
                matrix.swapElements(this.posX, this.posY, (int) newPosition.x, (int) newPosition.y);
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
            if(isFirst) {
                int newX = neighbor.posX + (Math.random() > 0.5 ? 1 : -1);

                if(newX >= 0 && newX < matrix.width) {
                    newPosition.x = newX;
                    Element diagonalNeighbor = matrix.getElementByPosition(newX, neighbor.posY);

                    actOnNeighbor(diagonalNeighbor, matrix, newPosition, true, false, lastValidPosition);
                }

                return true;
            } else {
                moveToLastValidPosition(matrix, lastValidPosition);
            }
            velocity.y = Math.min(-64f, (velocity.y + neighbor.velocity.y) / 2);
            return true;
        }

        return false;
    }
}
