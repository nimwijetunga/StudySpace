package com.studyspace.studyspace;

import android.content.Context;
import android.util.Log;

import org.json.*;
import com.loopj.android.http.*;
import cz.msebera.android.httpclient.Header;

public class RestClientUsage {

    private Context context;
    private String endpoint;
    private RestClient client;

    public RestClientUsage(Context context, String uri, String endpoint, RequestParams params){
        this.client = new RestClient(uri);
        this.endpoint = endpoint;
        this.context = context;
        try{
            this.getPublicTimeline(params);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void getPublicTimeline(RequestParams params) throws JSONException {

        this.client.get(this.endpoint, params, new JsonHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable err, JSONObject response){
                Log.d("Got Here Err", err.toString());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                try{
                    Log.d("JSON: ", response.toString());
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                // Pull out the first event on the public timeline
                // Do something with the response
                Log.d("Got", "Here");
                for(int i = 0; i < timeline.length(); i++){
                    try{
                        JSONObject obj = (JSONObject) timeline.get(i);
                        Log.i("JSON " + i, obj.toString());
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}

