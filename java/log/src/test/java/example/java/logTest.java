package example.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class logTest {

    @Test
    public void test(){
        logger.trace("simple trace test");
        logger.debug("simple debug test");
        logger.warn("simple warn test");
        logger.info("simple info test");
        logger.error("simple error test");
        logger.fatal("simple fatal test");
    }

    private static Logger logger = LogManager.getLogger();
}
