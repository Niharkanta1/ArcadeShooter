package com.dw.shooter.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.dw.shooter.component.PlayerComponent;
import com.dw.shooter.component.RemoveComponent;
import com.dw.shooter.component.TransformComponent;

/**
 * @author nihar
 * @date 03-07-2022
 * @project ArcadeShooter
 */
public class DebugSystem extends IntervalIteratingSystem {
    private static final float WINDOW_INFO_UPDATE_RATE = 0.25f;
    public DebugSystem() {
        super(Family.all(PlayerComponent.class).exclude(RemoveComponent.class).get(), WINDOW_INFO_UPDATE_RATE);
    }

    @Override
    protected void processEntity(Entity entity) {
        var transform = TransformComponent.Mapper.get(entity);
        var player = PlayerComponent.Mapper.get(entity);
        if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_1)) {
            transform.position.y = 1f;
            player.life = 1f;
            player.shield = 0f;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_2)) {
            player.shield = Math.min(player.maxShield, player.shield + 25f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_3)) {
            player.shield = Math.max(0f, player.shield - 25f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_4)) {
            getEngine().getSystem(MoveSystem.class).setProcessing(false);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_5)) {
            getEngine().getSystem(MoveSystem.class).setProcessing(true);
        }

        Gdx.graphics.setTitle("Debug:: Pos:"+transform.position+" Life:"+player.life+" Shield:"+player.shield);
    }
}
