package com.studyspace.rest.client;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class GetData_UW {

    private Context ctx;
    private String building, room;
    private final String uri = "https://api.uwaterloo.ca/v2";
    private RequestParams params;
    private double [] origin;


    public GetData_UW(Context ctx, String building, String room, double [] origin){
        this.ctx = ctx;
        this.origin = origin;
        this.building = building;
        this.room = room;
        this.params = new RequestParams();
        this.params.put("key", "225e214a328f5477ca868b204939b37c");//Waterloo API Key
        this.get_distances_list();
        this.get_course_list();
    }

    //Response Handler for COURSE api call;
    JsonHttpResponseHandler course_handler = new JsonHttpResponseHandler(){

        @Override
        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse)
        {
            Log.d("API CALL FAILED: ", errorResponse.toString());
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            try{
                JSONArray classes = response.getJSONArray("data");
                Log.d("JSON Classes: ", classes.toString());
            }catch(Exception e) {
                e.printStackTrace();
            }
        }

    };

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
                Log.d("JSON Building: ", buildings.toString());
                double lat = Double.valueOf(buildings.get("latitude").toString()), lng = Double.valueOf(buildings.get("longitude").toString());
                double [] dest = {lat, lng};
                GetData_Distance dist = new GetData_Distance(ctx, origin, dest, building);
            }catch(Exception e) {
                e.printStackTrace();
            }
        }

    };

    public void get_distances_list(){
        String endpoint = "/buildings/"+building+".json";
        RestClientUsage build_api = new RestClientUsage(ctx,uri, endpoint);
        build_api.api_call(params, build_handler);
    }


    public void get_course_list(){
        String endpoint = "/buildings/"+building+"/"+room+"/"+"courses.json";
        RestClientUsage course_api = new RestClientUsage(ctx,uri, endpoint);
        course_api.api_call(params, course_handler);

    }

    //First Get Course Data



}
