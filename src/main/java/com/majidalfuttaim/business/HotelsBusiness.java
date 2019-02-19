package com.majidalfuttaim.business;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.majidalfuttaim.beans.HotelResponse;
import com.majidalfuttaim.beans.HotelsFilters;
import com.majidalfuttaim.beans.Provider;
import com.majidalfuttaim.enums.ProviderDateFormatter;
import com.majidalfuttaim.utils.FailedMessages;
import com.majidalfuttaim.utils.HttpUtils;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * the main class to represent the business methods related to Hotels Resource {@link com.majidalfuttaim.resources.HotelsResource},
 * as Business Layer
 */
public class HotelsBusiness {

    private final static Logger LOGGER = LoggerFactory.getLogger(HotelsBusiness.class);

    /**
     * this method validate all filters received by Endpoint
     *
     * @param filters, {@link HotelsFilters} object contains all filters
     * @return String represent the error message, or null in case every thing is ok
     */
    public static String validateHotelsFilters(HotelsFilters filters) {
        LocalDate fromDate;
        LocalDate toDate;
        LocalDate currentDate = LocalDate.now();
        try {
            fromDate = LocalDate.parse(filters.getFromDate(), DateTimeFormatter.ISO_LOCAL_DATE);
            toDate = LocalDate.parse(filters.getToDate(), DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            return FailedMessages.DATE_FORMAT_ERROR;
        }
        if (fromDate.isBefore(currentDate)) {
            return FailedMessages.DATE_FROM_ERROR;
        }
        if (toDate.isBefore(fromDate)) {
            return FailedMessages.DATE_PERIOD_ERROR;
        }
        if (filters.getCity().length() != 3) {
            return FailedMessages.INVALID_IATA_CODE;
        }
        if (filters.getNumberOfAdults() == null || filters.getNumberOfAdults() < 1) {
            return FailedMessages.INVALID_ADULTS_NUMBER;
        }
        return null;
    }

    /**
     * this method loops over all providers and calling their APIs with the main filters
     * after that collects all response in list of {@link HotelResponse} ordered by rate
     *
     * @param filters, main filters for providers APIs
     * @return List of {@link HotelResponse} oederd by rate
     * @throws IOException
     */
    public static List<HotelResponse> getVendorsHotels(HotelsFilters filters) throws IOException {
        List<HotelResponse> hotelsResponses = new ArrayList<>();
        LocalDate fromDate = LocalDate.parse(filters.getFromDate(), DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate toDate = LocalDate.parse(filters.getToDate(), DateTimeFormatter.ISO_LOCAL_DATE);

        for (Provider provider : SystemProviders.getInstance().getProviders()) {
            DateTimeFormatter providerFormatter = ProviderDateFormatter.valueOf(provider.getDateFormatter()).getDateTimeFormatter();
            Response getResponse = HttpUtils.httpGetRequest(String.format(provider.getUrl(), filters.getCity(), fromDate.format(providerFormatter), toDate.format(providerFormatter), filters.getNumberOfAdults()));
            if (getResponse == null || !getResponse.isSuccessful()) {
                LOGGER.error(getResponse.message());
                continue;
            }

            JsonArray responseArray = HttpUtils.GSON.fromJson(getResponse.body().string(), JsonArray.class);
            for (JsonElement element : responseArray) {
                HotelResponse hotel = getHotelResponse(element, provider);
                hotelsResponses.add(hotel);
            }
        }
        Collections.sort(hotelsResponses);
        return hotelsResponses;
    }

    private static HotelResponse getHotelResponse(JsonElement element, Provider provider) {
        HotelResponse hotel = new HotelResponse();
        hotel.setProvider(provider.getName());
        JsonObject asJsonObject = element.getAsJsonObject();
        if (asJsonObject.get(provider.getResponseFields().getHotelNameField()) != null) {
            hotel.setHotelName(asJsonObject.get(provider.getResponseFields().getHotelNameField()).getAsString());
        }
        if (asJsonObject.get(provider.getResponseFields().getHotelFareField()) != null) {
            hotel.setFare(asJsonObject.get(provider.getResponseFields().getHotelFareField()).getAsDouble());
        }
        if (asJsonObject.get(provider.getResponseFields().getHotelDiscountField()) != null) {
            hotel.setDiscount(asJsonObject.get(provider.getResponseFields().getHotelDiscountField()).getAsDouble());
        }

        hotel.setRate(getHotelRate(asJsonObject.get(provider.getResponseFields().getHotelRateField())));
        JsonElement amenitiesElement = asJsonObject.get(provider.getResponseFields().getHotelAmenitiesField());
        if (amenitiesElement != null) {

            if (amenitiesElement.isJsonArray()) {
                List<String> amenities = new ArrayList<>();
                for (JsonElement amenity : amenitiesElement.getAsJsonArray()) {
                    amenities.add(amenity.getAsString());
                }
                hotel.setAmenities(amenities);
            } else {
                hotel.setAmenities(amenitiesElement.getAsString());
            }
        }
        return hotel;
    }


    private static int getHotelRate(JsonElement rateElement) {
        if (rateElement == null) {
            return 0;
        }
        String rateString = rateElement.getAsString();
        if (rateString == null || rateString.isEmpty()) {
            return 0;
        }

        try {
            return Integer.parseInt(rateString);
        } catch (NumberFormatException e) {
            return rateString.length() > 5 ? 5 : rateString.length();
        }
    }
}
