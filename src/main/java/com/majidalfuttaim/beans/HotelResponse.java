package com.majidalfuttaim.beans;

import java.util.Arrays;
import java.util.List;

/**
 * this bean represents the final response fields will return
 * when calling hotels/available
 */
public class HotelResponse implements Comparable<HotelResponse> {

    private String provider;
    private String hotelName;
    private double fare;
    private int rate;
    private Double discount;
    private List<String> amenities;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        if (hotelName == null || hotelName.isEmpty()) {
            this.hotelName = "";
        } else {
        this.hotelName = hotelName;
        }
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        if (discount == null || discount <= 0) {
            return;
        }
        this.discount = discount;
    }

    public void setAmenities(String amenitiesString) {
        if (amenitiesString == null || amenitiesString.isEmpty()) {
            return;
        }
        String[] amenitiesArray = amenitiesString.split(",");
        this.setAmenities(Arrays.asList(amenitiesArray));
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public int compareTo(HotelResponse otherHotel) {
        return otherHotel.getRate() - this.getRate();
    }
}
