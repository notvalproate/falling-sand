package com.valproate.sandapp.elements;

import com.valproate.sandapp.elements.liquid.Water;
import com.valproate.sandapp.elements.solids.immovable.Stone;
import com.valproate.sandapp.elements.solids.movable.Sand;

public enum ElementType {
    EMPTY() {
        @Override
        public Element createElementAtIndex(int x, int y) {
            return EmptyElement.getInstance();
        }
    },
    SAND() {
        @Override
        public Element createElementAtIndex(int x, int y) {
            return new Sand(x, y);
        }
    },
    STONE() {
        @Override
        public Element createElementAtIndex(int x, int y) {
            return new Stone(x, y);
        }
    },
    WATER() {
        @Override
        public Element createElementAtIndex(int x, int y) {
            return new Water(x, y);
        }
    };

    public abstract Element createElementAtIndex(int x, int y);
}
