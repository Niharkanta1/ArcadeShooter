package com.dw.shooter.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

/**
 * @author nihar
 * @date 03-07-2022
 * @project ArcadeShooter
 */
public class MoveComponent implements Pool.Poolable, Component {
    public static final ComponentMapper<MoveComponent> Mapper = ComponentMapper.getFor(MoveComponent.class);

    public Vector2 moveSpeed = new Vector2();

    @Override
    public void reset() {
        moveSpeed = Vector2.Zero;
    }
}
