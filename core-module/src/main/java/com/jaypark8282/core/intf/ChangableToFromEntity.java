package com.jaypark8282.core.intf;

public interface ChangableToFromEntity<E>{
    public E to();
    public void from (E entity);
}

