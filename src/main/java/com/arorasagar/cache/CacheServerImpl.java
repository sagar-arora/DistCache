package com.arorasagar.cache;

import com.arorasagar.cache.client.CacheClient;
import com.arorasagar.cache.messages.*;
import io.grpc.stub.StreamObserver;
import lombok.extern.log4j.Log4j2;

import java.util.Optional;

@Log4j2
public class CacheServerImpl extends CacheServerGrpc.CacheServerImplBase {

    ConsistentHashingRing consistentHashingRing;
    Configuration configuration;
    Cache cache;
    Node self;
    public CacheServerImpl(Configuration configuration,
                           ConsistentHashingRing consistentHashingRing,
                           Cache cache) {
        this.consistentHashingRing = consistentHashingRing;
        this.configuration = configuration;
        this.cache = cache;
        this.self = new Node(configuration.getAddress(), configuration.getPort(), configuration.getId());
    }

    @Override
    public void get(GetRequest request, StreamObserver<GetResponse> responseObserver) {
        String key = request.getKey();
        Optional<Node> mainNodeOptional = this.consistentHashingRing.getNode(key);

        if (!mainNodeOptional.isPresent()) {
            log.info("node is not found on the ring...");
        }

        Node mainNode = mainNodeOptional.get();

        if (mainNode.equals(this.self)) {
            String val = cache.cache.get(key);
            GetResponse getResponse = GetResponse.newBuilder().setVal(val).build();
            responseObserver.onNext(getResponse);
            responseObserver.onCompleted();
            return;
        }

        String val = getRouteRequest(mainNode, key);

        GetResponse getResponse = GetResponse.newBuilder().setVal(val).build();
        responseObserver.onNext(getResponse);
        responseObserver.onCompleted();
    }

    public String getRouteRequest(Node node, String key) {
        CacheClient cacheClient = new CacheClient(node.getAddress(), node.getPort());
        return cacheClient.get(key);
    }

    @Override
    public void put(PutRequest request, StreamObserver<PutResponse> responseObserver) {
        String key = request.getKey();
        String val = request.getVal();
        Optional<Node> mainNodeOptional = this.consistentHashingRing.getNode(key);

        if (!mainNodeOptional.isPresent()) {
            log.info("node is not found on the ring...");
        }

        Node mainNode = mainNodeOptional.get();

        if (mainNode.equals(this.self)) {
            cache.cache.put(key, val);
            PutResponse response = PutResponse.newBuilder().setKey(val).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            return;
        }

        String newKey = putRouteRequest(mainNode, key, val);

        PutResponse response = PutResponse.newBuilder().setKey(newKey).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public String putRouteRequest(Node node, String key, String val) {
        CacheClient cacheClient = new CacheClient(node.getAddress(), node.getPort());
        return cacheClient.put(key, val);
    }
}
