package com.dw.shooter.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.dw.shooter.MainGame;
import com.dw.shooter.util.Logger;

/**
 * @author nihar
 * @date 02-07-2022
 * @project ArcadeShooter
 */
public class LoadingScreen extends AbstractScreen {
    private Logger log = Logger.getInstance(this.getClass());

    public LoadingScreen(MainGame context) {
        super(context);
    }

    @Override
    public void show() {
        log.info("Loading...");
    }

    @Override
    public void render(float delta) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.L)) {
            log.info("Button Pressed.");
            context.setScreen(ScreenType.Game);
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
