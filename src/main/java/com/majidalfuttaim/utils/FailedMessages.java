package com.majidalfuttaim.utils;

/**
 * Utility class contains all failed messages may be returned
 */
public class FailedMessages {
    public static final String INVALID_PROVIDER = "couldn't add provider, please check the data sent";

    public static final String PROVIDER_EXIST = "Provider name must be unique";

    public static final String PROVIDER_NOT_REMOVED = "Provider did not remove, may its not exist";

    public static final String DATE_FORMAT_ERROR = "please check from/to dates formats";
    public static final String DATE_PERIOD_ERROR = "please check selected dates period";
    public static final String DATE_FROM_ERROR = "Date from can not be old date";
    public static final String INVALID_IATA_CODE = "IATA code must be 3 characters";
    public static final String INVALID_ADULTS_NUMBER = "please select adults number, and must be greater than 0";
}
