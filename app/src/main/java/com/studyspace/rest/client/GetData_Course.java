package com.studyspace.rest.client;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.studyspace.studyspace.DataReader;
import com.studyspace.studyspace.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class GetData_Course {

    private String building;
    private Context ctx;
    private String uri;
    private RequestParams params;

    public GetData_Course(Context ctx, String uri, String building, RequestParams params, String room){
        this.building = building;
        this.ctx = ctx;
        this.uri = uri;
        this.params = params;
        this.get_course_list(building,room);
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
                int len = 5;
                if(classes.length() < 5) len = classes.length();
                for(int i = 0; i < len ;i++){
                    JSONObject obj = (JSONObject) classes.getJSONObject(i);
                    int course_code = obj.getInt("class_number");
                    String start_time = obj.getString("start_time");
                    String end_time = obj.getString("end_time");
                    String build_code = obj.getString("building");
                    Log.i("cc, st, et, bc", course_code + " " + start_time + " " + end_time + " "
                            + build_code);
                    MainActivity.db.insertData(building, start_time, end_time, course_code, -1.0);
                }
                //Log.d("JSON Classes: ", classes.toString());
            }catch(Exception e) {
                e.printStackTrace();
            }
        }

    };

    public void get_course_list(String building, String room){
        String endpoint = "/buildings/"+building+"/"+room+"/"+"courses.json";
        RestClientUsage course_api = new RestClientUsage(ctx,uri, endpoint);
        course_api.api_call(params, course_handler);
    }
}
