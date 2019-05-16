package com.epicodus.jobhunt.service;

import com.epicodus.jobhunt.constants.Constants;

import okhttp3.Callback;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MuseService {
    public static void findJobs(String jobs, String descending,Callback callback){
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.MUSE_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.JOBS_QUERY_PARAMETER, jobs);
        urlBuilder.addQueryParameter(Constants.PAGE_QUERY_PARAMETER, descending);
        String url = urlBuilder.build().toString();
        //request//
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", Constants.MUSE_TOKEN)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
