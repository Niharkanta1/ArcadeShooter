package com.dw.shooter.screen;

/**
 * @author nihar
 * @date 02-07-2022
 * @project ArcadeShooter
 */
public enum ScreenType {
    First(FirstScreen.class),
    Game(GameScreen.class),
    Loading(LoadingScreen.class),
    Menu(MenuScreen.class),
    Pause(PauseScreen.class);

    private final Class<? extends AbstractScreen> screenClass;

    ScreenType(Class<? extends AbstractScreen> screenClass) {
        this.screenClass = screenClass;
    }

    public Class<? extends AbstractScreen> getScreenClass() {
        return screenClass;
    }

}
