package com.hsbc.testing.automation.spock.aop.logging;

import org.apache.log4j.*;
import org.aspectj.lang.JoinPoint;

import java.io.IOException;

/**
 * Created by sudhe on 30-07-2016.
 */
public class Logging {
    private static Logger logger =Logger.getLogger("");
    private Logging(){
    }

    public static Logging createLogging(){
        PatternLayout patternLayout = new PatternLayout();
        Appender appender = null;
        try {
            patternLayout.setConversionPattern("%d{YYYY-MMM-dd HH:mm:ss,SSS} [%-5p] : %m%n");
            appender = new FileAppender(patternLayout,"spock-aop.log",false);
        } catch (IOException e) {
            appender= new ConsoleAppender(patternLayout);
        }
        logger.addAppender(appender);
        logger.setLevel(Level.INFO);
        return new Logging();
    }

    public void logEntry(final JoinPoint joinPoint){
        StringBuffer message = new StringBuffer("Entering");
        message.append(" ");
        message.append(joinPoint.getSignature());
        logger.info(message);
    }

    public void logExit(final JoinPoint joinPoint){
        StringBuffer message = new StringBuffer("Exited");
        message.append(" ");
        message.append(joinPoint.getSignature());
        logger.info(message);
    }

    public void logReturn(Object returnValue){
        if (returnValue!=null) {
            StringBuffer message = new StringBuffer("Returned");
            message.append(" ");
            message.append(returnValue);
            logger.info(message);
        }
    }

    public void logException(Exception exception){
        StringBuffer message = new StringBuffer("Exception Occurred");
        message.append(" ");
        logger.fatal(message,exception);

    }
}
