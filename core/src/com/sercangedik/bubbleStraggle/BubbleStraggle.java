package com.sercangedik.bubbleStraggle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.sercangedik.bubbleStraggle.managers.BallManager;
import com.sercangedik.bubbleStraggle.managers.GameManager;
import com.sercangedik.bubbleStraggle.managers.WorldManager;

public class BubbleStraggle extends ApplicationAdapter {
	SpriteBatch batch;
	
	Box2DDebugRenderer debugRenderer;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		
		//BUG
		World a = WorldManager.world;
		WorldManager.createWall(WorldManager.BOTTOM_WALL_X,WorldManager.BOTTOM_WALL_Y,WorldManager.BOTTOM_WALL_WIDTH,WorldManager.BOTTOM_WALL_HEIGHT);
		WorldManager.createWall(WorldManager.LEFT_WALL_X,WorldManager.LEFT_WALL_Y,WorldManager.LEFT_WALL_WIDTH,WorldManager.LEFT_WALL_HEIGHT);
		WorldManager.createWall(WorldManager.RIGHT_WALL_X,WorldManager.RIGHT_WALL_Y,WorldManager.RIGHT_WALL_WIDTH,WorldManager.RIGHT_WALL_HEIGHT);
		
		BallManager.createBalls(4, 3);
		BallManager.refreshBalls();
		
		debugRenderer = new Box2DDebugRenderer();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		GameManager.beforeRender();
		debugRenderer.render(WorldManager.world, WorldManager.getCamera().combined);
		
		batch.begin();
		GameManager.getPlayer().controlHandler(batch);
		batch.end();
	}
}
