package com.studyspace.rest.client;

import android.content.Context;

import com.loopj.android.http.*;

public class RestClientUsage {

    protected Context context;
    private String endpoint;
    private RestClient client;

    public RestClientUsage(Context context, String uri, String endpoint){
        this.client = new RestClient(uri);
        this.endpoint = endpoint;
        this.context = context;
    }

    public void api_call(RequestParams params, JsonHttpResponseHandler handler){
        this.client.get(endpoint,params,handler);
    }
}

