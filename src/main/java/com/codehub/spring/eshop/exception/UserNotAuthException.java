package com.codehub.spring.eshop.exception;

/**
 * Created by Dimitris on 21/5/2018.
 */
public class UserNotAuthException extends EShopException {

    public UserNotAuthException(String msg) {
        super(msg);
    }
}
