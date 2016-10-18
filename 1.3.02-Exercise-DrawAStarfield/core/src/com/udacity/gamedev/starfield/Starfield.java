package com.udacity.gamedev.starfield;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

/**
 * In this exercise we'll draw a star field of white points on a black background. The number of
 * points will be defined by a density parameter that states what proportion of the pixels should be
 * white.
 * <p>
 * <p>
 * One thing to note is we're using two new LibGDX classes, Array, and Vector2. We're using a custom
 * Array type so LibGDX can control the memory, and avoid unfortunate garbage collection events.
 * Vector2 is a super simple class for holding a 2D position. You can find more information in the
 * LibGDX Javadocs, or just by right clicking on the class name, and selecting Go To > Declaration.
 * <p>
 * One new utility class we'll be using in this exercise is com.badlogic.gdx.math.Vector2. You can
 * find more information in the LibGDX Javadocs.
 * <p>
 * Remember you can set up a Desktop run configuration using the dropdown in the toolbar, or you can
 * open the terminal at the bottom of the screen and run
 * <p>
 * $ ./gradlew desktop:run
 */


public class Starfield extends ApplicationAdapter {

    private static final float STAR_DENSITY = 0.01f;
    private static final Color[] COLORS = {new Color(0.7f, 0.4f, 0.6f, 1f),
            new Color(0.6f, 0.4f, 0.8f, 1f),
            new Color(0.4f, 0.6f, 0.6f, 1f),
            new Color(0.7f, 0.9f, 0.8f, 1f),
            new Color(0.9f, 0.9f, 0.4f, 1f)};
    private Random random;
    ShapeRenderer shapeRenderer;
    Array<Vector2> stars;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        random = new Random();
        initStars(STAR_DENSITY);
    }

    public void initStars(float density) {
        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();
        int starCount = (int) (screenHeight * screenWidth * density);
        stars = new Array<Vector2>(starCount);
        for (int i = 0; i < starCount; i++) {
            int x = random.nextInt(screenWidth);
            int y = random.nextInt(screenHeight);
            stars.add(new Vector2(x, y));
        }
    }

    @Override
    public void resize(int width, int height) {
        initStars(STAR_DENSITY);
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Point);
        for (Vector2 star : stars) {
            shapeRenderer.setColor(getRandomColor());
            shapeRenderer.point(star.x, star.y, 0);
        }
        shapeRenderer.end();
    }

    public Color getRandomColor() {
        return COLORS[random.nextInt(COLORS.length)];
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        super.dispose();
    }
}
// TODO: Challenge - Make technicolor stars using shapeRenderer.setColor();
// TODO: Challenge - Draw the Milky Way using a band of denser stars