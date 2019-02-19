package com.majidalfuttaim;

import com.majidalfuttaim.resources.HotelsResource;
import com.majidalfuttaim.resources.ProviderResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class hotelsServiceApplication extends Application<hotelsServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new hotelsServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "hotelsService";
    }

    @Override
    public void initialize(final Bootstrap<hotelsServiceConfiguration> bootstrap) {
    }

    @Override
    public void run(final hotelsServiceConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(new ProviderResource());
        environment.jersey().register(new HotelsResource());
    }

}
