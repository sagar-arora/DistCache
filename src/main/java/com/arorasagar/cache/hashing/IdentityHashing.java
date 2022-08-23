package com.arorasagar.cache.hashing;


public class IdentityHashing implements Hashing {

    @Override
    public int getHash(String key) {
        return Integer.parseInt(key);
    }
}
