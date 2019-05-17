package com.epicodus.jobhunt.service;

import com.epicodus.jobhunt.constants.Constants;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class AcobotService {

//    public static void findRestaurants(String message, String uid,String bid,Callback callback) {
//        OkHttpClient client = new OkHttpClient.Builder()
//                .build();
//
//        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.ACABOT_BASE_URL).newBuilder();
//        urlBuilder.addQueryParameter(Constants.CHAT_QUERY_PARAMETER, message);
//        urlBuilder.addQueryParameter(Constants.BID_QUERY_PARAMETER, bid);
//        urlBuilder.addQueryParameter(Constants.UI_ID, uid);
//        String url = urlBuilder.build().toString();
//
//        //request//
//        Request request = new Request.Builder()
//                .url(url)
//                .header("X-RapidAPI-Key", Constants.ACABOT_TOKEN)
//                .build();
//        Call call = client.newCall(request);
//        call.enqueue(callback);
//    }
}