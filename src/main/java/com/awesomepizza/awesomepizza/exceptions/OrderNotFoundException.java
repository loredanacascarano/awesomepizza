package com.awesomepizza.awesomepizza.exceptions;

import java.util.function.Supplier;

public class OrderNotFoundException implements Supplier<Throwable> {
    public OrderNotFoundException(String ordine_non_trovato) {
    }


    @Override
    public Throwable get() {
        return null;
    }
}
