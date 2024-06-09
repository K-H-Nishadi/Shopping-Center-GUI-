package com.ws.uitl;

public interface Validator<T> {
    T validate(String input) throws IllegalArgumentException;
}
