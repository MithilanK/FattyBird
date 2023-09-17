package com.mithilank.fattybird.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mithilank.fattybird.Fattybird;
import com.mithilank.fattybird.objects.MeatBall;

public class GameOverState implements Screen {
    BitmapFont font;
    private final Fattybird Game;
    Stage stage;
    Button restart;
    Skin skin;
    TextureAtlas buttonAtlas;
    Button.ButtonStyle style;
    SpriteBatch bg;
    SpriteBatch bgbg;
    OrthographicCamera cam;
    public GameOverState(final Fattybird game) {
        this.Game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("Buttons.pack"));
        skin.addRegions(buttonAtlas);
        style = new Button.ButtonStyle();
        style.up = skin.getDrawable("Redo");
        style.down = skin.getDrawable("RedoPressed");
        style.checked = skin.getDrawable("Redo");
        restart = new Button(style);
        restart.setX(Gdx.graphics.getWidth() / 2f - (restart.getWidth() / 2f));
        restart.setY(400f);
        font = new BitmapFont(Gdx.files.internal("Fonts/g.fnt"));
        font.setColor(Color.BLACK);
        restart.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                if (Game.getScreen() == Game.over) {
                    System.out.println("Working");
                    Game.play = new PlayState(Game);
                    Game.setScreen(Game.play);
                }
            }
        });
        stage.addActor(restart);

        bg = new SpriteBatch();
        bgbg = new SpriteBatch();
        cam = new OrthographicCamera();
        cam.setToOrtho(false);






    }

    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        bgbg.begin();
        bgbg.draw(this.Game.play.bgimg, 0, 0);
        bgbg.end();
        bg.setProjectionMatrix(cam.combined);
        bg.begin();
        for (MeatBall ball : this.Game.play.balls) {
            ball.draw(bg);
        }
        bg.draw(this.Game.play.floor.getTexture(), this.Game.play.floor.getX(),this.Game.play.floor.getY());
        bg.draw(this.Game.play.floor.getTexture(), this.Game.play.floor.getX() - 540,this.Game.play.floor.getY());
        bg.draw(this.Game.play.floor.getTexture(), this.Game.play.floor.getX() + 540,this.Game.play.floor.getY());
        this.Game.play.player.object.draw(bg);

        cam.position.x = this.Game.play.camera.position.x;
        cam.position.y = this.Game.play.camera.position.y;
        cam.update();

        GlyphLayout glyphLayout = new GlyphLayout();
        String item = "Score: (" + String.valueOf(Game.play.player.score) + ")";
        glyphLayout.setText(font,item);
        float w = glyphLayout.width;
        font.draw(bg, "Score: (" + String.valueOf(Game.play.player.score) + ")", ((float) Gdx.graphics.getWidth() - w) * 1, 70);
        bg.end();


        stage.draw();




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

    }
}
