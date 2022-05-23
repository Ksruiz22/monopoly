package com.monopoly.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.monopoly.game.Pantallas.GameScreen;
import com.monopoly.game.Pantallas.MainMenuScreen;
import com.monopoly.game.Pantallas.OptionsScreen;

public abstract class Screens extends InputAdapter implements Screen {
    public static final float SCREEN_WIDTH = 1280;
    public static final float SCREEN_HEIGHT = 800;

    public static final float WORLD_WIDTH = 12.8f;
    public static final float WORLD_HEIGHT = 8f;

    public monopoly game;

    public OrthographicCamera oCamUi;
    public OrthographicCamera oCamBox2D;
    public static Sprite backgroundTitleSprite,backgroundImageSprite;
    public SpriteBatch spriteBatch;


    public static Stage stage;

    public Screens(monopoly game){
        this.game = game;

        stage = new Stage(new StretchViewport(Screens.SCREEN_WIDTH,Screens.SCREEN_HEIGHT));

        oCamUi = new OrthographicCamera(SCREEN_WIDTH,SCREEN_HEIGHT);
        oCamUi.position.set(SCREEN_WIDTH/2f,SCREEN_HEIGHT/2f,0);

        oCamBox2D = new OrthographicCamera(WORLD_WIDTH,WORLD_HEIGHT);
        oCamBox2D.position.set(WORLD_WIDTH/2f,WORLD_HEIGHT/2f,0);

        InputMultiplexer input = new InputMultiplexer(this,stage);
        Gdx.input.setInputProcessor(input);

        if(this instanceof MainMenuScreen){
            backgroundImageSprite = new Sprite(Assets.backgroundImageTexture);
            backgroundImageSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

            backgroundTitleSprite = new Sprite(Assets.backgroundTitleTexture);
            backgroundTitleSprite.setSize(600, 173);
            backgroundTitleSprite.setPosition((Gdx.graphics.getWidth()/2)-(backgroundTitleSprite.getWidth()/2),Gdx.graphics.getHeight()/1.6f);
        }


        spriteBatch = new SpriteBatch();

    }

    @Override
    public void render(float delta) {

        update(delta);

        stage.act(delta);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(this instanceof OptionsScreen){
            Gdx.gl.glClearColor(135/255f, 206/255f, 235/255f, 1);
        }else if(this instanceof GameScreen){
            Gdx.gl.glClearColor(13/255f, 107/255f, 2/255f, 1);
        }

        spriteBatch.begin();

        draw(delta);

        spriteBatch.end();

        stage.draw();

    }

    public abstract void draw(float delta);

    public abstract void update(float delta);

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height, true);
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.ESCAPE || keycode == Input.Keys.BACK){
            if(this instanceof MainMenuScreen){
                Gdx.app.exit();
            }else{
                game.setScreen(new MainMenuScreen(game));
            }

        }
        return super.keyDown(keycode);
    }

    @Override
    public void show() {

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
