package com.valproate;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.valproate.sandapp.SandApp;

public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Falling Sand Simulator");
        config.setWindowedMode(1280, 720);
        config.useVsync(true);
        new Lwjgl3Application(new SandApp(), config);
    }
}