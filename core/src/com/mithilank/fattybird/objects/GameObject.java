package com.mithilank.fattybird.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mithilank.fattybird.util.CollideRect;
public class GameObject extends Sprite{
    public CollideRect rect;

    public GameObject(Texture img, float x, float y) {
        super(img);
        this.setX(x);
        this.setY(y);
        rect = new CollideRect(x, y, getWidth(), getHeight());
    }

    public void changeX (float x) {
        setX(getX() + x);
    }

    public void changeY (float y) {
        setY(getY() + y);
    }

    public void Update () {
        rect.x = this.getX();
        rect.y = this.getY();
    }


}


