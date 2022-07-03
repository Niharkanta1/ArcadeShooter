package com.dw.shooter.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.dw.shooter.ecs.component.GraphicsComponent;
import com.dw.shooter.ecs.component.TransformComponent;
import com.dw.shooter.util.ZComparator;

import java.util.Comparator;

/**
 * @author nihar
 * @date 02-07-2022
 * @project ArcadeShooter
 */
public class RenderSystem extends SortedIteratingSystem {
    static final float PPM = 16.0f; // sets the amount of pixels each metre of box2d objects contains
    // this gets the height and width of our camera frustum based off the width and height of the screen and our pixel per meter ratio
    static final float FRUSTUM_WIDTH = Gdx.graphics.getWidth()/PPM;
    static final float FRUSTUM_HEIGHT = Gdx.graphics.getHeight()/PPM;
    public static final float PIXELS_TO_METRES = 1.0f / PPM; // get the ratio for converting pixels to metres

    public RenderSystem() {
        super(Family.all(TransformComponent.class, GraphicsComponent.class).get(),
                new ZComparator());
    }

    public RenderSystem(Family family, Comparator<Entity> comparator) {
        super(family, comparator);
    }

    public RenderSystem(Family family, Comparator<Entity> comparator, int priority) {
        super(family, comparator, priority);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transform = TransformComponent.Map.get(entity);
        GraphicsComponent graphicsComponent = GraphicsComponent.Map.get(entity);

    }

    // static method to get screen width in metres
    private static Vector2 meterDimensions = new Vector2();
    private static Vector2 pixelDimensions = new Vector2();

    public static Vector2 getScreenSizeInMeters(){
        meterDimensions.set(Gdx.graphics.getWidth()*PIXELS_TO_METRES,
                Gdx.graphics.getHeight()*PIXELS_TO_METRES);
        return meterDimensions;
    }

    // static method to get screen size in pixels
    public static Vector2 getScreenSizeInPixels(){
        pixelDimensions.set(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        return pixelDimensions;
    }

    // convenience method to convert pixels to meters
    public static float PixelsToMeters(float pixelValue){
        return pixelValue * PIXELS_TO_METRES;
    }
}
