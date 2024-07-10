package com.valproate.sandapp.elements.liquid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.valproate.sandapp.SandMatrix;
import com.valproate.sandapp.elements.Element;
import com.valproate.sandapp.elements.ElementType;

import java.util.Random;

public class Liquid extends Element {
    public int dispersionRate;
    private final Random random = new Random();

    public Liquid(int posX, int posY, ElementType type) {
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

        if(neighbor.type == ElementType.STONE || neighbor.type == ElementType.SAND || neighbor.type == ElementType.WATER) {
            if(isFirst) {
                int newX = this.posX + (random.nextBoolean() ? dispersionRate : -1 * dispersionRate);
                Element adjacentNeighbor = matrix.getElementByPosition(newX, this.posY);
                newPosition.x = newX;
                newPosition.y = this.posY;

                if(adjacentNeighbor != null) {
                    actOnNeighbor(adjacentNeighbor, matrix, newPosition, true, false, lastValidPosition);
                    return true;
                }
            }

            moveToLastValidPosition(matrix, lastValidPosition);
            return true;
        }

        return false;
    }
}
