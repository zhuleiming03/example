package example.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class Slf4j {

    private static final Logger logger = LoggerFactory.getLogger(Slf4j.class);

    static void logTest() {
        logger.trace("simple TRACE test");
        logger.debug("simple DEBUG test");
        logger.info("simple {} test", "INFO");
        logger.warn("simple WARN test");
        logger.error("simple ERROR test");
    }
}
