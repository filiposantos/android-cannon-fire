package com.mygdx.game;

import utils.Touch;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class MyGdxGame extends ApplicationAdapter implements InputProcessor {

	public static float GRAVITY;

	private SpriteBatch batch;
	private Texture cannon;

	private FreeTypeFontGenerator generator;
	private FreeTypeFontParameter param;

	private int fontSize, fontMargin;
	private BitmapFont font;

	private int score;

	private float cannonWidth, cannonHeight, cannonOrigin, cannonAngle;

	private CannonBall ball;

	private Touch touch;

	@Override
	public void create() {
		Gdx.input.setInputProcessor(this);

		GRAVITY = -Gdx.graphics.getWidth();

		batch = new SpriteBatch();
		cannon = new Texture("img/cannon.png");

		generator = new FreeTypeFontGenerator(
				Gdx.files.internal("fonts/engravers-old-english.ttf"));
		param = new FreeTypeFontParameter();

		fontSize = (int) (Gdx.graphics.getWidth() * 0.1);
		fontMargin = (int) (fontSize * 0.1);
		param.size = fontSize;
		font = generator.generateFont(param);

		score = 0;

		cannonWidth = Gdx.graphics.getWidth() * 0.23f;
		cannonHeight = cannonWidth * cannon.getHeight() / cannon.getWidth();

		cannonOrigin = cannonHeight / 2;

		cannonAngle = 0;

		ball = new CannonBall(cannonHeight * 0.2f, new Vector2(cannonOrigin,
				cannonOrigin));

		touch = new Touch();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 2);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// update
		if (touch.touched) {
			touch.touched = false;

			cannonAngle = (float) Math
					.atan((float) (Gdx.graphics.getHeight() - touch.y)
							/ touch.x);
		}

		ball.update();

		// draw
		batch.begin();

		ball.draw(batch);

		batch.draw(cannon, 0f, 0f, cannonOrigin, cannonOrigin, cannonWidth,
				cannonHeight, 1f, 1f, cannonAngle * MathUtils.radDeg, 0, 0,
				cannon.getWidth(), cannon.getHeight(), false, false);

		renderScore();

		batch.end();
	}

	private void renderScore() {
		String scoreStr = Integer.toString(score);

		font.setColor(Color.BLACK);
		font.draw(batch, scoreStr, fontMargin, Gdx.graphics.getHeight()
				- fontMargin);
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		touch.x = screenX;
		touch.y = screenY;
		touch.touched = true;

		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		touch.x = screenX;
		touch.y = screenY;
		touch.touched = true;

		ball.fire(cannonAngle);

		score++;

		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		touch.x = screenX;
		touch.y = screenY;
		touch.touched = true;

		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
