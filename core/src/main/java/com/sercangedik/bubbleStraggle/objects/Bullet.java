/* Oyuncunun ates etmesi esnasinda gerceklesen olaylari
 * kontrol eder. Merminin hareketi, carpismasi gibi durumlari 
 * olusturur ve bazi durumlari ball manager (carpisma) sinifi ile 
 * paylasarak oyunun senaryosunun ilerlemesini saglar.
 * */

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

	private void fire() {
		if(_isFired)
			return;

		Gdx.audio.newMusic(Gdx.files.internal("sounds/ates.wav")).play();

		_isFired = true;
		_position.x = GameManager.getPlayer().getFirePositionX();
		_position.y = -_height;
	}
	
	private void move() {
		_position.y += _moveSpeed * GameManager.getDelta();
		
		if(_position.y + _height >= WorldManager.getCamera().viewportHeight) {
			_isFired = false;
		}
	}

	private void checkOverlaps() {
		Rectangle playerRectangle = new Rectangle();
		playerRectangle.set(_position.x, _position.y + _height - 50, _width, 50);
		
		Ball ball;
		
		if((ball = BallManager.checkOverlaps(playerRectangle)) != null)
			try {
				remove();
				BallManager.shoot(ball);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
	}
	
	
	public void setMoveSpeed(float _moveSpeed) {
		this._moveSpeed = _moveSpeed;
	}
	
	public void remove(){
		_position.y = WorldManager.getCamera().viewportHeight;
	}
	
	

	public void controlHandler(SpriteBatch batch) {
		if(Gdx.input.isKeyPressed(Keys.SPACE) || Gdx.input.isTouched()) {
			fire();
		}
		if(_isFired) {
			move();
			batch.draw(_texture, _position.x, _position.y+60);	
			checkOverlaps();
		}
	}
}
