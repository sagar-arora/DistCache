package com.arorasagar.cache;

import com.arorasagar.cache.messages.*;
import com.arorasagar.cache.storage.Cache;
import com.google.common.collect.ImmutableList;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import io.grpc.testing.GrpcCleanupRule;

import java.io.IOException;
import java.util.List;
@RunWith(JUnit4.class)
public class CacheServerImplTest {

    CacheServerImpl cacheServer;
    public static final Node NODE1 = Node.builder().address("localhost").id("400").port(8080).build();
    public static final Node NODE2 = Node.builder().address("localhost").id("1000").port(8081).build();
    public static final Node NODE3 = Node.builder().address("localhost").id("2000").port(8082).build();
    List<Node> NODES = ImmutableList.of(NODE1, NODE2, NODE3);
    ConsistentHashingRing consistentHashingRing;
        /**
         * This creates and starts an in-process server, and creates a client with an in-process channel.
         * When the test is done, it also shuts down the in-process client and server.
         */
        @Rule
        public final GrpcCleanupRule grpcCleanupRule = new GrpcCleanupRule();

        @Before
        public void setup() throws IOException {
           /* consistentHashingRing = new ConsistentHashingRing(NODES);
            Configuration configuration1 = Configuration.builder().address("localhost").id("400").port(8080).nodes(NODES).build();
            grpcCleanupRule.register(InProcessServerBuilder.forPort(NODE1.getPort()).directExecutor()
                    .addService(new CacheServerImpl(configuration1, consistentHashingRing, new Cache())).build().start());

            Configuration configuration2 = Configuration.builder().address("localhost").id("1000").port(8081).nodes(NODES).build();
            grpcCleanupRule.register(InProcessServerBuilder.forPort(NODE2.getPort()).directExecutor()
                    .addService(new CacheServerImpl(configuration2, consistentHashingRing, new Cache())).build().start());


            Configuration configuration3 = Configuration.builder().address("localhost").id("2000").port(8082).nodes(NODES).build();
            grpcCleanupRule.register(InProcessServerBuilder.forPort(NODE2.getPort()).directExecutor()
                    .addService(new CacheServerImpl(configuration3, consistentHashingRing, new Cache())).build().start());*/

        }

    @Test
    public void testGetFromSameNode() throws IOException {
        consistentHashingRing = new ConsistentHashingRing(NODES);
        // Generate a unique in-process server name.
        String serverName = InProcessServerBuilder.generateName();
        Configuration configuration1 = Configuration.builder().address("localhost").id("400").port(8080).nodes(NODES).build();
        Cache cache = new Cache();
        grpcCleanupRule.register(InProcessServerBuilder.forName(serverName).directExecutor()
                .addService(new CacheServerImpl(configuration1, consistentHashingRing, cache)).build().start());

        CacheServerGrpc.CacheServerBlockingStub blockingStub = CacheServerGrpc.newBlockingStub(
                // Create a client channel and register for automatic graceful shutdown.
                grpcCleanupRule.register(InProcessChannelBuilder.forName(serverName).directExecutor().build()));

        PutRequest putRequest = PutRequest.newBuilder().setKey("300").setVal("399").build();

        PutResponse putResponse = blockingStub.put(putRequest);
        Assert.assertEquals("399", cache.cache.get("300"));

        GetRequest getRequest = GetRequest.newBuilder().setKey("300").build();
        GetResponse getResponse = blockingStub.get(getRequest);
        Assert.assertEquals("399", getResponse.getVal());
    }


    @Test
    public void testGetToAnotherNode() throws IOException {

    }
}
