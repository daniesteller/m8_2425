package andres.m8.uf3.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import andres.m8.uf3.helpers.AssetManager;
import andres.m8.uf3.utils.Settings;

public class Spacecraft extends Actor {
    //Distinguimos las posiciones
    public static final int SPACECRAFT_STRAIGHT=0;
    public static final int SPACECRAFT_UP=1;
    public static final int SPACECRAFT_DOWN=2;
    public static final int SPACECRAFT_LEFT = 3;
    public static final int SPACECRAFT_RIGHT = 4;

    public  Rectangle collisionRect;

    //parametros
    private Vector2 position;
    private int width, height;




    private int direction;

    //constructor
    public Spacecraft(float x, float y, int width, int height){
    this.width=width;
    this.height=height;
    position=new Vector2(x,y);

    direction=SPACECRAFT_STRAIGHT;
     collisionRect = new Rectangle();
     setBounds(position.x, position.y, width,height);
     setTouchable(Touchable.enabled);



    }
    @Override
    public void act(float delta) {
       switch (direction){
           case SPACECRAFT_UP:
               if (position.y - Settings.SPACECRAFT_VELOCITY * delta >= 0) {
                   position.y -= Settings.SPACECRAFT_VELOCITY * delta;
               }
               break;
           case SPACECRAFT_DOWN:
               if (position.y + height + Settings.SPACECRAFT_VELOCITY * delta <= Settings.GAME_HEIGHT) {
                   position.y += Settings.SPACECRAFT_VELOCITY * delta;
               }
               break;
           case SPACECRAFT_LEFT:

               if (position.x- Settings.SPACECRAFT_VELOCITY * delta >= 0) {
                   position.x -= Settings.SPACECRAFT_VELOCITY * delta;
               }
               break;
           case SPACECRAFT_RIGHT:

               if (position.x + height + Settings.SPACECRAFT_VELOCITY * delta <= Settings.GAME_WIDTH) {
                   position.x += Settings.SPACECRAFT_VELOCITY * delta;
               }
               break;
           case SPACECRAFT_STRAIGHT:
               break;
       }
        collisionRect.set(position.x, position.y+ 3,width,10 );
        setBounds(position.x, position.y, width,height);
    }

    public Rectangle getCollisionRect() {
        return collisionRect;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    //getters
    public float getX(){
        return position.x;
    }
    public float getY(){
        return position.y;
    }

    public void goDown(){
        direction=SPACECRAFT_DOWN;
    }
    public void goLeft() {
        direction = SPACECRAFT_LEFT;
    }

    public void goRight() {
        direction = SPACECRAFT_RIGHT;
    }
    public void goUp(){
        direction=SPACECRAFT_UP;
    }
    public void goStraight(){
        direction=SPACECRAFT_STRAIGHT;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(getSpacecraftTexture(),position.x,position.y,width,height);
    }
    public TextureRegion getSpacecraftTexture(){
        switch (direction){
            case SPACECRAFT_STRAIGHT:
                return AssetManager.spacecraft;
            case SPACECRAFT_UP:
                return AssetManager.spacecraftUp;
            case SPACECRAFT_DOWN:
                return AssetManager.spacecraftDown;
            case SPACECRAFT_RIGHT:
                return AssetManager.spacecraftRight;
            case SPACECRAFT_LEFT:
                return AssetManager.spacecraftLeft;
            default:
                return AssetManager.spacecraft;
        }
    }
    public void reset(){
      position.y=Settings.SPACECRAFT_STARTY;
      position.x=Settings.SPACECRAFT_STARTX;

        direction=SPACECRAFT_STRAIGHT;
        collisionRect = new Rectangle();
        setBounds(position.x, position.y, width,height);
    }
}
