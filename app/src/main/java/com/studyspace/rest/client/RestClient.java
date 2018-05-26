package com.studyspace.rest.client;
import com.loopj.android.http.*;

public class RestClient {

    private String BASE_URL;
    private AsyncHttpClient client;

    public RestClient(String BASE_URL){
        this.BASE_URL = BASE_URL;
        this.client = new AsyncHttpClient();
    }

    //Get Request No Header
    public void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    //Add Header before request
    public void addHeader(java.lang.String header, java.lang.String value){
        client.addHeader(header, value);
    }

    //Post
    public void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    //Get URL for post/get request
    private String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

}
