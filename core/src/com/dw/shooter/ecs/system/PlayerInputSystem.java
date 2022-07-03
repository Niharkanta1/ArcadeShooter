package com.dw.shooter.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dw.shooter.ecs.component.FacingComponent;
import com.dw.shooter.ecs.component.PlayerComponent;
import com.dw.shooter.ecs.component.TransformComponent;
import com.dw.shooter.enums.FacingDirection;
import com.dw.shooter.util.Logger;

/**
 * @author nihar
 * @date 03-07-2022
 * @project ArcadeShooter
 */
public class PlayerInputSystem extends IteratingSystem {
    private Logger log = Logger.getInstance(this.getClass());

    private static final float TOLERANCE_DISTANCE = 0.2f;

    private Viewport viewport;
    private Vector2 tempVec = new Vector2();

    public PlayerInputSystem(Viewport viewport) {
        super(Family.all(PlayerComponent.class, TransformComponent.class, FacingComponent.class).get());
        this.viewport = viewport;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        var facing = FacingComponent.Mapper.get(entity);
        var transform = TransformComponent.Mapper.get(entity);

        tempVec = viewport.unproject(new Vector2(Gdx.input.getX(), 0f));
        var diffX = tempVec.x - transform.position.x - transform.scale.x * 0.5f;
        if(diffX < -TOLERANCE_DISTANCE) {
            facing.direction = FacingDirection.LEFT;
        } else if(diffX > TOLERANCE_DISTANCE) {
            facing.direction = FacingDirection.RIGHT;
        } else {
            facing.direction = FacingDirection.DEFAULT;
        }


    }
}
