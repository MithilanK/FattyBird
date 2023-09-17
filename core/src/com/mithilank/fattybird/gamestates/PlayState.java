package com.mithilank.fattybird.gamestates;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mithilank.fattybird.Fattybird;
import com.mithilank.fattybird.objects.Floor;
import com.mithilank.fattybird.objects.MeatBall;
import com.mithilank.fattybird.objects.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayState implements Screen {
    private final Fattybird game;

    public PlayState(Fattybird game) {
        this.game = game;
        batch = new SpriteBatch();
        uibatch = new SpriteBatch();
        bgbatch = new SpriteBatch();
        bgimg = new Texture("BackGround.jpg");
        player = new Player(50, 500);
        floor = new Floor();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false);
        balls = new ArrayList<>();
        balls.add(new MeatBall(540, 450));
        balls.add(new MeatBall(1080, 200));
        balls.add(new MeatBall(1620, 450));
        screenedge = 0;
        font = new BitmapFont(Gdx.files.internal("Fonts/g.fnt"));
        font.setColor(Color.BLACK);
    }
    SpriteBatch batch;
    Texture bgimg;
    Player player;
    Floor floor;
    OrthographicCamera camera;
    List<MeatBall> balls;
    float screenedge;
    SpriteBatch bgbatch;
    SpriteBatch uibatch;
    BitmapFont font;


    public float MeatBallRange() {
        int min = 150;
        int max = 500;

        return (float)Math.floor(Math.random()*(max-min+1)+min);
    }
    @Override
    public void show() {

    }

    public void SwitchDeath () throws InterruptedException {
        Thread.sleep(90L);

        this.game.setScreen(game.over);
    }


    @Override
    public void render(float delta) {
        if (font == null) {
            font = new BitmapFont(Gdx.files.internal("f.fnt"));
        }
        try {
            player.update(balls, floor, this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        floor.Follow(player.object);
        for (MeatBall ball : balls) {
            if (ball.getX() + ball.getWidth() < screenedge) {
                ball.setX(ball.getX() + 1692);
                ball.setY(MeatBallRange());
                ball.Update();
                player.score++;


            }
        }

        ScreenUtils.clear(1, 0, 0, 1f);
        bgbatch.begin();
        bgbatch.draw(bgimg, 0, 0);
        bgbatch.end();
        batch.setProjectionMatrix(camera.combined);
        camera.position.x = player.object.getX() + 200;
        screenedge = camera.position.x - camera.viewportWidth / 2;
        batch.begin();

        batch.draw(bgimg, player.object.getX() - 74, 0);
        floor.Render(batch);
        for (MeatBall ball : balls) {
            ball.draw(batch);
        }

        player.render(batch);
        camera.update();
        batch.end();
        uibatch.begin();

        GlyphLayout glyphLayout = new GlyphLayout();
        String item = "Score: (" + String.valueOf(player.score) + ")";
        glyphLayout.setText(font,item);
        float w = glyphLayout.width;
        font.draw(uibatch, "Score: (" + String.valueOf(player.score) + ")", ((float) Gdx.graphics.getWidth() - w)/ 2, 70);
        uibatch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        bgimg.dispose();
        font.dispose();
        player.dispose();
    }
}
