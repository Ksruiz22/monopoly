package com.monopoly.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

import static com.badlogic.gdx.scenes.scene2d.ui.Label.*;
import static com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.*;
import static com.badlogic.gdx.scenes.scene2d.ui.TextButton.*;

public class Assets {
    public static Texture backgroundTitleTexture, backgroundImageTexture, logoTexture,
                          shipTexture, dogTexture, carTexture,
                          hatTexture, boardTexture,
                          shipTexturebg, dogTexturebg, carTexturebg,
                          hatTexturebg, dado1, dado2,
                          dado3, dado4, dado5,
                          dado6;

    public static BitmapFont font, fontTitles;
    public static TextButtonStyle txButtonStyle;
    public static ScrollPaneStyle scrollPaneStyle;
    public static  TextureAtlas atlas2, atlas;
    public static  NinePatchDrawable bt, btDown;
    public static  FreeTypeFontGenerator generator;
    public static LabelStyle labelStyle,labelTitlesStyle;

    public static void load(){


        initFonts();

        labelStyle = new LabelStyle();
        labelStyle.font = font;
        labelTitlesStyle = new LabelStyle();
        labelTitlesStyle.font = fontTitles;

        backgroundTitleTexture = new Texture(Gdx.files.internal("images/background_title.png"));
        backgroundImageTexture = new Texture(Gdx.files.internal("images/background_image2.jpg"));

        logoTexture = new Texture(Gdx.files.internal("images/logo.png"));
        Image logo = new Image(Assets.logoTexture);

        //Player Textures
        shipTexture = new Texture(Gdx.files.internal("images/shipPlayer.png"));
        hatTexture = new Texture(Gdx.files.internal("images/hatPlayer.png"));
        dogTexture = new Texture(Gdx.files.internal("images/dogPlayer.png"));
        carTexture = new Texture(Gdx.files.internal("images/carPlayer.png"));

        //Player Textures with bg
        shipTexturebg = new Texture(Gdx.files.internal("images/shipbg.png"));
        hatTexturebg = new Texture(Gdx.files.internal("images/hatbg.png"));
        dogTexturebg = new Texture(Gdx.files.internal("images/dogbg.png"));
        carTexturebg = new Texture(Gdx.files.internal("images/carbg.png"));

        //Dices texture
        dado1 = new Texture(Gdx.files.internal("images/dado1.png"));
        dado2 = new Texture(Gdx.files.internal("images/dado2.png"));
        dado3 = new Texture(Gdx.files.internal("images/dado3.png"));
        dado4 = new Texture(Gdx.files.internal("images/dado4.png"));
        dado5 = new Texture(Gdx.files.internal("images/dado5.png"));
        dado6 = new Texture(Gdx.files.internal("images/dado6.png"));



        //Board Texture
        boardTexture = new Texture(Gdx.files.internal("images/Seminopoly.png"));


        TextureAtlas atlas2 = new TextureAtlas(Gdx.files.internal("ui/button.atlas"));
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ui/ui.txt"));

        NinePatchDrawable bt2 = new NinePatchDrawable(atlas2.createPatch("bt"));
        NinePatchDrawable btDown2 = new NinePatchDrawable(atlas2.createPatch("btDown"));

        txButtonStyle = new TextButtonStyle(bt2,btDown2,null,font);

        NinePatchDrawable knob = new NinePatchDrawable(atlas.createPatch("scroll"));
        scrollPaneStyle = new ScrollPaneStyle(null,knob,knob,knob,knob);

    }

    public static void initFonts(){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/ItcKabel-Demi.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.size = 24;
        params.color = Color.WHITE;
        font = generator.generateFont(params);
        params.size = 48;
        fontTitles = generator.generateFont(params);
    }

}
