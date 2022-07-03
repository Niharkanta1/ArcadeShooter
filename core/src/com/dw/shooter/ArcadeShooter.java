package com.dw.shooter;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.dw.shooter.screen.ScreenType;
import com.dw.shooter.util.Logger;

import java.util.EnumMap;

public class ArcadeShooter extends Game {
    private Logger log = Logger.getInstance(this.getClass());

    public static final float UNIT_SCALE= 1/16f;
    private EnumMap<ScreenType, Screen> screenCache;
    private Batch batch;

    private Engine engine;
    private Entity player;

    @Override
    public void create() {
        Box2D.init();
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        engine = new PooledEngine();
        screenCache = new EnumMap<ScreenType, Screen>(ScreenType.class);
        batch = new SpriteBatch();
        setScreen(ScreenType.First);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        log.debug("Sprites in batch", ((SpriteBatch) batch).maxSpritesInBatch);
        batch.dispose();
    }

    public void setScreen(ScreenType screenType) {
        final Screen screen = screenCache.get(screenType);
        if(screen == null) {
            try {
                log.info("Creating new screen", screenType);
                final Screen newScreen = (Screen) ClassReflection.getConstructor(screenType.getScreenClass(),
                        ArcadeShooter.class).newInstance(this);
                screenCache.put(screenType, newScreen);
                setScreen(newScreen);
            } catch (ReflectionException e) {
                log.info("Screen " + screenType + " could not be created.");
                throw new GdxRuntimeException("Screen " + screenType + " could not be created.", e);
            }
        } else {
            log.error("Switching to new screen::" + screenType);
            setScreen(screen);
        }
    }

    public Batch getBatch() {
        return batch;
    }

    public Engine getEngine() {
        return engine;
    }
}
