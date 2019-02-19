package com.majidalfuttaim.beans;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.ws.rs.DefaultValue;

/**
 * this Bean represents all filters used within the hotels service for search
 *
 * fromDate, date as String
 *
 * toDate, date as String
 *
 * city, String represents IATA 3-chars code
 *
 * numberOfAdults, Integer indicates the number of adult in the room (minimum 1 and by default is 1)
 */
public class HotelsFilters {

    private String fromDate;
    private String toDate;
    private String city;
    private Integer numberOfAdults;

    public HotelsFilters() {
    }

    public HotelsFilters(String fromDate, String toDate, String city, Integer numberOfAdults) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.city = city;
        this.numberOfAdults = numberOfAdults;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(Integer numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }
}
