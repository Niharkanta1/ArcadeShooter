package com.dw.shooter.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.dw.shooter.component.*;
import com.dw.shooter.util.Logger;

import static com.dw.shooter.MainGame.V_HEIGHT;
import static com.dw.shooter.MainGame.V_WIDTH;

/**
 * @author nihar
 * @date 03-07-2022
 * @project ArcadeShooter
 */
public class MoveSystem extends IteratingSystem {
    private Logger log = Logger.getInstance(this.getClass());

    private static final float UPDATE_RATE = 1 / 30f;
    private static final float HOR_ACCELERATION = 16.5f;
    private static final float VER_ACCELERATION = 2.25f;
    private static final float MAX_VER_NEG_PLAYER_SPEED = 0.75f;
    private static final float MAX_VER_POS_PLAYER_SPEED = 5f;
    private static final float MAX_HOR_SPEED = 5.5f;
    private float accumulator = 0.0f;

    public MoveSystem() {
        super(Family.all(MoveComponent.class).exclude(RemoveComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        accumulator += deltaTime;
        while (accumulator >= UPDATE_RATE) {
            accumulator -= UPDATE_RATE;

            // Enhancement
            getEntities().forEach(entity -> {
                var tf = TransformComponent.Mapper.get(entity);
                tf.prevPosition = tf.position;
            });

            super.update(UPDATE_RATE);
        }
        var alpha = accumulator/ UPDATE_RATE;

        // Enhancement
        getEntities().forEach(entity -> {
            var tf = TransformComponent.Mapper.get(entity);
            tf.interpolatedPosition.set(
                    MathUtils.lerp(tf.prevPosition.x, tf.position.x, alpha),
                    MathUtils.lerp(tf.prevPosition.y, tf.position.y, alpha),
                    tf.position.z);
        });
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        var transform = TransformComponent.Mapper.get(entity);
        var move = MoveComponent.Mapper.get(entity);
        var player = PlayerComponent.Mapper.get(entity);

        if(player == null) {
            // Other movement
            moveEntity(transform, move, deltaTime);
        } else {
            var facing = FacingComponent.Mapper.get(entity);
            movePlayer(transform, move, player, facing, deltaTime);
        }

    }

    private void movePlayer(TransformComponent transform, MoveComponent move, PlayerComponent player, FacingComponent facing, float deltaTime) {
        switch (facing.direction) {
            case LEFT:
                move.moveSpeed.x = Math.min(0f, move.moveSpeed.x - HOR_ACCELERATION * deltaTime);
                break;
            case RIGHT:
                move.moveSpeed.x = Math.max(0f, move.moveSpeed.x + HOR_ACCELERATION * deltaTime);
                break;
            case DEFAULT:
                move.moveSpeed.x = 0f;
                break;
        }
        move.moveSpeed.x = MathUtils.clamp(move.moveSpeed.x, -MAX_HOR_SPEED, MAX_HOR_SPEED);
        move.moveSpeed.y = MathUtils.clamp(move.moveSpeed.y - VER_ACCELERATION * deltaTime,
                -MAX_VER_NEG_PLAYER_SPEED, MAX_VER_POS_PLAYER_SPEED);

        moveEntity(transform, move, deltaTime);
    }

    private void moveEntity(TransformComponent transform, MoveComponent move, float deltaTime) {
        transform.position.x = MathUtils.clamp(
                transform.position.x + move.moveSpeed.x * deltaTime,
                0f,
                V_WIDTH - transform.scale.x);
        transform.position.y = MathUtils.clamp(
                transform.position.y + move.moveSpeed.y * deltaTime,
                0f,
                V_HEIGHT - transform.scale.y);
    }
}
