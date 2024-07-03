package com.valproate;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.valproate.sandapp.SandApp;

public class Main {
    public static void main(String[] args) {
        int width = 1280;
        int height = 720;

        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Falling Sand Simulator");
        config.setWindowedMode(width, height);
        config.setForegroundFPS(60);
        new Lwjgl3Application(new SandApp(width, height), config);
    }
}