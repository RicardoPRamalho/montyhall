package com.trustly.montyhall.log.hadler;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;


public class LogHandler extends Handler
{
    Level lastLevel = Level.FINEST;

    public Level checkLevel() {
        return lastLevel;
    }

    public void publish(LogRecord record) {
        lastLevel = record.getLevel();
    }

    public void close(){}
    public void flush(){}
}