package com.majidalfuttaim.utils;


import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Utility class contains Utility Methods could be used within the resources
 */
public class HttpUtils {
    private static final OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
    private static OkHttpClient client = new OkHttpClient();

    public static final Gson GSON = new Gson();

    static {
        clientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        clientBuilder.readTimeout(30, TimeUnit.SECONDS);
        clientBuilder.writeTimeout(30, TimeUnit.SECONDS);
        client = clientBuilder.build();
    }

    public static Response httpGetRequest(String url) throws IOException {

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        return client.newCall(request).execute();
    }
}
