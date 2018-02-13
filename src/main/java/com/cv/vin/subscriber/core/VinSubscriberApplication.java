package com.cv.vin.subscriber.core;

import com.cv.vin.subscriber.subscription.VinSubscriber;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class VinSubscriberApplication extends Application<VinSubscriberConfiguration> {
    @Override
    public void run(VinSubscriberConfiguration vinSubscriberConfiguration, Environment environment) throws Exception {

        VinSubscriber subscriber = new VinSubscriber(vinSubscriberConfiguration);
        subscriber.start();

    }

    public static void main(String[] args) {
        try {
            new VinSubscriberApplication().run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
