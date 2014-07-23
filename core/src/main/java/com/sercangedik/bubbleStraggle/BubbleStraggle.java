package com.sercangedik.bubbleStraggle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sercangedik.bubbleStraggle.managers.GameManager;

public class BubbleStraggle extends ApplicationAdapter {
	SpriteBatch batch;
	Box2DDebugRenderer debugRenderer;
	Texture wallTexture;
	Sprite wallSprite;
	private Viewport viewport;

	@Override
	public void create() {
		batch = new SpriteBatch();

		GameManager.restart();
		GameManager.getTextures();

		// screen fit ?
		debugRenderer = new Box2DDebugRenderer();

	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		GameManager.beforeRender();

		batch.begin();
		GameManager.renderGame(batch);
		GameManager.getPlayer().controlHandler(batch);
		GameManager.getBullet().controlHandler(batch);

		batch.end();

		GameManager.renderTimerBar();

		// debugRenderer.render(WorldManager.world,
		// WorldManager.getCamera().combined);
	}

}
