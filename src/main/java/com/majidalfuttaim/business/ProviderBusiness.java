package com.majidalfuttaim.business;

import com.majidalfuttaim.beans.HotelsFilters;
import com.majidalfuttaim.beans.Provider;
import com.majidalfuttaim.enums.ProviderDateFormatter;

/**
 * the main class to represent the business methods related to Provider Resource {@link com.majidalfuttaim.resources.ProviderResource},
 * as Business Layer
 */
public class ProviderBusiness {

    /**
     * this method validate Provider Object received by Endpoint
     * @param provider, {@link Provider} object
     * @return boolean, true if is valid, false other wise
     * Since the provider class {@link Provider} has @NotEmpty constraints and so on, no need to check for nulls
     */
    public static boolean isValidProvider(Provider provider){

        if (!ProviderDateFormatter.hasValue(provider.getDateFormatter())) {

            return false;
        }
        return true;
    }
}
