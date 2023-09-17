package com.mithilank.fattybird.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mithilank.fattybird.gamestates.PlayState;

import java.util.List;

public class Player
{
    Texture playerimg;
    public GameObject object;
    public float yvel;
    public float xvel;
    float rotvel;
    public int score;
    public boolean dead;
    PlayState play;

    public Player (float x, float y) {
        playerimg = new Texture("Player.png");
        object = new GameObject(playerimg, x, y);
    }

    public void update (List<MeatBall> killers, Floor floor, PlayState play) throws InterruptedException {
        this.play = play;
        float delta = 1;
        yvel -=0.4;
        for (MeatBall ball : killers) {
            object.Update();
            if (object.rect.collidesWith(ball.rect)) {
                dead = true;
            }
        }
        if (object.rect.collidesWith(floor.rect)) {
            dead = true;
        }
        rotvel -= 0.1;
        if (!(dead)) {xvel = (((object.getX() + 4) + ((float) score / 2.5f)));}
        object.setX(xvel);
        for (MeatBall ball : killers) {
            object.Update();
            if (object.rect.collidesWith(ball.rect)) {
                dead = true;
            }
        }
        if (object.rect.collidesWith(floor.rect)) {
            dead = true;
        }
        if (!(dead)) {if (Gdx.input.isKeyJustPressed(Input.Keys.W) || Gdx.input.isKeyJustPressed(Input.Keys.I)|| Gdx.input.isKeyJustPressed(Input.Keys.UP) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            yvel = 10f / delta;
            rotvel = 15f / delta;

        }}
        if(!(dead)) {for (MeatBall ball : killers) {
            object.Update();
            if (object.rect.collidesWith(ball.rect)) {
                dead = true;
            }
        }}
        if(!(dead)) {if (object.rect.collidesWith(floor.rect)) {
            dead = true;
        }}


        if (object.getRotation() < -45) {
            object.setRotation(-45);
        }
        if (!(dead)) {if (object.getRotation() > 45) {
            object.setRotation(45);
            rotvel = 0;
        }}
        if (!(dead)) {object.changeY(yvel);}
        if (!(dead)) {object.rotate(rotvel);};

        if (!(dead)) {while (object.getY() + object.getHeight() > Gdx.graphics.getHeight()) {
            object.setY(object.getY() - 0.5f);
            yvel = 0;
        }}

        if (dead) {dieterribly(floor);}



    }

    public void dieterribly (Floor floor) throws InterruptedException {
        object.setY(object.getY() + yvel);
        if (object.getRotation() > 270) {
            object.setRotation(270);
        }
        if (floor.rect.collidesWith(object.rect)) {
            play.SwitchDeath();

        }
    }


    public void render (SpriteBatch batch) {
        object.draw(batch);

    }

    public void dispose () {

    }


}
