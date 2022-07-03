package com.dw.shooter.ecs.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dw.shooter.ecs.component.FacingComponent;
import com.dw.shooter.ecs.component.GraphicsComponent;
import com.dw.shooter.ecs.component.PlayerComponent;
import com.dw.shooter.enums.FacingDirection;
import com.dw.shooter.util.Logger;

/**
 * @author nihar
 * @date 03-07-2022
 * @project ArcadeShooter
 */
public class PlayerAnimationSystem extends IteratingSystem implements EntityListener {
    private Logger log = Logger.getInstance(this.getClass());
    TextureRegion defaultRegion;
    TextureRegion leftRegion;
    TextureRegion rightRegion;

    public PlayerAnimationSystem(TextureRegion defaultRegion, TextureRegion leftRegion, TextureRegion rightRegion) {
        super(Family.all(PlayerComponent.class, FacingComponent.class, GraphicsComponent.class).get());
        this.defaultRegion = defaultRegion;
        this.rightRegion = rightRegion;
        this.leftRegion = leftRegion;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        engine.addEntityListener(getFamily(),this);
    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
        engine.addEntityListener(this);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        var facing = FacingComponent.Mapper.get(entity);
        var graphics = GraphicsComponent.Mapper.get(entity);
        if(graphics.sprite.getTexture() != null && facing.lastDirection == facing.direction) {
            return;
        }
        facing.lastDirection = facing.direction;
        var region = defaultRegion;
        if(facing.direction == FacingDirection.LEFT)
            region = leftRegion;
        if(facing.direction == FacingDirection.RIGHT)
            region = rightRegion;

        graphics.setSpriteRegion(region);
    }

    @Override
    public void entityAdded(Entity entity) {
        GraphicsComponent.Mapper.get(entity).setSpriteRegion(defaultRegion);
    }

    @Override
    public void entityRemoved(Entity entity) {
        log.info("Entity Removed. Entity: "+ entity);
    }
}
