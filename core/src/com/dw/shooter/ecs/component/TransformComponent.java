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
    public static final ComponentMapper<TransformComponent> Mapper = ComponentMapper.getFor(TransformComponent.class);

    public Vector3 position = new Vector3();
    public Vector2 scale = new Vector2(1.0f, 1.0f);
    public float rotationDeg = 0.0f;
    public boolean isHidden = false;

    @Override
    public void reset() {
        position.set(Vector3.Zero);
        scale.set(1.0f, 1.0f);
        rotationDeg = 0.0f;
        isHidden = false;
    }

}
