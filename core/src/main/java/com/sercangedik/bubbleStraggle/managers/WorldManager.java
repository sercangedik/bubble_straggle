/*
 * Bu class genel itibari ile oyunun dunyasinin olusturuldugu
 * ornek : duvarlar, camera, yeniden olusturulma gibi. siniftir.
 * Oyunun baslangicinda buyuk rol ustlenir.
 * */

package com.sercangedik.bubbleStraggle.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public final class WorldManager {
	public static World world = new World(new Vector2(0.0f, -50.0f), true);
	
	public static float BOTTOM_WALL_X = 0.0f;
	public static float BOTTOM_WALL_HEIGHT = 30.0f;
	public static float BOTTOM_WALL_WIDTH = getCamera().viewportWidth;
	public static float BOTTOM_WALL_Y = BOTTOM_WALL_HEIGHT;
	
	public static float LEFT_WALL_Y = getCamera().viewportHeight;
	public static float LEFT_WALL_WIDTH = 1.0f;
	public static float LEFT_WALL_HEIGHT = getCamera().viewportHeight - BOTTOM_WALL_Y - BOTTOM_WALL_HEIGHT;
	public static float LEFT_WALL_X = LEFT_WALL_WIDTH;
	
	public static float RIGHT_WALL_Y = getCamera().viewportHeight;
	public static float RIGHT_WALL_WIDTH = 1.0f;
	public static float RIGHT_WALL_HEIGHT = getCamera().viewportHeight - BOTTOM_WALL_Y - BOTTOM_WALL_HEIGHT;
	public static float RIGHT_WALL_X = getCamera().viewportWidth - RIGHT_WALL_WIDTH;
	
	public static short GROUP_WALL = 1;
	public static short GROUP_BALL = -2;
	
	protected static OrthographicCamera _camera = null;
	
	public static OrthographicCamera getCamera() {
		if(_camera == null) {
			//@TODO: dynamic camera sizing
			
			_camera = new OrthographicCamera();  
			/*switch (Gdx.app.getType()) {
			case Android:
				_camera.viewportHeight = Gdx.graphics.getHeight();  
				_camera.viewportWidth = Gdx.graphics.getWidth();
				break;
			case Desktop:
				_camera.viewportHeight = 480;  
				_camera.viewportWidth = 640;
				break;
			default:
				break;
			}*/
			
			_camera.viewportHeight =  Gdx.graphics.getHeight();  
			_camera.viewportWidth = Gdx.graphics.getWidth();
			_camera.position.set(_camera.viewportWidth * .5f, _camera.viewportHeight * .5f, 2f);  
			_camera.update();  
		}
		
		
		return _camera;
	}
	
	public static void createWall(float x, float y, float w, float h) {
		BodyDef groundBodyDef = new BodyDef();  
		groundBodyDef.position.set(new Vector2(x, y));  

		Body groundBody = world.createBody(groundBodyDef);  

		PolygonShape groundBox = new PolygonShape();  
		groundBox.setAsBox(w, h);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = groundBox;
		fixtureDef.density = 1.0f;
		fixtureDef.filter.groupIndex = GROUP_WALL;
		
		groundBody.createFixture(fixtureDef);
		groundBox.dispose();
	}
	
	public static void restart() {
		clean();
		
		createWall(BOTTOM_WALL_X,BOTTOM_WALL_Y,BOTTOM_WALL_WIDTH,BOTTOM_WALL_HEIGHT);
		createWall(LEFT_WALL_X,LEFT_WALL_Y,LEFT_WALL_WIDTH,LEFT_WALL_HEIGHT);
		createWall(RIGHT_WALL_X,RIGHT_WALL_Y,RIGHT_WALL_WIDTH,RIGHT_WALL_HEIGHT);
		
	}
	
	public static void clean() {
		Array<Body> bodies = new Array<Body>();
		
		
		WorldManager.world.getBodies(bodies);
		
		for (Body body : bodies) {
			WorldManager.world.destroyBody(body);
		}
	}
}
