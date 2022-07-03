package com.dw.shooter.util;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;

/**
 * @author nihar
 * @date 02-07-2022
 * @project ArcadeShooter
 */
public class Logger {
    private static final Logger logger = new Logger();
    private static String className = null;
    public static Logger getInstance(Class<?> clazz) {
        className = clazz.getSimpleName();
        return logger;
    }

    public void info(String msg) {
        if(Gdx.app.getLogLevel() >= Application.LOG_DEBUG)
            Gdx.app.log(className, msg);
    }

    public void info(String msg, Object obj) {
        if(Gdx.app.getLogLevel() >= Application.LOG_DEBUG)
            Gdx.app.log(className, msg + ": "+ obj);
    }

    public void debug(String msg) {
        if(Gdx.app.getLogLevel() >= Application.LOG_DEBUG)
            Gdx.app.debug(className, msg);
    }

    public void debug(String msg, Object obj) {
        if(Gdx.app.getLogLevel() >= Application.LOG_DEBUG)
            Gdx.app.debug(className, msg + ": "+ obj);
    }

    public void error(String msg) {
        if(Gdx.app.getLogLevel() >= Application.LOG_DEBUG)
            Gdx.app.error(className, msg);
    }

    public void error(String msg, Throwable throwable) {
        if(Gdx.app.getLogLevel() >= Application.LOG_DEBUG)
            Gdx.app.error(className, msg, throwable);
    }

}