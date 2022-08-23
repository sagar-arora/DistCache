package com.arorasagar.cache;

import com.arorasagar.cache.hashing.Hashing;
import com.arorasagar.cache.hashing.IdentityHashing;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

@Log4j2
public class ConsistentHashingRing {

    private final TreeMap<Integer, Node> ring = new TreeMap<>();
    private final Hashing hasher;

    public ConsistentHashingRing(List<Node> nodes) {
        this(nodes, new IdentityHashing());
    }

    public ConsistentHashingRing(List<Node> nodes, Hashing hasher) {
        this.hasher = hasher;
        for (Node node : nodes) {
            addNode(node);
        }
    }

    public void addNode(Node node) {
        int hash = hasher.getHash(node.getKey());
        log.info("added node: {} at the loc: {}", node, hash);
        ring.put(hash, node);
    }

    public Optional<Node> getNode(String key) {
        int hash = hasher.getHash(key);
        Integer val = ring.ceilingKey(hash);
        if (val == null) {
            return Optional.of(ring.firstEntry().getValue());
        }
        return Optional.of(ring.get(val));
    }
}
