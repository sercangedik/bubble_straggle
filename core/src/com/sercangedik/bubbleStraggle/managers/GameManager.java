package com.sercangedik.bubbleStraggle.managers;

import com.badlogic.gdx.Gdx;
import com.sercangedik.bubbleStraggle.objects.Player;

public final class GameManager {
	protected static Player _player = null;
	protected static float _delta = 0;
	protected static float _currentFrameTime = 0;
	
	public static Player getPlayer() {
		if(_player == null) {
			_player = new Player(Gdx.files.internal("images/man.png"),0,WorldManager.BOTTOM_WALL_HEIGHT);
		}
		
		return _player;
	}

	public static float getDelta() {
		return _delta;
	}

	public static float setDelta() {
		_delta = Gdx.graphics.getDeltaTime();
		_currentFrameTime += _delta;
		
		return _delta;
	}

	public static float getCurrentFrameTime() {
		return _currentFrameTime;
	}
	
	public static void beforeRender() {
		setDelta();
		WorldManager.world.step(1/60f, 6, 2);
	}
}
