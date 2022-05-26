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

    public static Texture cp1, cp3, cp5, cp6, cp8, cp9, cp11, cp12,
            cp13, cp14, cp15, cp16, cp18, cp19, cp21, cp23, cp24, cp25,
            cp26, cp27, cp28, cp29, cp31, cp32, cp34, cp35, cp37, cp38, cp39;;

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

        //Properties cards texture
        cp1 = new Texture(Gdx.files.internal("properties-cards/card#1.png"));
        cp3 = new Texture(Gdx.files.internal("properties-cards/card#3.png"));
        cp5 = new Texture(Gdx.files.internal("properties-cards/card#5.png"));
        cp6 = new Texture(Gdx.files.internal("properties-cards/card#6.png"));
        cp8 = new Texture(Gdx.files.internal("properties-cards/card#8.png"));
        cp9 = new Texture(Gdx.files.internal("properties-cards/card#9.png"));
        cp11 = new Texture(Gdx.files.internal("properties-cards/card#11.png"));
        cp12 = new Texture(Gdx.files.internal("properties-cards/card#12.png"));
        cp13 = new Texture(Gdx.files.internal("properties-cards/card#13.png"));
        cp14 = new Texture(Gdx.files.internal("properties-cards/card#14.png"));
        cp15 = new Texture(Gdx.files.internal("properties-cards/card#15.png"));
        cp16 = new Texture(Gdx.files.internal("properties-cards/card#16.png"));
        cp18 = new Texture(Gdx.files.internal("properties-cards/card#18.png"));
        cp19 = new Texture(Gdx.files.internal("properties-cards/card#19.png"));
        cp21 = new Texture(Gdx.files.internal("properties-cards/card#21.png"));
        cp23 = new Texture(Gdx.files.internal("properties-cards/card#23.png"));
        cp24 = new Texture(Gdx.files.internal("properties-cards/card#24.png"));
        cp25 = new Texture(Gdx.files.internal("properties-cards/card#25.png"));
        cp26 = new Texture(Gdx.files.internal("properties-cards/card#26.png"));
        cp27 = new Texture(Gdx.files.internal("properties-cards/card#27.png"));
        cp28 = new Texture(Gdx.files.internal("properties-cards/card#28.png"));
        cp29 = new Texture(Gdx.files.internal("properties-cards/card#29.png"));
        cp31 = new Texture(Gdx.files.internal("properties-cards/card#31.png"));
        cp32 = new Texture(Gdx.files.internal("properties-cards/card#32.png"));
        cp34 = new Texture(Gdx.files.internal("properties-cards/card#34.png"));
        cp35 = new Texture(Gdx.files.internal("properties-cards/card#35.png"));
        cp37 = new Texture(Gdx.files.internal("properties-cards/card#37.png"));
        cp38 = new Texture(Gdx.files.internal("properties-cards/card#38.png"));
        cp39 = new Texture(Gdx.files.internal("properties-cards/card#39.png"));

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
