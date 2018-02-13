package com.cv.vin.subscriber.subscription;

import javax.jms.*;

import com.cv.vehicle.pojo.VehicleAgreement;
import com.cv.vin.subscriber.core.VinSubscriberConfiguration;
import com.cv.vin.subscriber.couchbase.CouchBaseConnectionUtils;
import org.apache.activemq.ActiveMQConnectionFactory;
import java.util.ArrayList;
import java.util.Arrays;

public class VinSubscriber {

    private ActiveMQConnectionFactory connectionFactory;
    private String clientId="2";
    private Connection connection;
    private Session session;
    private MessageConsumer messageConsumer;
    private CouchBaseConnectionUtils couchBaseConnectionUtils;

    public VinSubscriber(VinSubscriberConfiguration config){
        try {
            connectionFactory = new ActiveMQConnectionFactory(config.getActiveMqUsername(), config.getActiveMqPwd(), System.getenv(config.getActiveMqServer()));
            connectionFactory.setTrustedPackages(new ArrayList(Arrays.asList("com.cv.vehicle.pojo".split(","))));
            connection = connectionFactory.createConnection();
            connection.setClientID(clientId);
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("vin");
            messageConsumer = session.createDurableSubscriber(topic, "VinSubcribtion");
            connection.start();
            couchBaseConnectionUtils = new CouchBaseConnectionUtils(config);
        }catch(JMSException e){
            e.printStackTrace();
        }
    }
    public void start(){


        try {

            MessageListener listener = this::onMessage;
            messageConsumer.setMessageListener(listener);

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private void onMessage(Message message) {
        try {
            if (message instanceof ObjectMessage) {
                ObjectMessage objectMessage = (ObjectMessage) message;
                VehicleAgreement vehicleAgreement = (VehicleAgreement) objectMessage.getObject();
                couchBaseConnectionUtils.store(vehicleAgreement);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
