package com.trustly.montyhall.gameshow;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.trustly.montyhall.gameshow.GameShow;
import com.trustly.montyhall.gameshow.exception.InvalidGameParameterException;
import com.trustly.montyhall.log.hadler.LogHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GameShowTests {

    @Autowired
    private GameShow gameShow;

    private static LogHandler handler;

    private static Logger logger;


    @BeforeAll
    public static void setup() {
        logger = Logger.getLogger(GameShow.class.getName());
        handler = new LogHandler();
        handler.setLevel(Level.ALL);
        logger.addHandler(handler);

    }

    @Test
    public void verifyNegativeInteractionValue() {
        Assertions.assertThrows(InvalidGameParameterException.class, () ->
                gameShow.executeInteractions(-1, 100, 3));
        ;
    }

    @Test
    public void verifyNegativeDoorsValue() {
        Assertions.assertThrows(InvalidGameParameterException.class, () ->
                gameShow.executeInteractions(100, -10, 0));
        ;
    }

    @Test
    public void verifyNegativePrizesValue() {
        Assertions.assertThrows(InvalidGameParameterException.class, () ->
                gameShow.executeInteractions(100, 10, -1));
        ;
    }

    @Test
    public void verifyPrizeHigherThanDoorValue() {
        Assertions.assertThrows(InvalidGameParameterException.class, () ->
                gameShow.executeInteractions(100, 10, 30));
        ;
    }

    @Test
    public void verifyLogMessage() throws InvalidGameParameterException {
        gameShow.executeInteractions(10, 3, 1);
        assertEquals(Level.INFO, handler.checkLevel());

    }



}
