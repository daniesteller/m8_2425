package andres.m8.uf3;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import andres.m8.uf3.helpers.AssetManager;
import andres.m8.uf3.screens.GameScreen;
import andres.m8.uf3.screens.Portada;

public class SpaceRace extends Game {

	
	@Override
	public void create () {
		AssetManager.load();
		setScreen(new Portada(this));

	}

	@Override
	public void render () {
    super.render();
	}
	
	@Override
	public void dispose () {
     AssetManager.dispose();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause ()  {

	}
	@Override
	public void resume ()  {

	}


}
