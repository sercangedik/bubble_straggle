package com.sercangedik.bubbleStraggle.managers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public final class WorldManager {
	public static World world = new World(new Vector2(0.0f, -100.0f), true);
	protected static OrthographicCamera camera = null;
	
	public static OrthographicCamera getCamera() {
		if(camera == null) {
			//@TODO: dynamic camera sizing
			
			camera = new OrthographicCamera();  
	        camera.viewportHeight = 480;  
	        camera.viewportWidth = 640;  
	        camera.position.set(camera.viewportWidth * .5f, camera.viewportHeight * .5f, 0f);  
	        camera.update();  
		}
		
		
		return camera;
	}
}
