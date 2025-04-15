package nettee.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class AdaptiveLogger {
    // delegation (위임)
    private final Logger logger;

    public AdaptiveLogger(Class<?> clazz) {
        this(
                Objects.requireNonNull(clazz, "...").getName()
        );
    }

    public AdaptiveLogger(String className) {
        Objects.requireNonNull(className, "...");
        this.logger = LoggerFactory.getLogger(className);
    }

    public static AdaptiveLogger getLogger(Class<?> clazz) {
        return CachedAdaptiveLogger.getLogger(clazz);
    }

    public static AdaptiveLogger getLogger(String className) {
        return CachedAdaptiveLogger.getLogger(className);
    }

    public static AdaptiveLogger getLoggerNonCached(Class<?> clazz) {
        return new AdaptiveLogger(clazz);
    }

    public static AdaptiveLogger getLoggerNonCached(String className) {
        return new AdaptiveLogger(className);
    }

    public LevelFixedLogger with(LogLevel level) {
        return new LevelFixedLogger(logger, level);
    }

    public Logger getLogger() {
        return logger;
    }

    public void trace(String message) {
        logger.info(message);
    }

    public void trace(String format, Object... args) {
        logger.trace(format, args);
    }

    public void debug(String s) {
        logger.debug(s);
    }

    public void debug(String s, Object... objects) {
        logger.debug(s, objects);
    }

    public void info(String s) {
        logger.info(s);
    }

    public void info(String s, Object... objects) {
        logger.info(s, objects);
    }

    public void warn(String s) {
        logger.warn(s);
    }

    public void warn(String s, Object... objects) {
        logger.warn(s, objects);
    }

    public void error(String s) {
        logger.error(s);
    }

    public void error(String s, Object... objects) {
        logger.error(s, objects);
    }
}
