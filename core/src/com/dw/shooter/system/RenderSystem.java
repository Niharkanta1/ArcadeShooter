package com.dw.shooter.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dw.shooter.component.GraphicsComponent;
import com.dw.shooter.component.TransformComponent;
import com.dw.shooter.util.Logger;
import com.dw.shooter.util.ZComparator;

import java.util.Comparator;

/**
 * @author nihar
 * @date 02-07-2022
 * @project ArcadeShooter
 */
public class RenderSystem extends SortedIteratingSystem {
    private Logger log = Logger.getInstance(this.getClass());

    private SpriteBatch batch;
    private Viewport viewport;
    private Comparator<Entity> comparator;

    public RenderSystem(SpriteBatch spriteBatch, Viewport gameViewPort) {
        super(Family.all(TransformComponent.class, GraphicsComponent.class).get(), new ZComparator());
        this.batch = spriteBatch;
        this.viewport = gameViewPort;
        this.comparator = new ZComparator();
    }

    @Override
    public void update(float deltaTime) {
        forceSort();
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        super.update(deltaTime);
        batch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        var transform = TransformComponent.Mapper.get(entity);
        var graphics = GraphicsComponent.Mapper.get(entity);
        if(graphics.sprite.getTexture() == null) {
            log.error("Entity must have a Graphics component. Entity "+ entity);
            return;
        }
        graphics.sprite.rotate(transform.rotationDeg);
        graphics.sprite.setBounds(transform.interpolatedPosition.x, transform.interpolatedPosition.y, transform.scale.x, transform.scale.y);
        graphics.sprite.draw(batch);
    }
}
