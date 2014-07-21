package com.sercangedik.bubbleStraggle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.sercangedik.bubbleStraggle.managers.GameManager;
import com.sercangedik.bubbleStraggle.managers.WorldManager;

public class BubbleStraggle extends ApplicationAdapter {
	SpriteBatch batch;
	Box2DDebugRenderer debugRenderer;
	Texture wallTexture;
	Sprite wallSprite;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		GameManager.restart();
		GameManager.getTextures();
		debugRenderer = new Box2DDebugRenderer();
		
	}

	@Override
	public void render () {
		//Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		GameManager.beforeRender();
		
		batch.begin();
		GameManager.renderGame(batch);
		GameManager.getPlayer().controlHandler(batch);
		GameManager.getBullet().controlHandler(batch);
		batch.end();
		
		debugRenderer.render(WorldManager.world, WorldManager.getCamera().combined);
	}
}
