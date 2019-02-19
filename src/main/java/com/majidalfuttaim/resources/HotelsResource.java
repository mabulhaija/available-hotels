package com.majidalfuttaim.resources;

import com.majidalfuttaim.beans.HotelsFilters;
import com.majidalfuttaim.business.HotelsBusiness;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * this is the hotels resource where you can define the hotels end-points
 */

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("hotels")

public class HotelsResource {

    /**
     * this end point returns all available hotels match the filters passed
     * @NotEmpty, will prevent the request from reaching the Endpoint if the field was null or empty
     * @DefaultValue, if field was null or empty, will set default value for that field
     * @Min, minimum value can be set in a number field
     */

    @GET
    @Path("available")
    public Response getAvailableHotels(@NotEmpty @QueryParam("from") String from,
                                       @NotEmpty @QueryParam("to") String to,
                                       @DefaultValue("AUH") @QueryParam("city") String city,
                                       @DefaultValue("1") @Min(1) @QueryParam("adults") Integer numberOfAdults) {

        HotelsFilters filters = new HotelsFilters(from, to, city, numberOfAdults);

        String validationError = HotelsBusiness.validateHotelsFilters(filters);
        if (validationError != null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(validationError).build();
        }

        try {
            return Response.ok(HotelsBusiness.getVendorsHotels(filters)).build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getLocalizedMessage()).build();
        }

    }
}