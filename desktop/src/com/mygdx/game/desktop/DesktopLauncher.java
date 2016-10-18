package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.PhageWarsGame;

public class DesktopLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.height = PhageWarsGame.HEIGHT;
        config.width = PhageWarsGame.WIDTH;

        new LwjglApplication(new PhageWarsGame(), config);
    }
}
