package com.majidalfuttaim.beans;

import com.majidalfuttaim.enums.ProviderDateFormatter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * this class represents the Provider Object that can be added, returned and so on..
 *
 * name, Unique String will not be duplicated
 *
 * url, the Provider URL String with placeholders to be replaced
 *
 * responseFields {@link ProviderResponseFields}, Object contains the main provider API response fields names
 *
 * dateFormatter, String indicates the provider API Date formatter, related to supported formats {@link ProviderDateFormatter}
 *
 * @NotEmpty, will prevent the request from reaching the Endpoint if the field was null or empty
 * @NotNull, will prevent the request from reaching the Endpoint if the field was null
 * @Valid, run object inner constraints
 */
public class Provider {
    @NotEmpty
    private String name;
    @NotEmpty
    private String url;
    @NotNull
    @Valid
    private ProviderResponseFields responseFields;
    @NotEmpty
    private String dateFormatter;

    public Provider() {
    }

    public Provider(String name, String url, ProviderResponseFields fields, ProviderDateFormatter formatter) {
        this.name = name;
        this.url = url;
        this.responseFields = fields;
        this.dateFormatter = formatter.name();
    }

    public Provider(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ProviderResponseFields getResponseFields() {
        return responseFields;
    }

    public void setResponseFields(ProviderResponseFields responseFields) {
        this.responseFields = responseFields;
    }

    public String getDateFormatter() {
        return dateFormatter;
    }

    public void setDateFormatter(String dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Provider provider = (Provider) o;
        return Objects.equals(name, provider.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
