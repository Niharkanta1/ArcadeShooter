package com.dw.shooter.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.utils.Pool;
import com.dw.shooter.enums.FacingDirection;

/**
 * @author nihar
 * @date 03-07-2022
 * @project ArcadeShooter
 */
public class FacingComponent implements Component, Pool.Poolable {
    public static final ComponentMapper<FacingComponent> Mapper = ComponentMapper.getFor(FacingComponent.class);
    public FacingDirection direction = FacingDirection.DEFAULT;
    public FacingDirection lastDirection = FacingDirection.DEFAULT;

    @Override
    public void reset() {
        direction = FacingDirection.DEFAULT;
        lastDirection = null;
    }
}

