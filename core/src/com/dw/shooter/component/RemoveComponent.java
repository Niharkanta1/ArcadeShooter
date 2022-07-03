package com.dw.shooter.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.utils.Pool;

/**
 * @author nihar
 * @date 03-07-2022
 * @project ArcadeShooter
 */
public class RemoveComponent implements Pool.Poolable, Component {
    public static final ComponentMapper<RemoveComponent> Mapper = ComponentMapper.getFor(RemoveComponent.class);

    public float delay = 0.0f;

    @Override
    public void reset() {
        delay = 0.0f;
    }
}
