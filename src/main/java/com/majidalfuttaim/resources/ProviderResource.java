package com.majidalfuttaim.resources;

import com.majidalfuttaim.beans.Provider;
import com.majidalfuttaim.business.ProviderBusiness;
import com.majidalfuttaim.business.SystemProviders;
import com.majidalfuttaim.utils.FailedMessages;
import com.majidalfuttaim.utils.SuccessMessages;
import org.hibernate.validator.constraints.NotEmpty;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("providers")
/**
 * this is the Providers resource where you can define the providers end-points
 */
public class ProviderResource {

    /**
     *
     * @return Set of Providers {@link Provider} are used within the system
     */
    @GET
    public Response getProviders() {

        return Response.ok(SystemProviders.getInstance().getProviders()).build();
    }

    /**
     *
     * @param provider, provider Object that would be added to the system
     */
    @POST
    public Response addProvider(Provider provider) {
        if (!ProviderBusiness.isValidProvider(provider)) {
            return Response.status(Response.Status.BAD_REQUEST).entity(FailedMessages.INVALID_PROVIDER).build();
        }
        if (!SystemProviders.getInstance().getProviders().add(provider)) {
            return Response.status(Response.Status.BAD_REQUEST).entity(FailedMessages.PROVIDER_EXIST).build();
        }
        return Response.ok(SuccessMessages.PROVIDER_ADDED_SUCCESSFULLY).build();
    }

    /**
     *
     * @param name, provider name to be deleted
     */
    @DELETE
    public Response deleteProvider(@NotEmpty @QueryParam("name") String name) {

        if (!SystemProviders.getInstance().getProviders().remove(new Provider(name))) {

            return Response.status(Response.Status.BAD_REQUEST).entity(FailedMessages.PROVIDER_NOT_REMOVED).build();
        }
        return Response.ok(SuccessMessages.PROVIDER_DELETED_SUCCESSFULLY).build();
    }
}
