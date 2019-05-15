package com.epicodus.jobhunt;

import javax.security.auth.callback.Callback;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

public class MuseService {
    public static void findJobs(String jobs, Callback callback){
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.MUSE_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.JOBS_QUERY_PARAMETER, jobs);
        String url = urlBuilder.build().toString();
    }
}
