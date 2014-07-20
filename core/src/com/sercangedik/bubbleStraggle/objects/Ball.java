package com.sercangedik.bubbleStraggle.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.sercangedik.bubbleStraggle.managers.WorldManager;

public class Ball {
	public final static int EFFECT_DROP = 1;
	public final static int EFFECT_BLOW_UP_LEFT = 2;
	public final static int EFFECT_BLOW_UP_RIGHT = 3;
	
	private int _level;
	private Vector2 _position;
	private float _density;
	private float _friction;
	private float _restitution;
	private Body _body;
	private BodyDef _bodyDef;
	
	public Ball(int level) {
		this(level,new Vector2(0.0f,0.0f));
	}
	
	public Ball(int level, Vector2 position) {
		this(level,1.0f,0,1f,position);
	}
	
	public Ball(int level, float density, float friction, float restitution, Vector2 position) {
		_level = level;
		_density = density;
		_friction = friction;
		_restitution = restitution;
		_position = position;
	}

	public float getRestitution() {
		return _restitution;
	}

	public float getFriction() {
		return _friction;
	}

	public float getDensity() {
		return _density;
	}

	public Vector2 getPosition() {
		if(_body != null)
			return _body.getPosition();
		else
			return new Vector2();
	}
	
	public float getWidth() {
		return _level*20/2;
	}
	
	public float getHeight() {
		return _level*20/2;
	}

	public int getLevel() {
		return _level;
	}
	
	protected void addEffect(int effect) {
		if(effect == EFFECT_DROP) {
			if(_position.x <= WorldManager.getCamera().viewportWidth / 2)
				_body.applyLinearImpulse(400.0f, 0f, _body.getLocalCenter().x, _body.getLocalCenter().y, false);
			else
				_body.applyLinearImpulse(-400.0f, 0f, _body.getLocalCenter().x, _body.getLocalCenter().y, false);
		}
		else if(effect == EFFECT_BLOW_UP_LEFT) {
			_body.applyLinearImpulse(-400,400f,  _body.getLocalCenter().x, _body.getLocalCenter().y, true);
		}
		else if(effect == EFFECT_BLOW_UP_RIGHT) {
			_body.applyLinearImpulse(400,400f,  _body.getLocalCenter().x, _body.getLocalCenter().y, true);
		}
		
	}
	
	public void show(int effect) {
		_bodyDef = new BodyDef();
		_bodyDef.type = BodyType.DynamicBody;
		_bodyDef.position.set(_position);
		
		_body = WorldManager.world.createBody(_bodyDef);
		
		CircleShape shape = new CircleShape();
		shape.setRadius(_level*20/2);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = _density; 
		fixtureDef.friction = _friction;
		fixtureDef.restitution = _restitution;
		fixtureDef.filter.groupIndex = WorldManager.GROUP_BALL;
		
		addEffect(effect);
		
		_body.createFixture(fixtureDef);
		shape.dispose();
	}
	
	public void show() {
		show(EFFECT_DROP);
	}
	
	public void shoot() {
		WorldManager.world.destroyBody(_body);
	}
}
