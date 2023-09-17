package com.mithilank.fattybird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mithilank.fattybird.Fattybird;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 540;
		config.height = 720;
		config.fullscreen = false;
		config.useGL30 = false;
		config.forceExit = true;
		config.vSyncEnabled = false;
		config.foregroundFPS = 60;
		config.backgroundFPS =60;
		new LwjglApplication(new Fattybird(), config);
	}
}
