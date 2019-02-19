package com.majidalfuttaim.enums;

import java.time.format.DateTimeFormatter;

/**
 * this enum defines the formatters can be used as Provider dates formatter
 */
public enum ProviderDateFormatter {

    ISO_LOCAL_DATE(DateTimeFormatter.ISO_LOCAL_DATE), ISO_INSTANT(DateTimeFormatter.ISO_INSTANT);

    private DateTimeFormatter dateTimeFormatter;

    ProviderDateFormatter(DateTimeFormatter formatter) {
        this.dateTimeFormatter = formatter;
    }

    public DateTimeFormatter getDateTimeFormatter() {
        return dateTimeFormatter;
    }

    public static boolean hasValue(String name) {
        for (ProviderDateFormatter formatter : ProviderDateFormatter.values()) {
            if (formatter.name().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
