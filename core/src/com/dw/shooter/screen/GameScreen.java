package com.dw.shooter.screen;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dw.shooter.ArcadeShooter;
import com.dw.shooter.ecs.component.GraphicsComponent;
import com.dw.shooter.ecs.component.TransformComponent;
import com.dw.shooter.util.Logger;

import static com.dw.shooter.ArcadeShooter.UNIT_SCALE;

/**
 * @author nihar
 * @date 02-07-2022
 * @project ArcadeShooter
 */
public class GameScreen extends AbstractScreen {
    private Logger log = Logger.getInstance(GameScreen.class);

    private Texture texture;
    private Viewport viewport;
    private Entity player;

    public GameScreen(ArcadeShooter context) {
        super(context);
    }

    @Override
    public void show() {
        log.info( "GameScreen is shown.");
        viewport = new FitViewport(9f, 16f);

        // Create Player Entity and its Components
        player = engine.createEntity();
        TransformComponent transform = engine.createComponent(TransformComponent.class);
        GraphicsComponent graphics = engine.createComponent(GraphicsComponent.class);
        // Create Player Data
        transform.position.set(1f, 1f, 0f);
        texture = new Texture(Gdx.files.internal("graphics/window-icon.png"));
        graphics.sprite.setRegion(texture);
        graphics.sprite.setSize(9 * UNIT_SCALE, 10 * UNIT_SCALE);
        graphics.sprite.setPosition(1f, 1f);

        // Add the components to the Entity
        player.add(transform);
        player.add(graphics);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor( 0, 1, 0, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        // Update the Engine, all the systems in the engine
        engine.update(delta);

        // Apply Viewport - basically using a particular viewport when we have multiple viewport setup
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined); // setting projection matrix/camera to the batch
        batch.begin();
        GraphicsComponent.Map.get(player).sprite.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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
