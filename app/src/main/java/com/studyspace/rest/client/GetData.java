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

public class GetData {

    private Context ctx;
    private String building;
    private final String uri = "https://api.uwaterloo.ca/v2";
    private RequestParams params;
    private double [] origin;
    private ArrayList<String> used_loc = new ArrayList<String>();


    public GetData(Context ctx, DataReader reader, double [] origin){
        this.ctx = ctx;
        this.origin = origin;
        this.params = new RequestParams();
        GetData_Course courses;
        this.params.put("key", "AIzaSyATKmpd42gAXy_I2tw7gXzVVWNkYOXmbkk");//Waterloo API Key
        for (int i=0; i< reader.getBuild().size(); i++){
            building = reader.getBuild().get(i);
            used_loc.add(building);
            courses = new GetData_Course(ctx, uri,building,params,reader.getRoom().get(i), used_loc,
            origin);
        }
    }
}
