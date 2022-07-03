package com.dw.shooter.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool;

/**
 * @author nihar
 * @date 02-07-2022
 * @project ArcadeShooter
 */
public class TransformComponent implements Pool.Poolable, Component {
    public static final ComponentMapper<TransformComponent> Map = ComponentMapper.getFor(TransformComponent.class);

    public Vector3 position = new Vector3();
    public Vector2 size = new Vector2(1f, 1f);
    public float rotationDeg = 0f;

    @Override
    public void reset() {
        position.set(Vector3.Zero);
        size.set(1f, 1f);
        rotationDeg = 0f;
    }

}
