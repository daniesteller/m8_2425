package andres.m8.uf3.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.ArrayList;

import andres.m8.uf3.actors.Asteroid;
import andres.m8.uf3.actors.ScrollHandler;
import andres.m8.uf3.actors.Spacecraft;
import andres.m8.uf3.helpers.AssetManager;
import andres.m8.uf3.helpers.InputHandler;
import andres.m8.uf3.utils.Settings;

public class Portada implements Screen {

    private Stage stage;
    private Texture texture;
    private Image spacecraft;
    private Batch batch;
    private BitmapFont font;
    private GlyphLayout textLayout;

    private ScrollHandler scrollHandler;
    private ShapeRenderer shapeRenderer;
    private Container container;
    private Label texlabel;
    private Label.LabelStyle stilo;
    private Game Cambio;

    public Portada(Game cambio){
        //scrollHandler = new ScrollHandler();
        Cambio=cambio;
        shapeRenderer=new ShapeRenderer();
        font =new BitmapFont(true);

        stilo= new Label.LabelStyle(AssetManager.font,null);
        texlabel = new Label("SpaceRace",stilo);
        container=new Container(texlabel);
        container.setTransform(true);
        container.center();
        container.setPosition(Settings.GAME_WIDTH/2,Settings.GAME_HEIGHT/2);
        SequenceAction sequenceAction= Actions.sequence(Actions.scaleTo(1.5f,1.5f),Actions.scaleTo(1,1,5));
        RepeatAction act=Actions.repeat(RepeatAction.FOREVER,sequenceAction);
        container.addAction(act);
        OrthographicCamera camera= new OrthographicCamera(Settings.GAME_WIDTH,Settings.GAME_HEIGHT);
        camera.setToOrtho(true);
        StretchViewport viewport=new StretchViewport(Settings.GAME_WIDTH,Settings.GAME_HEIGHT,camera);
        stage = new Stage(viewport);
        batch=stage.getBatch();
        spacecraft = new Image(AssetManager.spacecraft);
        // texture = new Texture(Gdx.files.internal("arcade.png"));
        float y=Settings.GAME_HEIGHT/2+texlabel.getHeight();
        SequenceAction sequencial = Actions.sequence(Actions.moveTo(0-spacecraft.getHeight(),y),Actions.moveTo(Settings.GAME_WIDTH,y,5));
        RepeatAction acion=Actions.repeat(RepeatAction.FOREVER,sequencial);
        spacecraft.addAction(acion);
        stage.addActor(new Image(AssetManager.background));
        stage.addActor(container);
        stage.addActor(spacecraft);

    }
    public  void drawElements(){

        Gdx.gl20.glClearColor(0.0f,0.0f,0.0f,1.0f);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(new Color(0,1,0,1));
        shapeRenderer.rect(spacecraft.getX(),spacecraft.getY(),spacecraft.getWidth(),spacecraft.getHeight());
        ArrayList<Asteroid> asteroids= scrollHandler.getAsteroids();
        Asteroid asteroid;
        for (int i=1; i<asteroids.size(); i++){
            asteroid= asteroids.get(i);
            switch (i){
                case 0:
                    shapeRenderer.setColor(1,0,0,1);
                    break;
                case 1:
                    shapeRenderer.setColor(0,0,1,1);
                    break;
                case 2:
                    shapeRenderer.setColor(1,1,0,1);
                    break;
                default:
                    shapeRenderer.setColor(1,1,1,1);
                    break;
            }
            shapeRenderer.circle(asteroid.getX()+asteroid.getWidth()/2,+asteroid.getY()+asteroid.getWidth()/2,asteroid.getWidth()/2);

        }
        shapeRenderer.end();
    }
    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }





    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage.draw();
        stage.act(delta);
        if (Gdx.input.isTouched()) {
           Cambio.setScreen(new GameScreen());
            dispose();
        }

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
