package com.studyspace.studyspace;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.loopj.android.http.RequestParams;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestParams params = new RequestParams();
        params.put("key", "225e214a328f5477ca868b204939b37c");
        RestClientUsage api = new RestClientUsage(this, "https://api.uwaterloo.ca/v2", "/buildings/list.json",
               params);
    }
}
