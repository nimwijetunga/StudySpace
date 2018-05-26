package com.studyspace.studyspace;
import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;

public class RestClient {

    //private static final String BASE_URL = "https://cobalt.qas.im/api/1.0";
    private static final String BASE_URL = "https://api.uwaterloo.ca/v2";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void get(String url, Header header, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        addHeader(header.getName(), header.getValue());
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void 	addHeader(java.lang.String header, java.lang.String value){
        client.addHeader(header, value);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

}
