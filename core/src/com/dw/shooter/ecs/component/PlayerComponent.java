package com.dw.shooter.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.utils.Pool;

/**
 * @author nihar
 * @date 03-07-2022
 * @project ArcadeShooter
 */
public class PlayerComponent implements Pool.Poolable, Component {
    public static final ComponentMapper<PlayerComponent> Mapper = ComponentMapper.getFor(PlayerComponent.class);
    public static final float MAX_LIFE = 100f;
    public static final float MAX_SHIELD = 100f;
    float life = MAX_LIFE;
    float maxLife = MAX_LIFE;
    float shield = 0f;
    float maxShield = MAX_SHIELD;
    float distance = 0f;

    @Override
    public void reset() {
        float life = MAX_LIFE;
        float maxLife = MAX_LIFE;
        float shield = 0f;
        float maxShield = MAX_SHIELD;
        float distance = 0f;
    }
}
