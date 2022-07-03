package com.dw.shooter.util;

import com.badlogic.ashley.core.Entity;
import com.dw.shooter.component.TransformComponent;

import java.util.Comparator;

/**
 * @author nihar
 * @date 02-07-2022
 * @project ArcadeShooter
 */
public class ZComparator implements Comparator<Entity> {

    @Override
    public int compare(Entity e1, Entity e2) {
        float zDiff = Float.compare(TransformComponent.Mapper.get(e1).position.z, TransformComponent.Mapper.get(e1).position.z);
        return zDiff == 0 ?  Float.compare(TransformComponent.Mapper.get(e1).position.y, TransformComponent.Mapper.get(e1).position.y): (int) zDiff;
    }
}
