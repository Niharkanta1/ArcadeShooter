package com.dw.shooter.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.dw.shooter.component.RemoveComponent;
import com.dw.shooter.util.Logger;

/**
 * @author nihar
 * @date 03-07-2022
 * @project ArcadeShooter
 */
public class RemoveSystem extends IteratingSystem {
    private Logger log = Logger.getInstance(this.getClass());

    public RemoveSystem() {
        super(Family.all(RemoveComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        var remove = RemoveComponent.Mapper.get(entity);
        remove.delay -= deltaTime;
        if(remove.delay <= 0f) {
            log.info("Removing Entity: "+entity);
            getEngine().removeEntity(entity);
        }
    }
}
