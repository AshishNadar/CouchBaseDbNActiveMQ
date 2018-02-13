package com.cv.vin.subscriber.core;

import io.dropwizard.Configuration;

public class VinSubscriberConfiguration extends Configuration {
    private String couchbaseClusterIp, couchbaseBucketName, couchbasePassword,activeMqServer, activeMqUsername, activeMqPwd;

    public String getCouchbaseClusterIp() {
        return couchbaseClusterIp;
    }

    public void setCouchbaseClusterIp(String couchbaseClusterIp) {
        this.couchbaseClusterIp = couchbaseClusterIp;
    }

    public String getCouchbaseBucketName() {
        return couchbaseBucketName;
    }

    public void setCouchbaseBucketName(String couchbaseBucketName) {
        this.couchbaseBucketName = couchbaseBucketName;
    }

    public String getCouchbasePassword() {
        return couchbasePassword;
    }

    public void setCouchbasePassword(String couchbasePassword) {
        this.couchbasePassword = couchbasePassword;
    }

    public String getActiveMqServer() {
        return activeMqServer;
    }

    public void setActiveMqServer(String activeMqServer) {
        this.activeMqServer = activeMqServer;
    }

    public String getActiveMqUsername() {
        return activeMqUsername;
    }

    public void setActiveMqUsername(String activeMqUsername) {
        this.activeMqUsername = activeMqUsername;
    }

    public String getActiveMqPwd() {
        return activeMqPwd;
    }

    public void setActiveMqPwd(String activeMqPwd) {
        this.activeMqPwd = activeMqPwd;
    }
}
