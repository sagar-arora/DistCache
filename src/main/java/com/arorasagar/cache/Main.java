package com.arorasagar.cache;

import com.arorasagar.cache.storage.Cache;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;

@Log4j2
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
            String configPath = args[0];
            File f = new File(configPath);
        Configuration config = Configuration.fromJsonFile(f);

        log.info("Starting the server at the port: {}", config);

        Cache cache = new Cache();
        ConsistentHashingRing consistentHashingRing = new ConsistentHashingRing(config.getNodes());

        Server server = ServerBuilder
                .forPort(config.getPort())
                .addService(new CacheServerImpl(config, consistentHashingRing, cache))
                .build();

        server.start();
        server.awaitTermination();
    }
}
