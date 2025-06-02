package andres.m8.uf3.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import andres.m8.uf3.actors.Spacecraft;
import andres.m8.uf3.screens.GameScreen;

public class InputHandler implements InputProcessor {
    private Spacecraft spacecraft;
    private GameScreen screen;
    int previousY=0;
    int previousX=0;
    private Vector2 stageCoord;
    private Stage stage;

    public InputHandler(GameScreen screen) {
       this.screen=screen;
       spacecraft=screen.getSpacecraft();
       stage=screen.getStage();
    }
        @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        switch (screen.getEstado()) {
            case 0:
               screen.setEstado(1);
                break;
            case 1:
                stageCoord=stage.screenToStageCoordinates(new Vector2(screenX,screenY));
                Actor actorHit=stage.hit(stageCoord.x,stageCoord.y,true);
                if (actorHit!=null) {
                    Gdx.app.log("HIT",actorHit.getName());
                }
                break;
            case 2:
               screen.reset();
                break;

        }
            return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        spacecraft.goStraight();
        return true;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
      if (Math.abs(previousY-screenY)> 2){
          if (previousY< screenY){
              spacecraft.goDown();
          } else {
              spacecraft.goUp();
          }

          previousY=screenY;
      }
        if (Math.abs(previousX-screenX)> 2){
            if (previousX< screenY){
                spacecraft.goLeft();
            } else {
                spacecraft.goRight();
            }


        }


        previousY=screenY;
        previousX=screenX;
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public void setScreen(GameScreen screen) {
    }
}
