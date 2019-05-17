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
                String tags = "";
                String company = jobJSON.getJSONObject("company").getString("name");
                String refs = jobJSON.getJSONObject("refs").getString("landing_page");
                String levels = "";
                String categories="";

                //location//
                String locationn = "";
                JSONArray location = jobJSON.getJSONArray("locations");
                JSONArray cat1 = jobJSON.getJSONArray("categories");
                JSONArray tag1 = jobJSON.getJSONArray("tags");


                for (int a = 0; a < location.length(); a++) {

                    JSONObject l1 = location.getJSONObject(a);

                    String l2 = l1.getString("name");
                    locationn = l2;
                }

                //tags//

                for (int b = 0; b < tag1.length(); b++) {

                    JSONObject rf1 = tag1.getJSONObject(b);

                    String rf2 = rf1.getString("name");
                    tags = rf2;
                }
                //categories///

                for (int c = 0; c < cat1.length(); c++) {

                    JSONObject cat2 = cat1.getJSONObject(c);

                    String cat3 = cat2.getString("name");
                    categories = cat3;
                }




//                for (int y = 0; y < location2.length(); y++){
//                    location.add(location.get(y).toString());
//                }

                JobModel jobsInstance = new JobModel(company,locationn,refs,contents,categories,tags,levels);
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
