package com.dw.shooter.screen;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.dw.shooter.MainGame;
import com.dw.shooter.component.*;
import com.dw.shooter.util.Logger;

/**
 * @author nihar
 * @date 02-07-2022
 * @project ArcadeShooter
 */
public class GameScreen extends AbstractScreen {
    private Logger log = Logger.getInstance(this.getClass());

    public static final float MAX_DELTA_TIME = 1/20f;

    private Texture texture;
    private Entity player;

    public GameScreen(MainGame context) {
        super(context);
    }

    @Override
    public void show() {
        log.info( "GameScreen is shown.");

        // Create Player Entity and its Components
        player = engine.createEntity();
        TransformComponent transform = engine.createComponent(TransformComponent.class);
        GraphicsComponent graphics = engine.createComponent(GraphicsComponent.class);
        PlayerComponent playerComponent = engine.createComponent(PlayerComponent.class);
        FacingComponent facingComponent = engine.createComponent(FacingComponent.class);
        MoveComponent moveComponent = engine.createComponent(MoveComponent.class);

        // Create Player Data
        transform.position.set(4.5f, 8f, 0f);

        // Add the components to the Entity
        player.add(transform);
        player.add(graphics);
        player.add(playerComponent);
        player.add(facingComponent);
        player.add(moveComponent);

        // Add entity to the Engine
        engine.addEntity(player);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor( 0, 0, 0, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        // Update the Engine, all the systems in the engine
        engine.update(Math.min(MAX_DELTA_TIME, delta));
    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height, true);
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
        texture.dispose();
        batch.dispose();
    }
}
