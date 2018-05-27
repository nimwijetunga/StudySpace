package com.studyspace.studyspace;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class select_page_home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_page_home);
    }
    public void Study_spot_results(View view){
        android.content.Intent Study_spot_results = new android.content.Intent(this,study_spot_results_location.class);
        startActivity(Study_spot_results);

    }
    public void viewAll(View view){
        android.content.Intent viewAll = new android.content.Intent(this,study_spot_results_location.class);
        startActivity(viewAll);

    }
}
