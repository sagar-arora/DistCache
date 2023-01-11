package com.arorasagar.cache.router;


import com.arorasagar.cache.Destination;
import com.arorasagar.cache.ServerId;
import com.arorasagar.cache.Token;
import com.arorasagar.cache.cluster.ClusterMembership;

import java.util.List;

public interface Router {

    /**
     * Determine which hosts a message can be sent to. (in the future keyspace should hold a node list)
     * @return all hosts a given request can be routed to
     */
    List<Destination> routesTo(ServerId serverId, ClusterMembership clusterMembership, Token token);

}
