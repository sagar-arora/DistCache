package com.arorasagar.cache.hashing;

public class JavaHashing implements Hashing {

    @Override
    public int getHash(String key) {
        return key.hashCode();
    }
}
