package com.arorasagar.cache.cluster;

import com.arorasagar.cache.Configuration;
import com.arorasagar.cache.ServerId;

import java.util.List;

public abstract class ClusterMembership {

    protected Configuration configuration;
    protected ServerId serverId;

    public ClusterMembership(Configuration configuration, ServerId serverId){
        this.configuration = configuration;
        this.serverId = serverId;
    }

    public abstract void init();
    public abstract void shutdown();
    public abstract List<ClusterMember> getLiveMembers();
    public abstract List<ClusterMember> getDeadMembers();

    public String findHostnameForId(String id) {
        if (id.equalsIgnoreCase(serverId.getU())) {
            return configuration.getAddress();
        }
        for (ClusterMember cm : getLiveMembers()){
            if (id.equals(cm.getId())){
                return cm.getHost();
            }
        }
        for (ClusterMember cm : getDeadMembers()){
            if (id.equals(cm.getId())){
                return cm.getHost();
            }
        }
        return null;
    }
}
