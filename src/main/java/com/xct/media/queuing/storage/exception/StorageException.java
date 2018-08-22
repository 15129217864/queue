package com.xct.media.queuing.storage.exception;

/**
 * Created by Chris on 2017/9/16.
 */
public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
