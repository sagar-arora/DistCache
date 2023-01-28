package com.arorasagar.cache.engine;

import com.arorasagar.cache.Token;
import com.arorasagar.cache.engine.atomic.AtomicKey;
import com.arorasagar.cache.engine.atomic.AtomicValue;

import java.util.Map;

public class Entry {
    Token token;
    Map<AtomicKey, AtomicValue> cells;
}
