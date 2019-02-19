package com.majidalfuttaim.business;

import com.majidalfuttaim.beans.Provider;
import com.majidalfuttaim.beans.ProviderResponseFields;
import com.majidalfuttaim.enums.ProviderDateFormatter;

import java.util.HashSet;
import java.util.Set;

/**
 * this is a shared Object over the system which holds List of all vendors,
 * you can add, remove providers in easy way using {@link com.majidalfuttaim.resources.ProviderResource} endpoints
 */
public class SystemProviders {
    private static SystemProviders providersInstance =new SystemProviders();

    /**
     *
     * @return Single Instance of this class
     */
    public static synchronized SystemProviders getInstance(){
        return providersInstance;
    }
    private Set<Provider> providers;

    /**
     * the constructor will initiate the Providers List with predefined providers
     * BestHotels and CrazyHotels
     */
    private SystemProviders(){
        this.providers = new HashSet<>();
        ProviderResponseFields bestHotelsFields = new ProviderResponseFields("hotel", "hotelRate", "hotelFare", "roomAmenities", null);
        ProviderResponseFields crazyHotelsFields = new ProviderResponseFields("hotelName", "rate", "price", "amenities", "discount");
        providers.add(new Provider("BestHotels", "http://localhost:8080/BestHotels?city=%s&fromDate=%s&toDate=%s&numberOfAdults=%d",bestHotelsFields,ProviderDateFormatter.ISO_LOCAL_DATE));
        providers.add(new Provider("CrazyHotels", "http://localhost:8080/CrazyHotels?city=%s&from=%s&To=%s&adultsCount=%d", crazyHotelsFields, ProviderDateFormatter.ISO_INSTANT));
    }


    public Set<Provider> getProviders() {
        return providers;
    }

    public void setProviders(Set<Provider> providers) {
        this.providers = providers;
    }
}
