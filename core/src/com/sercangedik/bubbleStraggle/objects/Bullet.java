package com.sercangedik.bubbleStraggle.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.sercangedik.bubbleStraggle.managers.BallManager;
import com.sercangedik.bubbleStraggle.managers.GameManager;
import com.sercangedik.bubbleStraggle.managers.WorldManager;

public class Bullet {
	protected int _width;
	protected int _height;
	protected Texture _texture;
	protected Vector2 _position;
	protected float _moveSpeed;
	protected boolean _isFired = false;
	
	public Bullet(FileHandle fileHandle) {
		this(fileHandle,0,0);
	}
	
	public Bullet(FileHandle fileHandle, float x, float y) {
		_texture = new Texture(fileHandle);
		
		_moveSpeed = 300.0f;
		_width = _texture.getWidth();
		_height = _texture.getHeight();
		_position = new Vector2(x,y);
	}
	
	public int getWidth() {
		return _width;
	}

	public int getHeight() {
		return _height;
	}
	
	public boolean isFired() {
		return _isFired;
	}

	public void fire() {
		if(_isFired) {
			return;
		}
		Gdx.audio.newMusic(Gdx.files.internal("sounds/ates.wav")).play();

		_isFired = true;
		setPosition(GameManager.getPlayer().getPosition().x, -getHeight());
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
	
	public void move() {
		_position.y += _moveSpeed * GameManager.getDelta();
		
		if(_position.y >= WorldManager.getCamera().viewportHeight) {
			_isFired = false;
		}
	}
	
	public void checkOverlaps() {
		Rectangle playerRectangle = new Rectangle();
		playerRectangle.set(getPosition().x, getPosition().y+getHeight()+50, getWidth(), 30f);
		
		Ball ball;
		
		if((ball = BallManager.checkOverlaps(playerRectangle)) != null)
			try {
				BallManager.shoot(ball);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
	}
	
	public void controlHandler(SpriteBatch batch) {
		if(Gdx.input.isKeyPressed(Keys.SPACE)) {
			fire();
		}
		
		if(_isFired) {
			checkOverlaps();
			move();
			
			batch.draw(_texture, getPosition().x, getPosition().y+60);
		}
	}
}
