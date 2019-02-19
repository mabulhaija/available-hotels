package com.majidalfuttaim.beans;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * this class represents the main Provider API Response Fields
 *
 * @NotEmpty, will prevent the request from reaching the Endpoint if the field was null or empty
 */
public class ProviderResponseFields {

    @NotEmpty
    private String hotelNameField;
    @NotEmpty
    private String hotelRateField;
    @NotEmpty
    private String hotelFareField;
    @NotEmpty
    private String hotelAmenitiesField;

    private String hotelDiscountField;

    public ProviderResponseFields() {
    }

    public ProviderResponseFields(String hotelNameField, String hotelRateField, String hotelFareField, String hotelAmenitiesField, String hotelDiscountField) {
        this.hotelNameField = hotelNameField;
        this.hotelRateField = hotelRateField;
        this.hotelFareField = hotelFareField;
        this.hotelAmenitiesField = hotelAmenitiesField;
        this.hotelDiscountField = hotelDiscountField;
    }

    public String getHotelNameField() {
        return hotelNameField;
    }

    public void setHotelNameField(String hotelNameField) {
        this.hotelNameField = hotelNameField;
    }

    public String getHotelRateField() {
        return hotelRateField;
    }

    public void setHotelRateField(String hotelRateField) {
        this.hotelRateField = hotelRateField;
    }

    public String getHotelFareField() {
        return hotelFareField;
    }

    public void setHotelFareField(String hotelFareField) {
        this.hotelFareField = hotelFareField;
    }

    public String getHotelAmenitiesField() {
        return hotelAmenitiesField;
    }

    public void setHotelAmenitiesField(String hotelAmenitiesField) {
        this.hotelAmenitiesField = hotelAmenitiesField;
    }

    public String getHotelDiscountField() {
        return hotelDiscountField;
    }

    public void setHotelDiscountField(String hotelDiscountField) {
        this.hotelDiscountField = hotelDiscountField;
    }
}
