package nettee.log;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class CachedAdaptiveLogger extends AdaptiveLogger {

    private final Map<LogLevel, LevelFixedLogger> cache = new ConcurrentHashMap<>();

    private CachedAdaptiveLogger(Class<?> clazz) {
        super(clazz);
    }

    private CachedAdaptiveLogger(String className) {
        super(className);
    }

    public static AdaptiveLogger getLogger(Class<?> clazz) {
        Objects.requireNonNull(clazz);
        return getLogger(clazz.getName());
    }

    public static AdaptiveLogger getLogger(String className) {
        Objects.requireNonNull(className);
        return Holder.MAP.computeIfAbsent(className, CachedAdaptiveLogger::new);
    }

    @Override
    public LevelFixedLogger with(LogLevel level) {
        return cache.computeIfAbsent(level, super::with);
    }

    private static class Holder {
        private static final Map<String, AdaptiveLogger> MAP = new ConcurrentHashMap<>();
    }
}
