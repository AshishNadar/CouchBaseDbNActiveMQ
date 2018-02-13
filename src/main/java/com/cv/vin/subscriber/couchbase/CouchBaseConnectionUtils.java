package com.cv.vin.subscriber.couchbase;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.RawJsonDocument;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.cv.vehicle.pojo.VehicleAgreement;
import com.cv.vin.subscriber.core.VinSubscriberConfiguration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class CouchBaseConnectionUtils {
    Bucket bucket;
    ObjectMapper mapper;
    public CouchBaseConnectionUtils(VinSubscriberConfiguration config){
        Cluster cluster = CouchbaseCluster.create(config.getCouchbaseClusterIp());
        bucket = cluster.openBucket(config.getCouchbaseBucketName(),config.getCouchbasePassword());
        mapper = new ObjectMapper();
    }
    public void store(VehicleAgreement vehicleAgreement){
        // If env needs to be defined manually for cases wherein the port numbers are not the default once
        //CouchbaseEnvironment env = DefaultCouchbaseEnvironment.builder().bootstrapHttpDirectPort(32801).bootstrapCarrierDirectPort(32796).build();
        //CouchbaseEnvironment env = DefaultCouchbaseEnvironment.builder().bootstrapHttpDirectPort(11207).bootstrapCarrierDirectPort(8091).build();
        //Cluster cluster = CouchbaseCluster.create(env,"localhost");

        try {
            bucket.insert(RawJsonDocument.create(vehicleAgreement.getVin(), mapper.writeValueAsString(vehicleAgreement)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
