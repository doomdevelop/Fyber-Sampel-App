package com.doomdev.fybersampel.data.exception;

/**
 * Created by and on 17.01.16.
 */
public class WrongSignatureException extends IllegalStateException{

    private static final String MESSAGE = "Something went wrong. The signature check generate error, it mean that the response is not valid and will be ignore.";
    /**
     * Constructs a new {@code WrongSignatureException} with the current stack
     * trace and the specified detail message.
     *
     *
     */
    public WrongSignatureException() {
        super(MESSAGE);
    }

    /**
     * Returns the detail message which was provided when this
     * {@code Throwable} was created. Returns {@code null} if no message was
     * provided at creation time.
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
