package com.sercangedik.bubbleStraggle.managers;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
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
			Vector2 position = new Vector2(positionX, Gdx.graphics.getHeight() - 150);
			ball = new Ball(level, position);
			
			balls.add(ball);
			ball.show();
		}
	}
	
	public static void clean() {
		balls = new ArrayList<Ball>();
	}
	
	public static void shoot(Ball ball) throws CloneNotSupportedException {
		ball.shoot();
		
		if(ball.getLevel() == 1) {
			balls.remove(ball);
			return;
		}
		
		int level = ball.getLevel() - 1;
		float density = ball.getDensity();
		float friction = ball.getFriction();
		float restitution = ball.getRestitution();
		Vector2 position = ball.getPosition();
		
		Ball ball1 = new Ball(level, density, friction, restitution, position);
		Ball ball2 = new Ball(level, density, friction, restitution, position);
		//radiuslar yariya dusmuyor olabilir ?
		//patlayan topu silip yerine yenilerini ekleyebilirim. 
		
		balls.add(ball1);
		balls.add(ball2);
		
		ball1.show(Ball.EFFECT_BLOW_UP_LEFT);
		ball2.show(Ball.EFFECT_BLOW_UP_RIGHT);
		
		balls.remove(ball);
	}
	
	private static boolean overlaps(Ball ball, Rectangle rectangle) {
		float x = ball.getPosition().x;
		float y = ball.getPosition().y;
		float width = ball.getWidth();
		float height = ball.getHeight();

		return x < rectangle.x + rectangle.width && x + width > rectangle.x && y < rectangle.y + rectangle.height && y + height > rectangle.y;
	}
	
	public static Ball checkOverlaps(Rectangle rectangle) {
		for (Ball ball : balls) {
			if(overlaps(ball, rectangle))
				return ball;
		}
		
		return null;
	}
}
