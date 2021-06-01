package com.trustly.montyhall.gameshow.exception;

/**
 *
 * Exception used during validation of invalid args
 *
 * @author Ricardo Pereira Ramalho
 */
public class InvalidGameParameterException extends Exception {

    /**
     * Default class constructor
     *
     * @param message
     */
    public InvalidGameParameterException(String message){
        super(message);
    }
}
