package com.dw.shooter.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Pool;

/**
 * @author nihar
 * @date 02-07-2022
 * @project ArcadeShooter
 */
public class GraphicsComponent implements Pool.Poolable, Component {
    public static final ComponentMapper<GraphicsComponent> Map = ComponentMapper.getFor(GraphicsComponent.class);
    public Sprite sprite = new Sprite();

    @Override
    public void reset() {
        sprite.setTexture(null);
        sprite.setColor(Color.WHITE);
    }
}
