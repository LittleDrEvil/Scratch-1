package KarnMenuTest.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import KarnMenuTest.GdxMenu;
import KarnMenuTest.TbMenu;
import KarnMenuTest.TbsMenu;
import KarnMenuTest.Screens.BlockClass;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;

/**
 * Created by Luke on 2016-04-05.
 */
public class ScrPlay implements Screen, InputProcessor {
    BlockClass bBlock;
    ArrayList[] alBlocks; 
    GdxMenu gdxMenu;
    TbsMenu tbsMenu;
    TbMenu tbMenu, tbGameover;
    Stage stage;
    SpriteBatch batch;
    BitmapFont screenName;
    CharClass charSonic = new CharClass();
    Sprite sprSonic;
    Animation[] aniChar;
    private float elapsedTime = 0;
    Texture imgFloor, imgBack, imgBlock;
    int nJum, nDir = 0, nAniCurr;
    float fSY, fSX, fBX=50, fBY=50, fX, fY, fBackX;
    double dGravity, dSpeed;
    Vector2 vSonic;
    boolean bPass=false;
    public ScrPlay(GdxMenu _gdxMenu) {  //Referencing the main class.
        gdxMenu = _gdxMenu;
    }

    public void show() {
        batch = new SpriteBatch();
        imgBack = new Texture(Gdx.files.internal("background.png"));
        imgFloor = new Texture(Gdx.files.internal("background1.png"));
        imgBlock = new Texture(Gdx.files.internal("block.png"));
        charSonic.charMain("Sonic");
        stage = new Stage();
        tbsMenu = new TbsMenu();
        tbMenu = new TbMenu("BACK", tbsMenu);
        tbGameover = new TbMenu("GAMEOVER", tbsMenu);
        tbMenu.setY(0);
        tbMenu.setX(0);
        tbGameover.setY(0);
        tbGameover.setX(440);
        stage.addActor(tbMenu);
        stage.addActor(tbGameover);
        Gdx.input.setInputProcessor(stage);
        btnMenuListener();
        btnGameoverListener();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        charSonic.update();
        batch.begin();
        elapsedTime += Gdx.graphics.getDeltaTime();
        batch.draw(imgBack, fBackX, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(imgBack, fBackX-Gdx.graphics.getWidth(), 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(imgBack, fBackX+Gdx.graphics.getWidth(), 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        if(fBackX < -Gdx.graphics.getWidth()){
            fBackX=0;
        }
        batch.draw(imgFloor, 0, 0, Gdx.graphics.getWidth()-30, 40);
        batch.draw(imgBlock, fBX, fBY, 30, 30);
        nDir = charSonic.Direction();
        batch.draw(charSonic.aniChar[nDir].getKeyFrame(elapsedTime, true), charSonic.vChar.x, charSonic.vChar.y);
        batch.end();
        
        System.out.println(charSonic.fSx);
            fBackX -= charSonic.fSx;
            
            
    }

    public void btnGameoverListener() {
        tbGameover.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                gdxMenu.currentState = gdxMenu.gameState.OVER;
                gdxMenu.updateState();
            }
        });
    }

    public void btnMenuListener() {
        tbMenu.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                gdxMenu.currentState = gdxMenu.gameState.MENU;
                gdxMenu.updateState();
            }
        });
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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}