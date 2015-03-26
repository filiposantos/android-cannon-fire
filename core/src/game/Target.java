package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Target {

	private Texture img;

	private Vector2 pos, dim;
	private Vector2 realPos, realDim;

	public Target(float ballSize) {
		img = new Texture("img/target.png");

		pos = new Vector2();

		float width = 10 * ballSize;
		float height = img.getHeight() * width / img.getWidth();
		dim = new Vector2(width, height);

		realPos = new Vector2(0.375f * dim.x, 0.275f * dim.y);
		realDim = new Vector2(0.891f * dim.x - realPos.x, 0.954f * dim.y
				- realPos.y);

		generateNewPosition();
	}

	public void draw(SpriteBatch batch) {
		batch.draw(img, pos.x, pos.y, dim.x, dim.y);
	}

	public void generateNewPosition() {
		pos.x = MathUtils.random(Gdx.graphics.getWidth() / 3,
				Gdx.graphics.getWidth() - dim.x);
	}

	public boolean hit(CannonBall ball) {
		if (ball.pos.x + ball.size < pos.x + realPos.x
				|| pos.x + realPos.x + realDim.x < ball.pos.x
				|| ball.pos.y > pos.y + realPos.y + realDim.y
				|| pos.y + realPos.y > ball.pos.y + ball.size) {
			return false;
		} else {
			generateNewPosition();
			ball.restart();

			return true;
		}
	}

}
