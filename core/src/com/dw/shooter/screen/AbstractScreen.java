package com.dw.shooter.screen;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dw.shooter.MainGame;

/**
 * @author nihar
 * @date 02-07-2022
 * @project ArcadeShooter
 */
public abstract class AbstractScreen implements Screen {
    protected final MainGame context;
    protected final Batch batch;
    protected final Engine engine;
    protected final Viewport gameViewport;

    protected AbstractScreen(MainGame context) {
        this.context = context;
        this.batch = context.getBatch();
        this.engine = context.getEngine();
        this.gameViewport = context.getGameViewport();
    }
}
