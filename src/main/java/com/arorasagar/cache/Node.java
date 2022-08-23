package com.arorasagar.cache;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@EqualsAndHashCode
@Getter
@Builder
@ToString
public class Node implements RingNode {
    String address;
    int port;
    String id;

    public Node(String address, int port, String id) {
        this.address = address;
        this.port = port;
        this.id = id;
    }

    @Override
    public String getKey() {
        return this.getId();
    }
}
