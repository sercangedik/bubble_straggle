package com.sercangedik.bubbleStraggle.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sercangedik.bubbleStraggle.objects.Ball;
import com.sercangedik.bubbleStraggle.objects.Bullet;
import com.sercangedik.bubbleStraggle.objects.Player;

public final class GameManager {
	protected static Player _player = null;
	protected static Bullet _bullet = null;
	protected static float _delta = 0;
	protected static float _currentFrameTime = 0;
	protected static float _gamePoint = 0;
	
	private static TextureRegion textureRegion;
	private static Texture backgroundTexture;
	private static Sprite backgroundSprite;
	private static Texture wallTexture;
	private static Sprite wallSprite;
	private static Texture pointTexture;
	private static Sprite pointSprite;
	private static Texture peakTexture;
	private static Sprite peakSprite;
	private static Texture liveTexture;
	private static Sprite liveSprite;
	private static float scaling = (float) 1.2;
	
	public static Player getPlayer() {
		if(_player == null) {
			_player = new Player(Gdx.files.internal("images/man.png"),320,57);
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
	
	public static void crashBall(Ball ball) {
		Gdx.audio.newMusic(Gdx.files.internal("sounds/olum.mp3")).play();
		if(_player.crashBall(ball) == 0)
			gameOver();
		else
			_player.setPosition(320,57);
	}

	private static void gameOver() {
		
	}
	
	public static void setTextures(SpriteBatch batch) {
		
		textureRegion = new TextureRegion(new Texture(Gdx.files.internal("images/bg.jpg")));
		textureRegion.flip(false, true);
		backgroundTexture = new Texture("images/bg.jpg");
	    backgroundSprite = new Sprite(backgroundTexture);
	    
	    wallTexture = new Texture("images/wall.jpg");
	    wallSprite = new Sprite(wallTexture);
	 
	    pointTexture = new Texture("images/puantablosu.png");
	    pointSprite = new Sprite(pointTexture);
	    
	    peakTexture = new Texture("images/peak.jpg");
	    peakSprite = new Sprite(peakTexture);
	    
	    liveTexture = new Texture("images/can.png");
	    liveSprite = new Sprite(liveTexture);
	    
	    
		backgroundSprite.setPosition(10, 60);
	    pointSprite.setPosition(490, 5);
		peakSprite.setPosition(270, 5);
		liveSprite.setPosition(20, 5);
		pointSprite.setScale(scaling);
		liveSprite.setScale(scaling);
		
		wallSprite.draw(batch);
		backgroundSprite.draw(batch);
		pointSprite.draw(batch);
		peakSprite.draw(batch);
		liveSprite.draw(batch);
	}
}
