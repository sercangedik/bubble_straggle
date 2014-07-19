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
		float positionX = 0;
		
		for (int i = 0; i < count; i++) {
			positionX += positionStep;
			Vector2 position = new Vector2(positionX, Gdx.graphics.getHeight() - 50);
			
			ball = new Ball(level, position);
			
			addBall(ball);
		}
	}
	
	public static void addBall(Ball ball) {
		balls.add(ball);
	}
	
	public static void removeBall(Ball ball) {
		WorldManager.world.destroyBody(ball.getBody());
		balls.remove(ball);
	}
	
	public static void clean() {
		balls = new ArrayList<Ball>();
	}
	
	public static void shoot(Ball ball) throws CloneNotSupportedException {
		if(ball.getLevel() == 1) {
			removeBall(ball);
			return;
		}
		
		int level = ball.getLevel() - 1;
		float density = ball.getDensity();
		float friction = ball.getFriction();
		float restitution = ball.getRestitution();
		Vector2 position = ball.getPosition();
		
		Ball ball1 = new Ball(level, density, friction, restitution, position);
		Ball ball2 = new Ball(level, density, friction, restitution, position);
		
		addBall(ball1);
		addBall(ball2);
		
		ball1.refresh();
		ball2.refresh();
		
		ball1.getBody().applyLinearImpulse(-10.0f, 10.0f, 0, 0, true);
		ball2.getBody().applyLinearImpulse(10.0f, 10.0f, 0, 0, true);
		
		removeBall(ball);
	}
	
	public static void refreshBalls() {
		for (Ball ball : balls) {
			ball.refresh();
		}
	}
}
