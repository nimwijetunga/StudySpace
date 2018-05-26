package com.studyspace.rest.client;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class GetData_Distance {

    private double [] origin, dest;
    private final String uri = "https://maps.googleapis.com/maps/api/distancematrix";
    private RequestParams params;
    private Context ctx;
    private String building;

    public GetData_Distance(Context ctx, double [] origin,
                            double [] dest, String building){//origin and dest are lat,long coords
        this.building = building;
        this.ctx = ctx;
        this.origin = origin;
        this.dest = dest;
        this.params = new RequestParams();
        this.params.put("key", "AIzaSyATKmpd42gAXy_I2tw7gXzVVWNkYOXmbkk");//Google API Key
        this.get_dist();
    }

    JsonHttpResponseHandler dist_handler = new JsonHttpResponseHandler(){
        @Override
        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse)
        {
            Log.d("API CALL FAILED: ", errorResponse.toString());
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            Log.d("JSON Distance: ", response.toString());
            Log.d("Building: ", building);
        }
    };

    public void get_dist(){
        String endpoint = "/json";
        params.put("origins",origin[0]+","+origin[1]);
        params.put("destinations",dest[0]+","+dest[1]);
        Log.d("Params :", params.toString());
        RestClientUsage dist_matrix = new RestClientUsage(ctx, uri, endpoint);
        dist_matrix.api_call(params, dist_handler);
    }


}
