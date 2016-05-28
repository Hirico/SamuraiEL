package com.samurai.el.field;

import com.badlogic.gdx.Gdx;
import com.samurai.el.maingame.GameInstance;
import com.samurai.el.resource.Resources;

public class Background4 extends Background {
	public Background4() {
		super();
		background = Resources.getInstance().background4;
		music = Resources.getInstance().bgm08;
		music.setVolume(Gdx.app.getPreferences("volumePref").getFloat("musicVolume", 0.5f));
		music.setLooping(true);
		music.play();
	}
	
	@Override
	public void render() {
		backBatch.setProjectionMatrix(GameInstance.getInstance().backgroundCamera.combined);
		backBatch.begin();
		background.draw(backBatch);
		backBatch.end();
	}
}
