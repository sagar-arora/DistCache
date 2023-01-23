package com.arorasagar.cache.meta;

import com.arorasagar.cache.model.Keyspace;

import java.util.Map;

public interface MetadataStorage {

    void init();

    Map<String, Keyspace> getKeySpaces();

    void storeKeyspace(Keyspace keySpace);

    void updateKeyspace(Keyspace keySpace);

    void loadKeySpaces();
}
