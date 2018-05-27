package com.studyspace.rest.client;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.studyspace.studyspace.DataReader;
import com.studyspace.studyspace.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class GetData_Course {

    private String building;
    private Context ctx;
    private String uri;
    private RequestParams params;
    private ArrayList<String> used_build;
    private double[] origin;

    public GetData_Course(Context ctx, String uri, String building, RequestParams params, String room, ArrayList<String> used_build,
    double [] origin){
        this.origin = origin;
        this.building = building;
        this.ctx = ctx;
        this.uri = uri;
        this.params = params;
        this.used_build = used_build;
        this.get_course_list(building,room);
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
                double lat = Double.valueOf(buildings.get("latitude").toString()), lng = Double.valueOf(buildings.get("longitude").toString());
                double [] dest = {lat, lng};
                GetData_Distance dist = new GetData_Distance(ctx, origin, dest, building, used_build); //Limit API Key Use for Now
            }catch(Exception e) {
                e.printStackTrace();
            }
        }

    };

    //Response Handler for COURSE api call;
    JsonHttpResponseHandler course_handler = new JsonHttpResponseHandler(){

        @Override
        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse)
        {
            Log.d("API CALL FAILED: ", errorResponse.toString());
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("JSON: ", response.toString());
            try{
                JSONArray classes = response.getJSONArray("data");
                int len = 5;
                if(classes.length() < 5) len = classes.length();
                for(int i = 0; i < 0 ;i++){
                    JSONObject obj = (JSONObject) classes.getJSONObject(i);
                    int course_code = obj.getInt("class_number");
                    String start_time = obj.getString("start_time");
                    String end_time = obj.getString("end_time");
                    String build_code = obj.getString("building");
                    String room = obj.getString("room");
                    Log.i("cc, st, et, bc, rm", course_code + " " + start_time + " " + end_time + " "
                            + build_code + " " + room);
                    MainActivity.db.insertData(building, start_time, end_time, course_code, -1.0, room);
                    get_distances_list(building);
                }
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

    public void get_course_list(String building, String room){
        String endpoint = "/buildings/"+building+"/"+room+"/"+"courses.json";
        RestClientUsage course_api = new RestClientUsage(ctx,uri, endpoint);
        course_api.api_call(params, course_handler);
    }
}
