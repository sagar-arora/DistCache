package com.arorasagar.cache.engine;

import com.arorasagar.cache.Configuration;
import com.arorasagar.cache.Token;
import com.arorasagar.cache.engine.atomic.AtomicValue;
import com.arorasagar.cache.model.Table;

import java.io.Closeable;

public class SSTableImpl implements Closeable {

    public SSTableImpl(Table columnFamily) {

    }

    public void open(String id, Configuration configuration) {

    }

    AtomicValue get(Token row, String column) {
        return null;
    }

    public void close() {

    }
}
