package com.sercangedik.bubbleStraggle.managers;

import com.badlogic.gdx.Gdx;
import com.sercangedik.bubbleStraggle.objects.Ball;
import com.sercangedik.bubbleStraggle.objects.Bullet;
import com.sercangedik.bubbleStraggle.objects.Player;

public final class GameManager {
	protected static Player _player = null;
	protected static Bullet _bullet = null;
	protected static float _delta = 0;
	protected static float _currentFrameTime = 0;
	protected static float _gamePoint = 0;
	
	public static Player getPlayer() {
		if(_player == null) {
			_player = new Player(Gdx.files.internal("images/man.png"),0,WorldManager.BOTTOM_WALL_HEIGHT);
		}
		
		return _player;
	}
	
	public static Bullet getBullet() {
		if(_bullet == null) {
			_bullet = new Bullet(Gdx.files.internal("images/bullet.png"),0,WorldManager.BOTTOM_WALL_HEIGHT);
		}
		
		return _bullet;
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
	
	public static float addGamePoint(float point) {
		_gamePoint += point;
		
		return _gamePoint;
	}
	
	public static float getGamePoint() {
		return _gamePoint;
	}
	
	public static void shootBall(Ball ball) {
		addGamePoint(30 / ball.getLevel());
	}
	
	public static void crashBall(Ball ball) {
		if(_player.crashBall(ball) == 0)
			gameOver();
		else
			_player.setPosition(0, 0);
	}

	private static void gameOver() {
		
	}
}
