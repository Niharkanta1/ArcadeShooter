package com.dw.shooter.screen;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.dw.shooter.ArcadeShooter;

/**
 * @author nihar
 * @date 02-07-2022
 * @project ArcadeShooter
 */
public abstract class AbstractScreen implements Screen {
    protected final ArcadeShooter context;
    protected final Batch batch;
    protected final Engine engine;

    protected AbstractScreen(ArcadeShooter context) {
        this.context = context;
        this.batch = context.getBatch();
        this.engine = context.getEngine();
    }
}
