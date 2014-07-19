package com.sercangedik.bubbleStraggle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.sercangedik.bubbleStraggle.managers.BallManager;
import com.sercangedik.bubbleStraggle.managers.WorldManager;

public class BubbleStraggle extends ApplicationAdapter {
	SpriteBatch batch;
	
	Box2DDebugRenderer debugRenderer;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		
		//BUG
		World a = WorldManager.world;
		
		BallManager.createBalls(3, 3);
		BallManager.refreshBalls();
		
		debugRenderer = new Box2DDebugRenderer();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		debugRenderer.render(WorldManager.world, WorldManager.getCamera().combined);
		WorldManager.world.step(1/60f, 6, 2);
		
		if(Gdx.input.isKeyPressed(Keys.NUM_0)){
			try {
				BallManager.shoot(BallManager.balls.get(0));
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		batch.begin();
		batch.end();
	}
}
