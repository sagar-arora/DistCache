package com.arorasagar.cache.client;

import com.arorasagar.cache.messages.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.*;

@EqualsAndHashCode
@ToString
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CacheClient {
    private String address;
    private int port;

    ManagedChannel buildChannel() {
       return ManagedChannelBuilder.forAddress(address, port)
                .usePlaintext()
                .build();
    }

    public String get(String key) {
        ManagedChannel channel = buildChannel();

        CacheServerGrpc.CacheServerBlockingStub stub = CacheServerGrpc.newBlockingStub(channel);

        GetResponse getResponse = stub.get(GetRequest.newBuilder().setKey(key).build());

        return getResponse.getVal();
    }

    public String put(String key, String val) {
        ManagedChannel channel = buildChannel();

        CacheServerGrpc.CacheServerBlockingStub stub = CacheServerGrpc.newBlockingStub(channel);

        PutResponse putResponse = stub.put(PutRequest.newBuilder().setKey(key).setVal(val).build());

        return putResponse.getKey();
    }
}
