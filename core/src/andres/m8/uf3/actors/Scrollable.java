package andres.m8.uf3.actors;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Scrollable extends Actor {
    protected Vector2 position;
    protected float velocity;
    protected float width;
    protected float height;
    protected boolean leftOfScreen;
    public Scrollable(float x, float y, float width, float height,float velocity){
     position = new Vector2(x,y);
     this.velocity=velocity;
     this.width=width;
     this.height=height;
     leftOfScreen=false;
    }
    public void reset (float newX){
        position.x=newX;
        leftOfScreen=false;
    }
    public boolean isLeftOfScreen(){
        return leftOfScreen;
    }
    public float getTailX(){
        return position.x + width;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    public float getX(){
        return position.x;
    }
    public float getY(){
        return position.y;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        position.x += velocity * delta;
        if (position.x + width < 0){
            leftOfScreen=true;
        }
    }
}
