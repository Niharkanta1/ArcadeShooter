package com.dw.shooter.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;

import static com.dw.shooter.MainGame.UNIT_SCALE;

/**
 * @author nihar
 * @date 02-07-2022
 * @project ArcadeShooter
 */
public class GraphicsComponent implements Pool.Poolable, Component {
    public static final ComponentMapper<GraphicsComponent> Mapper = ComponentMapper.getFor(GraphicsComponent.class);
    public Sprite sprite = new Sprite();

    @Override
    public void reset() {
        sprite.setTexture(null);
        sprite.setColor(Color.WHITE);
    }

    public void setSpriteRegion(TextureRegion region) {
        sprite.setRegion(region);
        sprite.setSize(region.getRegionWidth() * UNIT_SCALE, region.getRegionHeight() * UNIT_SCALE);
        sprite.setOriginCenter();
    }
}
