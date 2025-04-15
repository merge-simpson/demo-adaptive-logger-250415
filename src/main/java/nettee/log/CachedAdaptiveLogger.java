package nettee.log;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class CachedAdaptiveLogger extends AdaptiveLogger {

    private final Map<LogLevel, LevelFixedLogger> cache = new ConcurrentHashMap<>();

    public CachedAdaptiveLogger(Class<?> clazz) {
        super(clazz);
    }

    public CachedAdaptiveLogger(String className) {
        super(className);
    }

    public static AdaptiveLogger getLogger(Class<?> clazz) {
        Objects.requireNonNull(clazz);
        return getLogger(clazz.getName());
    }

    public static AdaptiveLogger getLogger(String className) {
        Objects.requireNonNull(className);
        return Holder.MAP.computeIfAbsent(className, (ignore) -> new CachedAdaptiveLogger(className));
    }

    @Override
    public LevelFixedLogger with(LogLevel level) {
        return cache.computeIfAbsent(level, (ignore) -> super.with(level));
    }

    private static class Holder {
        private static final Map<String, AdaptiveLogger> MAP = new ConcurrentHashMap<>();
    }
}
