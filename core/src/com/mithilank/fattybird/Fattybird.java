package com.mithilank.fattybird;

import com.badlogic.gdx.Game;
import com.mithilank.fattybird.gamestates.GameOverState;
import com.mithilank.fattybird.gamestates.PlayState;
import com.mithilank.fattybird.objects.Player;


public class Fattybird extends Game {
	public PlayState play;
	public GameOverState over;
	@Override
	public void create () {
		Fattybird game = this;

		over = new GameOverState(game);
		play = new PlayState(game);
		this.setScreen(play);

	}

	@Override
	public void render () {
		super.render();
	}


	@Override
	public void dispose () {

	}

	public void replay () {
		this.setScreen(play);
	}
}
