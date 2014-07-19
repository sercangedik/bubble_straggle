package com.sercangedik.bubbleStraggle.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.sercangedik.bubbleStraggle.managers.BallManager;
import com.sercangedik.bubbleStraggle.managers.GameManager;

public class Player {
	public static int MOVE_LEFT = -1;
	public static int MOVE_RIGHT = 1;
	public static int STAND = 0;
	
	protected Animation _animation;
	protected TextureRegion[][] _frames;
	protected int _width;
	protected int _height;
	protected Texture _texture;
	protected Vector2 _position;
	protected float _moveSpeed;
	protected int _direction = STAND;
	protected int _live = 3;
	
	public Player(FileHandle fileHandle) {
		this(fileHandle,0,0);
	}
	
	public Player(FileHandle fileHandle, float x, float y) {
		_texture = new Texture(fileHandle);
		_frames = TextureRegion.split(_texture, _texture.getWidth() / 3, _texture.getHeight() / 4);
		_animation = new Animation(0.10f, _frames[0]);
		
		_moveSpeed = 100.0f;
		_width = _texture.getWidth() / 3;
		_height = _texture.getHeight() / 4;
		_position = new Vector2(x,y);
	}
	
	public Animation getAnimation() {
		return _animation;
	}

	public TextureRegion[][] getFrames() {
		return _frames;
	}

	public int getWidth() {
		return _width;
	}

	public int getHeight() {
		return _height;
	}
	
	public void setPosition(float x, float y) {
		_position = new Vector2(x,y);
	}
	
	public Vector2 getPosition() {
		return _position;
	}

	public float getMoveSpeed() {
		return _moveSpeed;
	}

	public void setMoveSpeed(float _moveSpeed) {
		this._moveSpeed = _moveSpeed;
	}
	
	public void move(int direction) {
		_position.x += _moveSpeed * GameManager.getDelta() * direction;
		_direction = direction;
	}
	
	public TextureRegion getCurrentFrame() {
		return getAnimation().getKeyFrame(GameManager.getCurrentFrameTime(),true);
	}
	
	public void checkOverlaps() {
		Rectangle playerRectangle = new Rectangle();
		playerRectangle.set(getPosition().x, getPosition().y, getWidth(), getHeight());
		
		Ball ball;
		
		if((ball = BallManager.checkOverlaps(playerRectangle)) != null)
			GameManager.crashBall(ball);
	}
	
	public void controlHandler(SpriteBatch batch) {
		checkOverlaps();
		
		if(Gdx.input.isKeyPressed(Keys.A)){
			move(Player.MOVE_LEFT);
			_animation = new Animation(0.10f, _frames[1]);
		}
		else if(Gdx.input.isKeyPressed(Keys.D)){
			move(Player.MOVE_RIGHT);
			_animation = new Animation(0.10f, _frames[2]);
		}
		else {
			if(_direction != STAND)
				_animation = new Animation(0.10f, _frames[0]);
			_direction = STAND;
		}
		
		batch.draw(getCurrentFrame(), getPosition().x, getPosition().y);
	}
	
	public int crashBall(Ball ball) {
		return --_live;
	}
}
