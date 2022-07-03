package com.dw.shooter.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.dw.shooter.component.PlayerComponent;
import com.dw.shooter.component.RemoveComponent;
import com.dw.shooter.component.TransformComponent;

/**
 * @author nihar
 * @date 03-07-2022
 * @project ArcadeShooter
 */
public class DamageSystem extends IteratingSystem {

    public DamageSystem() {
        super(Family.all(PlayerComponent.class, TransformComponent.class).exclude(RemoveComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }

}
