package com.decathlon.oms.config.exception;

public class InternalServerEeception extends RuntimeException {
    public InternalServerEeception() {
        super();
    }
    public InternalServerEeception(String message, Throwable cause) {
        super(message, cause);
    }
    public InternalServerEeception(String message) {
        super(message);
    }
    public InternalServerEeception(Throwable cause) {
        super(cause);
    }
}
