package com.sercangedik.bubbleStraggle.managers;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.sercangedik.bubbleStraggle.objects.Ball;

public final class BallManager {
	public static ArrayList<Ball> balls = new ArrayList<Ball>();
	
	
	public static void createBalls(int count,int level) {
		Ball ball;
		float positionStep = Gdx.graphics.getWidth() / (count+1);
		Vector2 position = new Vector2(positionStep, 50.0f);
		
		for (int i = 0; i < count; i++) {
			ball = new Ball(level, position);
			position.x += positionStep;
			
			addBall(ball);
		}
	}
	
	public static void addBall(Ball ball) {
		balls.add(ball);
	}
	
	public static void removeBall(Ball ball) {
		balls.remove(ball);
	}
	
	public static void clean() {
		balls = new ArrayList<Ball>();
	}
	
	public void shoot(Ball ball) throws CloneNotSupportedException {
		int level = ball.getLevel() / 2;
		float density = ball.getDensity();
		float friction = ball.getFriction();
		float restitution = ball.getRestitution();
		Vector2 position = ball.getPosition();
		
		Ball ball1 = new Ball(level, density, friction, restitution, position);
		Ball ball2 = new Ball(level, density, friction, restitution, position);
		
		//@TODO: add force
		
		addBall(ball1);
		addBall(ball2);
		
		ball1.refresh(false);
		ball2.refresh(false);
		
		WorldManager.world.destroyBody(ball.getBody());
		removeBall(ball);
	}
}
