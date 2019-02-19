package com.majidalfuttaim;

import io.dropwizard.testing.ConfigOverride;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import org.junit.jupiter.api.BeforeAll;

import static io.dropwizard.testing.ResourceHelpers.resourceFilePath;

public class TestBase {

    public static final DropwizardAppExtension<hotelsServiceConfiguration> dropwizardExtension =
            new DropwizardAppExtension<>(
                    hotelsServiceApplication.class, resourceFilePath("test-config.yml"),
                    ConfigOverride.config("server.applicationConnectors[0].port", "0"),
                    ConfigOverride.config("server.adminConnectors[0].port", "0"));

    @BeforeAll
    public static void init(){
        dropwizardExtension.before();
    }
}
