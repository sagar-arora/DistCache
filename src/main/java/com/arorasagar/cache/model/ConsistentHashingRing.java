package com.arorasagar.cache.model;

import com.arorasagar.cache.Node;

import java.util.TreeMap;

public class ConsistentHashingRing {
    TreeMap<Long, Node> ring = new TreeMap<>();

}
