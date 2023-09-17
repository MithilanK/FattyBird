package com.mithilank.fattybird.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import static com.mithilank.fattybird.util.UtilFunc.closestMultiple;

public class MeatBall extends GameObject {

    public MeatBall(float x, float y) {
        super(new Texture("Meatball.png"), x, y);
        this.rect.x += 40;
        this.rect.y += 40;
        this.rect.width = 16 * 10;
        this.rect.height = 16 * 10;
    }

    @Override
    public void Update () {
        rect.x = this.getX() + 40;
        rect.y = this.getY() + 40;
    }

    public void follow(GameObject object) {
        setX(closestMultiple(object.getX(), Gdx.graphics.getWidth()));

    }
}
