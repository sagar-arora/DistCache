package com.arorasagar.cache.meta;

import com.arorasagar.cache.model.KeySpace;

import java.util.Map;

public interface MetadataStorage {

    void init();

    Map<String, KeySpace> getKeySpaces();

    void storeKeyspace(KeySpace keySpace);

    void updateKeyspace(KeySpace keySpace);

    void loadKeySpaces();
}
