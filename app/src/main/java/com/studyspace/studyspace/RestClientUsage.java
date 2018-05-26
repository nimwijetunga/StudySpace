package com.studyspace.studyspace;

import android.content.Context;
import android.util.Log;

import org.json.*;
import com.loopj.android.http.*;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;

public class RestClientUsage {

    private Context context;

    public RestClientUsage(Context context){
        this.context = context;
        try{
            this.getPublicTimeline();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void getPublicTimeline() throws JSONException {

        RequestParams params = new RequestParams();
        //params.put("key", "w6tdfPABp2hDC5uVBMIf3ZM1AOqql0BU");//NEED TO REST KEY AFTER A WHILE
        params.put("key", "225e214a328f5477ca868b204939b37c");
        Header [] headers = new Header[2];
        JSONObject tst = null;
        headers[0] = new BasicHeader("key", "225e214a328f5477ca868b204939b37c");

        RestClient.get("/buildings/list.json", params, new JsonHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable err, JSONObject response){
                Log.d("Got Here", err.toString());
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

