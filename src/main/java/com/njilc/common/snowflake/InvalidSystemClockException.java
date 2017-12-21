package com.njilc.common.snowflake;

public class InvalidSystemClockException extends RuntimeException {
    public InvalidSystemClockException(String message) {
        super(message);
    }
}