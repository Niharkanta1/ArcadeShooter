package com.dw.shooter.util;

import com.badlogic.ashley.core.Entity;
import com.dw.shooter.ecs.component.TransformComponent;

import java.util.Comparator;

/**
 * @author nihar
 * @date 02-07-2022
 * @project ArcadeShooter
 */
public class ZComparator implements Comparator<Entity> {

    @Override
    public int compare(Entity e1, Entity e2) {
        float zDiff = TransformComponent.Map.get(e1).position.z - TransformComponent.Map.get(e1).position.z;
        return (int) (zDiff == 0 ? TransformComponent.Map.get(e1).position.y - TransformComponent.Map.get(e1).position.y : zDiff);
    }
}