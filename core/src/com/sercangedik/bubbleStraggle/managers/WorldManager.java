package com.sercangedik.bubbleStraggle.managers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public final class WorldManager {
	public static World world = new World(new Vector2(0.0f, -100.0f), true);
}
