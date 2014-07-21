package com.sercangedik.bubbleStraggle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sercangedik.bubbleStraggle.managers.GameManager;
import com.sercangedik.bubbleStraggle.managers.WorldManager;

public class BubbleStraggle extends ApplicationAdapter {
	SpriteBatch batch;
	Box2DDebugRenderer debugRenderer;
	Texture wallTexture;
	Sprite wallSprite;
	private Viewport viewport;
	Array<Body> bodies = new Array<Body>();
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		GameManager.restart();
		GameManager.getTextures();
		//viewport = new FitViewport(800, 480, WorldManager.getCamera());
		//screen fit ?
		debugRenderer = new Box2DDebugRenderer();
		
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		GameManager.beforeRender();
		
		batch.begin();
		GameManager.renderGame(batch);
		GameManager.getPlayer().controlHandler(batch);
		GameManager.getBullet().controlHandler(batch);
		WorldManager.world.getBodies(bodies);
		for(Body body : bodies)
			if(body.getUserData() != null && body.getUserData() instanceof Sprite){
				Sprite sprite = (Sprite) body.getUserData();
				sprite.setPosition(body.getPosition().x-20f, body.getPosition().y-20f);
				sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
				sprite.draw(batch);				
			}
		batch.end();
		
		debugRenderer.render(WorldManager.world, WorldManager.getCamera().combined);
	}
	
	@Override
	public void resize(int width, int height) {
	   // viewport.update(width, height);
	}
	

}
