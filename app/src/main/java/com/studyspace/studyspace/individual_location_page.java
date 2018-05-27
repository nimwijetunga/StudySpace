package com.studyspace.studyspace;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class individual_location_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_location_page);
    }
    public void individual_location_page(View view){
        android.content.Intent Study_spot_results = new android.content.Intent(this,study_spot_results.class);
        startActivity(Study_spot_results);

    }
}
