package com.valproate.sandapp.elements;

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
    };

    public abstract Element createElementAtIndex(int x, int y);
}
