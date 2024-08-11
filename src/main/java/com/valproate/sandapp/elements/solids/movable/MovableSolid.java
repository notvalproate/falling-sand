package com.valproate.sandapp.elements.solids.movable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.valproate.sandapp.SandMatrix;
import com.valproate.sandapp.elements.Element;
import com.valproate.sandapp.elements.ElementType;
import com.valproate.sandapp.elements.solids.Solid;

public abstract class MovableSolid extends Solid {
    private final float velocityThreshold = 128f;

    public MovableSolid(int posX, int posY, ElementType type) {
        super(posX, posY, type);
    }

    @Override
    public void step(SandMatrix matrix) {
        this.velocity.add(SandMatrix.gravity);

        int maxY = (int) (Math.abs(this.velocity.y) * Gdx.graphics.getDeltaTime());
        int sign = this.velocity.y > 0 ? 1 : -1;
        Vector2 lastValidPosition = new Vector2(this.posX, this.posY);

        for(int i = 1; i <= maxY; i++) {
            int newY = this.posY + (i * sign);

            if(newY < 0 || newY >= matrix.height) {
                this.velocity.set(0, 0);
                break;
            }

            Element below = matrix.getElementByPosition(this.posX, newY);
            boolean stoppedFromBelow = actOnNeighbor(below);

            if(stoppedFromBelow) {
                this.velocity.set(0, 0);
                break;
            }

            lastValidPosition.y = newY;
        }

        this.moveToLastValidPosition(matrix, lastValidPosition);
    }

    private boolean actOnNeighbor(Element neighbor) {
        switch (neighbor.type) {
            case STONE, SAND -> { return true; }
            default -> { return false; }
        }
    }
}
