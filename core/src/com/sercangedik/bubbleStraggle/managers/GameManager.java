package com.sercangedik.bubbleStraggle.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sercangedik.bubbleStraggle.objects.Ball;
import com.sercangedik.bubbleStraggle.objects.Bullet;
import com.sercangedik.bubbleStraggle.objects.Player;

public final class GameManager {
	protected static Player _player = null;
	protected static Bullet _bullet = null;
	protected static float _delta = 0;
	protected static float _currentFrameTime = 0;
	protected static float _gamePoint = 0;
	
	private static Texture wallTexture;
	private static Sprite wallSprite;
	private static Texture pointTexture;
	private static Sprite pointSprite;
	private static Texture liveTexture;
	private static Sprite liveSprite;
	private static Texture headTexture;
	private static Sprite headSprite1;
	private static Sprite headSprite2;
	private static Sprite headSprite3;
	private static float scaling = (float) 1.2;
	
	public static Player getPlayer() {
		if(_player == null) {
			_player = new Player(Gdx.files.internal("images/man.png"),Player.RIGHT);
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
		Gdx.audio.newMusic(Gdx.files.internal("sounds/topvurusu.mp3")).play();
		addGamePoint(30 / ball.getLevel());
	}

	public static void gameOver() {
		
	}
	
	public static void setTextures(SpriteBatch batch) {
		
		 
	    wallTexture = new Texture("images/wall.jpg");
	    wallSprite = new Sprite(wallTexture);
	 
	    pointTexture = new Texture("images/puantablosu.png");
	    pointSprite = new Sprite(pointTexture);
	  
	    liveTexture = new Texture("images/can.png");
	    liveSprite = new Sprite(liveTexture);
	    
	    headTexture = new Texture("images/head.png");
	    headSprite1 = new Sprite(headTexture);
	    headSprite2 = new Sprite(headTexture);
	    headSprite3 = new Sprite(headTexture);
	    
	    headSprite1.scale((float) 1.05);
	    headSprite2.scale((float) 1.05);
	    headSprite3.scale((float) 1.05);
	    
	    pointSprite.setPosition(490, 5);
	    liveSprite.setPosition(20, 5);
	    headSprite1.setPosition(30, 25);
	    headSprite2.setPosition(70, 25);
	    headSprite3.setPosition(110, 25);
		pointSprite.setScale(scaling);
		liveSprite.setScale(scaling);
		//wallSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		wallSprite.draw(batch);
		pointSprite.draw(batch);
		liveSprite.draw(batch);
		headSprite1.draw(batch);
		headSprite2.draw(batch);
		headSprite3.draw(batch);
	}
}
