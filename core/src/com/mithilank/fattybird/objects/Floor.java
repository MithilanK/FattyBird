package com.mithilank.fattybird.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.mithilank.fattybird.util.UtilFunc.closestMultiple;

public class Floor extends GameObject{

    public Floor() {

        super(new Texture("Floor.png"), 0f, 0f);
        this.rect.x -= 540;
        this.rect.width += 1080;
    }

    @Override
    public void Update () {
        this.rect.x = this.getX() - 540;
    }


    public void Follow(GameObject object) {
        setX(closestMultiple(object.getX(), Gdx.graphics.getWidth()));
        Update();


    }


    public void Render(SpriteBatch batch) {
        batch.draw(this.getTexture(), this.getX(), this.getY());
        batch.draw(this.getTexture(), this.getX() - Gdx.graphics.getWidth(), this.getY());
        batch.draw(this.getTexture(), this.getX() + Gdx.graphics.getWidth(), this.getY());
    }
}
