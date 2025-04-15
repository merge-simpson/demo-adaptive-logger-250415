package nettee.log;

import org.slf4j.event.Level;

import java.util.Set;

public enum LogLevel {
//    ALL, // << 다른 라이브러리의 로그 레벨 제공할 때 ALL도 제공할 때가 있음.
    TRACE(Level.TRACE), // <<< 근데 얘가 ALL 역할로 나온 애라서, ALL은 중복된 역할.
                        // 아마 ALL을 쓴 사람은 TRACE보다 높은 수위의 무언가가 나올 때, ALL 로그가 영향을 받지 않고 사용 가능할 겅시라고 생각한 것 아닐지.
    DEBUG(Level.DEBUG),
    INFO(Level.INFO),
    WARN(Level.WARN),
    ERROR(Level.ERROR),
    OFF(null);

    private final Level level;

    LogLevel(Level level) {
        this.level = level;
    }

    public static LogLevel parse(Level level) {
        assert Set.of(Level.TRACE, Level.DEBUG, Level.INFO, Level.WARN, Level.ERROR).contains(level);

        return switch (level) {
            case TRACE -> TRACE;
            case DEBUG -> DEBUG;
            case INFO -> INFO;
            case WARN -> WARN;
            case ERROR -> ERROR;
            case null -> OFF;
        };
    }

    public Level toSlf4jLevel() {
        return level;
    }
}
