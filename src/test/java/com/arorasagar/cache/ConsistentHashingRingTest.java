package com.arorasagar.cache;

import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ConsistentHashingRingTest {

    public static final Node NODE1 = Node.builder().address("localhost").id("400").port(8080).build();
    public static final Node NODE2 = Node.builder().address("localhost").id("1000").port(8081).build();
    public static final Node NODE3 = Node.builder().address("localhost").id("2000").port(8082).build();
    List<Node> NODES = ImmutableList.of(NODE1, NODE2, NODE3);

    @Test
    public void testRing() {
        ConsistentHashingRing consistentHashingRing = new ConsistentHashingRing(NODES);
        Assert.assertEquals(NODE1, consistentHashingRing.getNode("200").get());
        Assert.assertEquals(NODE2, consistentHashingRing.getNode("800").get());
        Assert.assertEquals(NODE3, consistentHashingRing.getNode("1201").get());
        Assert.assertEquals(NODE1, consistentHashingRing.getNode("2001").get());
    }
}
