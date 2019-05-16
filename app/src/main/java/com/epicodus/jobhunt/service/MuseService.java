package com.epicodus.jobhunt.service;

import com.epicodus.jobhunt.constants.Constants;
import com.epicodus.jobhunt.model.JobModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Callback;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
public ArrayList<JobModel> processResults(Response response){
    ArrayList<JobModel> jobs = new ArrayList<>();
    try{
        String jsonData = response.body().string();
        JSONObject museJSON = new JSONObject(jsonData);
        JSONArray resultsJSON = museJSON.getJSONArray("results");
        if (response.isSuccessful()){
            for (int i = 0; i < resultsJSON.length(); i++){
                JSONObject jobJSON = resultsJSON.getJSONObject(i);
                String contents = jobJSON.getString("contents"); //
                String tags = jobJSON.getString("tags");
                String refs = jobJSON.getString("refs"); //
                String levels = jobJSON.getString("levels");
                String locations = jobJSON.getString("locations");
                String categories = jobJSON.getString("categories");
                String company = jobJSON.getString("company");

                JobModel jobsInstance = new JobModel(company,locations,refs,contents,categories,tags,levels);
                jobs.add(jobsInstance);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    } catch (JSONException e) {
        e.printStackTrace();
    }
    return jobs;
    }
}
