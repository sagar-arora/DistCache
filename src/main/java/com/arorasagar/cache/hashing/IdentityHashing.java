package com.arorasagar.cache.hashing;


public class IdentityHashing implements Hashing {

    @Override
    public int getHash(String key) throws NumberFormatException {
        return Integer.parseInt(key);
    }
}
