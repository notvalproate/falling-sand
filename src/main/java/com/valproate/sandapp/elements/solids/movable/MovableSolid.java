package com.valproate.sandapp.elements.solids.movable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.valproate.sandapp.SandMatrix;
import com.valproate.sandapp.elements.Element;
import com.valproate.sandapp.elements.ElementType;
import com.valproate.sandapp.elements.solids.Solid;

import java.util.Random;

public abstract class MovableSolid extends Solid {
    private final float velocityThreshold = 128f;
    private final Random random = new Random();

    public MovableSolid(int posX, int posY, ElementType type) {
        super(posX, posY, type);
    }

    @Override
    public void step(SandMatrix matrix) {
        this.velocity.add(SandMatrix.gravity);

        int maxY = (int) (Math.abs(this.velocity.y) * Gdx.graphics.getDeltaTime());
        int sign = this.velocity.y > 0 ? 1 : -1;
        Vector2 lastValidPosition = new Vector2(this.posX, this.posY);
        int i = 1;

        for(; i <= maxY; i++) {
            int newY = this.posY + (i * sign);

            if(newY < 0 || newY >= matrix.height) {
                this.velocity.set(0, 0);
                break;
            }

            Element below = matrix.getElementByPosition(this.posX, newY);
            boolean stoppedFromBelow = actOnNeighbor(below);

            if(stoppedFromBelow) {
//                this.velocity.set(0, 0);
                break;
            }

            lastValidPosition.y = newY;
        }

        if(i == 1) {
            int dir = this.random.nextBoolean() ? 1 : -1;
            Element firstDiagonal = matrix.getElementByPosition(this.posX + dir, this.posY - 1);
            boolean stoppedFirst = actOnNeighbor(firstDiagonal);

            if (stoppedFirst) {
                Element secondDiagonal = matrix.getElementByPosition(this.posX - dir, this.posY - 1);
                boolean stoppedSecond = actOnNeighbor(secondDiagonal);

                if (!stoppedSecond) {
                    lastValidPosition.x = this.posX - dir;
                    lastValidPosition.y = this.posY - 1;
                }
            } else {
                lastValidPosition.x = this.posX + dir;
                lastValidPosition.y = this.posY - 1;
            }
        }

        this.moveToLastValidPosition(matrix, lastValidPosition);
    }

    private boolean actOnNeighbor(Element neighbor) {
        if(neighbor == null) {
            return true;
        }

        switch (neighbor.type) {
            case STONE, SAND -> { return true; }
            default -> { return false; }
        }
    }
}
