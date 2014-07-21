package com.sercangedik.bubbleStraggle.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.sercangedik.bubbleStraggle.objects.Ball;
import com.sercangedik.bubbleStraggle.objects.Bullet;
import com.sercangedik.bubbleStraggle.objects.Player;

public final class GameManager {
	protected static Player _player = null;
	protected static Bullet _bullet = null;
	protected static float _delta = 0;
	protected static float _currentFrameTime = 0;
	protected static float _gamePoint = 0;
	
	private static float scaling = (float) 1.2;
	private static Texture wallTexture;
	private static Sprite wallSprite;
	private static Texture pointTexture;
	private static Sprite pointSprite;	
	private static BitmapFont font;
	private static Texture liveTexture;
	private static Sprite liveSprite;
	private static Texture headTexture;
	private static Sprite headSprite;
	
	
	public static Player getPlayer() {
		if(_player == null) {
			_player = new Player(Gdx.files.internal("images/man.png"),Player.CENTER);
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
	
	public static float getGamePoint() {
		return _gamePoint;
	}
	
	public static void shootBall(Ball ball) {
		Gdx.audio.newMusic(Gdx.files.internal("sounds/topvurusu.mp3")).play();
		_gamePoint += 30 / ball.getLevel();
	}

	public static void gameOver() {
		restart();
	}
	
	public static void restart() {
		Array<Body> bodies = new Array<Body>();
		WorldManager.world.getBodies(bodies);
		
		for (Body body : bodies) {
			WorldManager.world.destroyBody(body);
		}
		
		WorldManager.restart();
		BallManager.restart(3, 2);
		_gamePoint = 0;
		_player = new Player(Gdx.files.internal("images/man.png"),Player.CENTER);
	}
	
	public static void getTextures(){
		
		//Walls
		wallTexture = new Texture("images/wall.jpg");
		wallSprite = new Sprite(wallTexture);
		
		//Score
		pointTexture = new Texture("images/puantablosu.png");
		pointSprite = new Sprite(pointTexture);	
		font = new BitmapFont();
				
		pointSprite.setPosition(490, 5);
		pointSprite.setScale(scaling);
		
		font.scale((float) 1.05);
		font.setColor(0.0f, 0.0f, 0.0f, 1.0f);
		
		//Live
		liveTexture = new Texture("images/can.png");
		liveSprite = new Sprite(liveTexture);
				
		liveSprite.setPosition(20, 5);
		liveSprite.setScale(scaling);
		
		headTexture = new Texture("images/head.png");
		headSprite = new Sprite(headTexture);
		headSprite.scale(1.05f);

	}
	
	public static void renderGame(SpriteBatch batch) {
	
	    //wallSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	  	wallSprite.draw(batch);
		pointSprite.draw(batch);

		font.draw(batch, Float.toString(getGamePoint()), 500, 45);
		liveSprite.draw(batch);

		
		for (int i = 0; i < getPlayer().getLives(); i++) {
	
			headSprite.setPosition(30 * (i+1) + i * 10, 25);
			headSprite.draw(batch);
		}
		
		
		
		//tricks (:
		if(Gdx.input.isKeyPressed(Keys.NUM_0)){
			try {
				if(BallManager.balls.size() > 0)
					BallManager.shoot(BallManager.balls.get(0));
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(Gdx.input.isKeyPressed(Keys.R)) {
			restart();
		}
		else if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			getPlayer().setPosition(Player.LEFT);
		}
		else if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			getPlayer().setPosition(Player.RIGHT);
		}
		else if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			getPlayer().setPosition(Player.CENTER);
		}
		else if(Gdx.input.isKeyPressed(Keys.NUM_1)) {
			BallManager.createBalls(1, 2);
		}
		else if(Gdx.input.isKeyPressed(Keys.NUM_2)) {
			getPlayer().crash();
		}
	}
}
