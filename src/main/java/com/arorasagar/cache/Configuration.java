package com.arorasagar.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Configuration {
    int replicationFactor;
    String id;
    String address;
    int port;
    List<Node> nodes;
    boolean localMode;
    String dataDirectory;

    public boolean isLocalMode() {
        return localMode;
    }

    public static Configuration fromJsonFile(File jsonFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Configuration
                configuration = objectMapper.readValue(jsonFile, Configuration.class);

        return configuration;
    }
}
