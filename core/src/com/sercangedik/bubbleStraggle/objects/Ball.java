package com.sercangedik.bubbleStraggle.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.sercangedik.bubbleStraggle.managers.WorldManager;

public class Ball {
	private int _level;
	private Vector2 _position;
	private float _density;
	private float _friction;
	private float _restitution;
	private Body _body;
	private BodyDef _bodyDef = new BodyDef();
	private CircleShape _shape;
	private boolean _isCreated = false;
	
	public Ball(int level) {
		this(level,0,0,1.0f,new Vector2(0.0f,0.0f));
	}
	
	public Ball(int level, Vector2 position) {
		this(level,0,0,1.0f,position);
	}
	
	public Ball(int level, float density, float friction, float restitution, Vector2 position) {
		setLevel(level);
		setDensity(density);
		setFriction(friction);
		setRestitution(restitution);
		setPosition(position);
	}
	
	protected void createShape() {
		_shape = new CircleShape();
		_shape.setRadius(_level*10.0f/2);
	}
	
	public Body getBody() {
		return _body;
	}


	public float getRestitution() {
		return _restitution;
	}

	public void setRestitution(float restitution) {
		this._restitution = restitution;
	}

	public float getFriction() {
		return _friction;
	}

	public void setFriction(float friction) {
		this._friction = friction;
	}

	public float getDensity() {
		return _density;
	}

	public void setDensity(float density) {
		this._density = density;
	}

	public CircleShape getShape() {
		return _shape;
	}

	public Vector2 getPosition() {
		return _body.getPosition();
	}

	public void setPosition(float x, float y) {
		this._position.x = x;
		this._position.y = y;
	}
	
	public void setPosition(Vector2 position) {
		this._position = position;
	}

	public int getLevel() {
		return _level;
	}

	public void setLevel(int level) {
		this._level = level;
		createShape();
	}
	
	public void create() {
		_bodyDef = new BodyDef();
		_bodyDef.type = BodyType.DynamicBody;
		_bodyDef.position.set(_position);
		_body = WorldManager.world.createBody(_bodyDef);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = _shape;
		fixtureDef.density = _density; 
		fixtureDef.friction = _friction;
		fixtureDef.restitution = _restitution;
		
		_body.createFixture(fixtureDef);
		
		_isCreated = true;
	}
	
	public void refresh() {
		if(!_isCreated)
			create();
		
		_shape.dispose();
	}
}
