package com.valproate.sandapp;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.valproate.sandapp.input.UserInputProcessor;
import org.lwjgl.opengl.GL20;

public class SandApp extends ApplicationAdapter {
    int width, height;
    SandMatrix matrix;
    UserInputProcessor inputProcessor;
    ShapeRenderer sr;

    public SandApp(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void create() {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        sr = new ShapeRenderer();

        this.matrix = new SandMatrix(width, height);

        this.inputProcessor = new UserInputProcessor(matrix, this.height);
        Gdx.input.setInputProcessor(inputProcessor);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);

        inputProcessor.handleInputs();

        matrix.step();
        matrix.render(sr);
    }

    @Override
    public void dispose() {
        sr.dispose();
    }
}
