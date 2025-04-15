package nettee.log;

import org.slf4j.Logger;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class LevelFixedLogger {
    private final Consumer<String> logConsumer;
    private final BiConsumer<String, Object[]> logBiConsumer;

    public LevelFixedLogger(AdaptiveLogger adaptiveLogger, LogLevel logLevel) {
        this(adaptiveLogger.getLogger(), logLevel);
    }

    public LevelFixedLogger(Logger logger, LogLevel logLevel) {
        switch (logLevel) {
            case TRACE:
                this.logConsumer = logger::trace;
                this.logBiConsumer = logger::trace;
                break;
            case DEBUG:
                this.logConsumer = logger::debug;
                this.logBiConsumer = logger::debug;
                break;
            case INFO:
                this.logConsumer = logger::info;
                this.logBiConsumer = logger::info;
                break;
            case WARN:
                this.logConsumer = logger::warn;
                this.logBiConsumer = logger::warn;
                break;
            case ERROR:
                this.logConsumer = logger::error;
                this.logBiConsumer = logger::error;
                break;
            case OFF:
                this.logConsumer = (str) -> {};
                this.logBiConsumer = (str, args) -> {};
                break;
            default:
                this.logConsumer = logger::info;
                this.logBiConsumer = logger::info;
                break;
        }
    }

    private static Consumer<String> getConsumer(Logger logger, LogLevel logLevel) {
        return switch (logLevel) {
            case TRACE -> logger::trace;
            case DEBUG -> logger::debug;
            case INFO ->  logger::info;
            case WARN ->  logger::warn;
            case ERROR -> logger::error;
            case OFF -> (str) -> {};
            default -> (str) -> {};
        };
    }

    private static BiConsumer<String, Object[]> getBiConsumer(Logger logger, LogLevel logLevel) {
        return switch (logLevel) {
            case TRACE -> logger::trace;
            case DEBUG -> logger::debug;
            case INFO ->  logger::info;
            case WARN ->  logger::warn;
            case ERROR -> logger::error;
            case OFF -> (str, args) -> {};
            default -> (str, args) -> {};
        };
    }

    public void log(String message) {
        logConsumer.accept(message);
    }

    public void log(String message, Object... args) {
        logBiConsumer.accept(message, args);
    }
}
