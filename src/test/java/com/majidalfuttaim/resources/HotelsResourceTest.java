package com.majidalfuttaim.resources;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.majidalfuttaim.TestBase;
import com.majidalfuttaim.beans.HotelResponse;
import com.majidalfuttaim.utils.HttpUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HotelsResourceTest extends TestBase {
    private String mockBestHotelResponse = "[{\n" +
            "  \"hotel\":\"h1r2\",\n" +
            "  \"hotelRate\":2,\n" +
            "  \"hotelFare\":100,\n" +
            "  \"roomAmenities\":\"am1,am2\"\n" +
            "}]";

    private String mockCrazyHotelResponse = "[{\n" +
            "  \"hotelName\":\"h2r5\",\n" +
            "  \"rate\":\"*****\",\n" +
            "  \"price\":100,\n" +
            "  \"discount\":10,\n" +
            "  \"amenities\":[\n" +
            "    \"ame1\",\"ame2\"\n" +
            "    ]\n" +
            "},\n" +
            "{\n" +
            "  \"hotelName\":\"h2r1\",\n" +
            "  \"rate\":\"*\",\n" +
            "  \"price\":100,\n" +
            "  \"amenities\":[\n" +
            "    \"ame3\",\"ame4\"\n" +
            "    ]\n" +
            "}\n" +
            "]";

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
            return rateString.length();
        }
    }

    @Test
    public void getAvailableHotels() throws IOException {

        List<HotelResponse> response = getVendorsHotelsTest();
        assertNotNull(response);
        assertEquals(3, response.size());
        assertEquals(5,response.get(0).getRate() );
        assertEquals(2,response.get(1).getRate() );
        assertEquals(1,response.get(2).getRate() );
    }

    private List<HotelResponse> getVendorsHotelsTest() throws IOException {
        List<HotelResponse> hotelsResponses = new ArrayList<>();

        JsonArray bestHotelArr = HttpUtils.GSON.fromJson(mockBestHotelResponse, JsonArray.class);
        for (JsonElement element : bestHotelArr) {
            HotelResponse hotel = new HotelResponse();
            hotel.setProvider("BestHotel");
            if (element.getAsJsonObject().get("hotel") != null) {

                hotel.setHotelName(element.getAsJsonObject().get("hotel").getAsString());
            }
            if (element.getAsJsonObject().get("hotelFare") != null) {
                hotel.setFare(element.getAsJsonObject().get("hotelFare").getAsDouble());
            }
            if (element.getAsJsonObject().get(null) != null) {

                hotel.setDiscount(element.getAsJsonObject().get(null).getAsDouble());
            }

            hotel.setRate(getHotelRate(element.getAsJsonObject().get("hotelRate")));
            JsonElement amenitiesElement = element.getAsJsonObject().get("roomAmenities");
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
            hotelsResponses.add(hotel);
        }

        JsonArray crazyHotelArr = HttpUtils.GSON.fromJson(mockCrazyHotelResponse, JsonArray.class);
        for (JsonElement element : crazyHotelArr) {
            HotelResponse hotel = new HotelResponse();
            hotel.setProvider("CrazyHotel");
            if (element.getAsJsonObject().get("hotelName") != null) {

                hotel.setHotelName(element.getAsJsonObject().get("hotelName").getAsString());
            }
            if (element.getAsJsonObject().get("price") != null) {

                hotel.setFare(element.getAsJsonObject().get("price").getAsDouble());
            }
            if (element.getAsJsonObject().get("discount") != null) {

                hotel.setDiscount(element.getAsJsonObject().get("discount").getAsDouble());
            }
            hotel.setRate(getHotelRate(element.getAsJsonObject().get("rate")));
            JsonElement amenitiesElement = element.getAsJsonObject().get("amenities");
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
            hotelsResponses.add(hotel);
        }

        Collections.sort(hotelsResponses);
        return hotelsResponses;
    }
}
