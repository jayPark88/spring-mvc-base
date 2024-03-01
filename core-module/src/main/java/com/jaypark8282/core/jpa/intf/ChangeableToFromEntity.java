package com.jaypark8282.core.jpa.intf;

public interface ChangeableToFromEntity<E> {
    E to();

    void from(E entity);
}
