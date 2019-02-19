package com.majidalfuttaim.resources;

import com.majidalfuttaim.TestBase;
import com.majidalfuttaim.beans.Provider;
import com.majidalfuttaim.beans.ProviderResponseFields;
import com.majidalfuttaim.business.SystemProviders;
import com.majidalfuttaim.enums.ProviderDateFormatter;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProviderResourceTest extends TestBase {

    @Test
    public void getProviders() {

        List<Provider> providers = dropwizardExtension.client()
                .target("http://localhost:" +
                dropwizardExtension.getLocalPort() + "/providers")
                .request()
                .get(List.class);

        assertNotNull(providers);
        assertEquals(2, providers.size());
    }

    @Test
    public void addProvider() {
        ProviderResponseFields providerResponseFields = new ProviderResponseFields("hotelN", "hotelR", "hotelF", "Amenities", "disc");
        Provider provider = new Provider("addedProvider", "any-url", providerResponseFields, ProviderDateFormatter.ISO_LOCAL_DATE);
        Response response = dropwizardExtension.client()
                .target("http://localhost:" +
                        dropwizardExtension.getLocalPort() + "/providers")
                .request()
                .post(Entity.entity(provider,MediaType.APPLICATION_JSON));

        assertNotNull(response);
        assertEquals(200, response.getStatus());
        assertEquals(3,SystemProviders.getInstance().getProviders().size());
        SystemProviders.getInstance().getProviders().remove(new Provider("addedProvider"));
        assertEquals(2,SystemProviders.getInstance().getProviders().size());
    }
    public void addProviderFailed() {
        ProviderResponseFields providerResponseFields = new ProviderResponseFields("hotelN", "hotelR", "hotelF", "Amenities", "disc");
        Provider provider = new Provider("BestHotels", "any-url", providerResponseFields, ProviderDateFormatter.ISO_LOCAL_DATE);
        Response response = dropwizardExtension.client()
                .target("http://localhost:" +
                        dropwizardExtension.getLocalPort() + "/providers")
                .request()
                .post(Entity.entity(provider,MediaType.APPLICATION_JSON));

        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals(2,SystemProviders.getInstance().getProviders().size());
    }
    @Test
    public void removeProvider() {
        Provider provider = new Provider("toBeRemovedProvider");
        SystemProviders.getInstance().getProviders().add(provider);
        assertEquals(3, SystemProviders.getInstance().getProviders().size());

        Response response = dropwizardExtension.client()
                .target("http://localhost:" +
                        dropwizardExtension.getLocalPort() + "/providers?name=toBeRemovedProvider")
                .request()
                .delete();

        assertNotNull(response);
        assertEquals(200, response.getStatus());
        assertEquals(2,SystemProviders.getInstance().getProviders().size());
    }

    @Test
    public void removeProviderFailed() {

        Response response = dropwizardExtension.client()
                .target("http://localhost:" +
                        dropwizardExtension.getLocalPort() + "/providers?name=notExist")
                .request()
                .delete();

        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals(2,SystemProviders.getInstance().getProviders().size());
    }
}
