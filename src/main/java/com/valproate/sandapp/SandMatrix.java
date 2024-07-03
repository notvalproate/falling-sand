package com.valproate.sandapp;

import com.valproate.sandapp.elements.Element;

public class SandMatrix {
    private int width, height;
    private Element[][] matrix;

    SandMatrix(int width, int height) {
        this.width = width;
        this.height = height;
        this.matrix = new Element[width][height];


    }
}
