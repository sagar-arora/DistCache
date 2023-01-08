package com.arorasagar.cache.cluster;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
public class ClusterMember {
    private String host;
    private int port;
    private long heatbeat;
    private String id;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = result * prime + ((host == null) ? 0 : host.hashCode());
        result = result * prime + port;

        return result;
    }

    @Override
    public boolean equals(Object clusterMember) {
        if (this == clusterMember) {
            return true;
        }
        if (clusterMember == null) return false;
        if (!(clusterMember instanceof ClusterMember that)) {
            return false;
        }

        if (!host.equals(that.host)) {
            return false;
        }
        return port == that.port;
    }
}
