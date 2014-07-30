/*
 * Oyundaki karakterin genel sinifidir. Getter,setter
 * fonksiyonlari bulunur. Oyuncunun olusturulmasi, animasyonu,
 * sag ve sol hareketleri, konumunun belirlenmesi gibi durumlari 
 * olusturur ve kontrol eder. 
 * */
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
import com.sercangedik.bubbleStraggle.managers.WorldManager;

public class Player {
	public static int MOVE_LEFT = -1;
	public static int MOVE_RIGHT = 1;
	public static int STAND = 0;
	
	public static int LEFT = -1;
	public static int CENTER = 0;
	public static int RIGHT = 1;
	
	public final static int DEFAULT_LIVE = 3;
	
	protected Animation _animation;
	protected TextureRegion[][] _frames;
	protected int _width;
	protected int _height;
	protected Texture _texture;
	protected Vector2 _position;
	protected float _moveSpeed;
	protected int _direction = STAND;
	protected int _live = DEFAULT_LIVE;
	
	public Player(FileHandle fileHandle) {
		this(fileHandle,0,0);
	}
	
	public Player(FileHandle fileHandle, int position) {
		this(fileHandle,0,0);
		setPosition(position);
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
	
	public int getHeight() {
		return _height;
	}
	
	public int getWidth() {
		return _width;
	}
	
	public void setPosition(int position) {
		float x = 0;
		float y = WorldManager.BOTTOM_WALL_HEIGHT * 2;
		
		if(position == LEFT) {
			x = WorldManager.LEFT_WALL_WIDTH;
		}
		else if(position == CENTER) {
			x = WorldManager.getCamera().viewportWidth / 2 - _width / 2;
		}
		else if(position == RIGHT) {
			x = WorldManager.getCamera().viewportWidth - _width - WorldManager.RIGHT_WALL_WIDTH;
		}
		
		_position.x = x;
		_position.y = y;
	}
	
	private TextureRegion getCurrentFrame() {
		return _animation.getKeyFrame(GameManager.getCurrentFrameTime(),true);
	}
	
	public void crash() {
		Gdx.audio.newMusic(Gdx.files.internal("sounds/olum.mp3")).play();
		
		--_live;
		
		if(_live <= 0)
			GameManager.gameOver();
		else
			born();
	}
	
	public boolean isCrashed() {
		Rectangle playerRectangle = new Rectangle();
		playerRectangle.set(_position.x, _position.y, _width, _height);
		
		if(BallManager.checkOverlaps(playerRectangle) != null)
			return true;
		else
			return false;
	}
	
	private void checkOverlaps() {
		if(isCrashed())
			crash();
	}
	
	private void move(int direction) {
		_position.x += _moveSpeed * GameManager.getDelta() * direction;
		_direction = direction;
	}
	
	public int getLives() {
		return _live;
	}
	
	public float getFirePositionX() {
		return _position.x + _width / 2;
	}
	
	public float getPlayerPositionX(){
		return _position.x;
	}
	
	public void setMoveSpeed(float moveSpeed) {
		_moveSpeed = moveSpeed;
	}
	
	public void controlHandler(SpriteBatch batch) {
		if(_live <= 0)
			return;
		
		checkOverlaps();
		
		if(Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.getAccelerometerY() < 0){
			if(WorldManager.LEFT_WALL_X + 5f < GameManager.getPlayer().getPlayerPositionX()){
				move(Player.MOVE_LEFT);
				_animation = new Animation(0.10f, _frames[1]);
			}
		}
		else if(Gdx.input.isKeyPressed(Keys.RIGHT) ||  Gdx.input.getAccelerometerY() > 0){
			if(WorldManager.RIGHT_WALL_X - 40f > GameManager.getPlayer().getPlayerPositionX()){
				move(Player.MOVE_RIGHT);
				_animation = new Animation(0.10f, _frames[2]);
			}
		}
		else {
			if(_direction != STAND)
				_animation = new Animation(0.10f, _frames[0]);
			_direction = STAND;
		}
		
		batch.draw(getCurrentFrame(), _position.x, _position.y);
	}
	
	public void born() {
		GameManager.restartLevel();
	}
	
	public void clean() {
		_live = DEFAULT_LIVE;
	}
}
