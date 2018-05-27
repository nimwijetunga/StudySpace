package com.studyspace.rest.client;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.studyspace.studyspace.DataReader;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class GetData_Building {

    private Context ctx;
    private String building;
    private final String uri = "https://api.uwaterloo.ca/v2";
    private RequestParams params;
    private double [] origin;


    public GetData_Building(Context ctx, DataReader reader, double [] origin){
        this.ctx = ctx;
        this.origin = origin;
        this.params = new RequestParams();
        GetData_Course courses;
        this.params.put("key", "225e214a328f5477ca868b204939b37c");//Waterloo API Key
        for (int i=0; i<reader.getBuild().size(); i++){
            building = reader.getBuild().get(i);
            courses = new GetData_Course(ctx, uri,building,params,reader.getRoom().get(i));
        }
        for (String x : reader.getBuild_unique()){
            this.get_distances_list(x);
        }
    }

    //Response Handler for BUILDINGS API
    JsonHttpResponseHandler build_handler = new JsonHttpResponseHandler(){

        @Override
        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse)
        {
            Log.d("API CALL FAILED: ", errorResponse.toString());
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            try{
                JSONObject buildings = response.getJSONObject("data");
                //Log.d("JSON Building: ", buildings.toString());
                double lat = Double.valueOf(buildings.get("latitude").toString()), lng = Double.valueOf(buildings.get("longitude").toString());
                double [] dest = {lat, lng};
                //GetData_Distance dist = new GetData_Distance(ctx, origin, dest, building); //Limit API Key Use for Now
            }catch(Exception e) {
                e.printStackTrace();
            }
        }

    };

    public void get_distances_list(String building){
        String endpoint = "/buildings/"+building+".json";
        RestClientUsage build_api = new RestClientUsage(ctx,uri, endpoint);
        build_api.api_call(params, build_handler);
    }
}
