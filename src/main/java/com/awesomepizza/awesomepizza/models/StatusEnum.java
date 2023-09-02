package com.awesomepizza.awesomepizza.models;


public enum StatusEnum {
    QUEUED,
    ONGOING,
    READY,
    FULFILLED;

    public StatusEnum nextStatus() {
        switch (this) {
            case QUEUED:
                return ONGOING;
            case ONGOING:
                return READY;
            case READY:
                return FULFILLED;
            case FULFILLED:
                // Non c'è stato successivo
                return this;
            default:
                throw new IllegalArgumentException("Stato non valido: " + this);
        }
    }
}

