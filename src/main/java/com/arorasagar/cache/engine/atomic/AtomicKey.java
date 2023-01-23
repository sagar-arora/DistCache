package com.arorasagar.cache.engine.atomic;

public abstract class AtomicKey implements Comparable<AtomicKey> {
    public String name;

    public abstract byte[] externalize();
}
