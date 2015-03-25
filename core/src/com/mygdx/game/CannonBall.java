package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class CannonBall {

	private float SHOT_VEL;

	private float size;

	private Vector2 startPos, pos, startVel;
	private float t;

	private boolean isMoving;

	private Texture cannonBall;

	public CannonBall(float size, Vector2 startPos) {
		SHOT_VEL = Gdx.graphics.getWidth();

		this.size = size;

		this.startPos = startPos;
		pos = new Vector2(startPos);
		startVel = new Vector2();

		isMoving = false;

		cannonBall = new Texture("img/cannonball.png");
	}

	public void update() {
		float elapsedTime = Gdx.graphics.getDeltaTime();
		t += elapsedTime;

		if (isMoving) {
			pos.x = startPos.x + startVel.x * t;
			pos.y = startPos.y + startVel.y * t + 0.5f * MyGdxGame.GRAVITY * t
					* t;
		}
	}

	public void draw(SpriteBatch batch) {
		batch.draw(cannonBall, pos.x, pos.y, size, size);
	}

	public void fire(float shotAngle) {
		startVel = new Vector2((float) (SHOT_VEL * Math.cos(shotAngle)),
				(float) (SHOT_VEL * Math.sin(shotAngle)));

		t = 0;

		isMoving = true;
	}

}
