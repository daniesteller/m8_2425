package andres.m8.uf3.screens;

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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.ArrayList;

import andres.m8.uf3.actors.Asteroid;
import andres.m8.uf3.actors.ScrollHandler;
import andres.m8.uf3.actors.Spacecraft;
import andres.m8.uf3.helpers.AssetManager;
import andres.m8.uf3.helpers.InputHandler;
import andres.m8.uf3.utils.Settings;

public class GameScreen implements Screen {
    private Stage stage;
    private Texture texture;
    private Spacecraft spacecraft;
    private ScrollHandler scrollHandler;
    private ShapeRenderer shapeRenderer;
    private Batch batch;
    Boolean gameOver=false;
    private float explosionTime=0;
    private BitmapFont font;
    private GlyphLayout textLayout;
    private GlyphLayout texto;
    private Label.LabelStyle stilo;

    private int estado;

    public GameScreen(){

        AssetManager.music.play();

        scrollHandler = new ScrollHandler();
        shapeRenderer=new ShapeRenderer();
        font =new BitmapFont(true);
        textLayout=new GlyphLayout();
        textLayout.setText(AssetManager.font,"GameOver");
        texto=new GlyphLayout();
        texto.setText(AssetManager.font,"     Are you ready?");
        OrthographicCamera camera= new OrthographicCamera(Settings.GAME_WIDTH,Settings.GAME_HEIGHT);
        camera.setToOrtho(true);
        StretchViewport viewport=new StretchViewport(Settings.GAME_WIDTH,Settings.GAME_HEIGHT,camera);
        stage = new Stage(viewport);
        batch=stage.getBatch();
        spacecraft = new Spacecraft(Settings.SPACECRAFT_STARTX,Settings.SPACECRAFT_STARTY,Settings.SPACECRAFT_WIDTH,Settings.SPACECRAFT_HEIGHT);
       // texture = new Texture(Gdx.files.internal("arcade.png"));

        stage.addActor(scrollHandler);
        stage.addActor(spacecraft);
        spacecraft.setName("spacecraft");
        Gdx.input.setInputProcessor(new InputHandler(this));

    }

    @Override
    public void show() {

    }

    public Stage getStage() {
        return stage;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

    public Spacecraft getSpacecraft() {
        return spacecraft;
    }

    public void setSpacecraft(Spacecraft spacecraft) {
        this.spacecraft = spacecraft;
    }

    public ScrollHandler getScrollHandler() {
        return scrollHandler;
    }

    public void setScrollHandler(ScrollHandler scrollHandler) {
        this.scrollHandler = scrollHandler;
    }

    @Override
    public void render(float delta) {
     stage.draw();

        switch (estado) {
            case 0:
                ready();
                break;
            case 1:
                runing(delta);
                break;
            case 2:
                gameOver(delta);
                break;

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
    public void ready(){
        batch.begin();
        //batch.draw((TextureRegion) AssetManager.explosionAnim.getKeyFrame(explosionTime,false),(spacecraft.getX()+ spacecraft.getWidth()/2)-32,spacecraft.getY()+spacecraft.getHeight()/2-32,64,64);
        AssetManager.font.draw(batch, texto,Settings.GAME_WIDTH/2-texto.width/2,Settings.GAME_HEIGHT/2-texto.height/2);
        batch.end();
    }

    public void runing(float delta){

        stage.act(delta);


            if (scrollHandler.collides(spacecraft)){
                AssetManager.explosionSound.play();
                stage.getRoot().findActor("spacecraft").remove();
                    estado=2;
             }
    }
    public void gameOver(float delta){
        stage.act(delta);
        batch.begin();
        batch.draw((TextureRegion) AssetManager.explosionAnim.getKeyFrame(explosionTime,false),(spacecraft.getX()+ spacecraft.getWidth()/2)-32,spacecraft.getY()+spacecraft.getHeight()/2-32,64,64);
        AssetManager.font.draw(batch, textLayout,Settings.GAME_WIDTH/2-textLayout.width/2,Settings.GAME_HEIGHT/2-textLayout.height/2);
        batch.end();
        explosionTime+=delta;
    }
    public void reset(){

        spacecraft.reset();
        scrollHandler.reset();
         estado=0;

      stage.addActor(spacecraft);

      explosionTime=0.0f;
    }

}
