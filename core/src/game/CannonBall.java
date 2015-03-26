package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class CannonBall {

	private float SHOT_VEL;

	public float size;

	private Vector2 startPos, startVel;
	public Vector2 pos;

	private float t;

	private boolean isMoving;

	private Texture cannonBall;

	public CannonBall(float size, Vector2 startPos) {
		SHOT_VEL = Gdx.graphics.getWidth() * 1.3f;

		this.size = size;

		this.startPos = startPos;
		this.startPos.x -= size / 2;

		restart();

		cannonBall = new Texture("img/cannonball.png");
	}

	/**
	 * @return <code>true</code> if ball fell off screen
	 */
	public boolean update() {
		if (isMoving) {
			float elapsedTime = Gdx.graphics.getDeltaTime();
			t += elapsedTime;

			pos.x = startPos.x + startVel.x * t;
			pos.y = startPos.y + startVel.y * t + 0.5f * Game.GRAVITY * t * t;

			if (pos.x + size < 0 || pos.y + size < 0
					|| pos.x > Gdx.graphics.getWidth()) {
				restart();
				return true;
			}
		}

		return false;
	}

	public void draw(SpriteBatch batch) {
		batch.draw(cannonBall, pos.x, pos.y, size, size);
	}

	public void fire(float shotAngle) {
		if (!isMoving) {
			startVel = new Vector2((float) (SHOT_VEL * Math.cos(shotAngle)),
					(float) (SHOT_VEL * Math.sin(shotAngle)));

			t = 0;

			isMoving = true;
		}
	}

	public void restart() {
		pos = new Vector2(startPos);
		startVel = new Vector2();

		isMoving = false;
	}

}
