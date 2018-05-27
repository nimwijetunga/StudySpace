package com.studyspace.studyspace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class select_page_home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_page_home);
    }
    public void Study_spot_results(View view){
        android.content.Intent Study_spot_results = new android.content.Intent(this,study_spot_results.class);
        startActivity(Study_spot_results);
    }
    public void viewAll(View view){
        android.content.Intent viewAll = new android.content.Intent(this,study_spot_results.class);
        startActivity(viewAll);
    }

    public void menu_dropdown(View view){
        android.content.Intent menu_dropdown = new android.content.Intent(this,MainActivity.class);
        startActivity(menu_dropdown);
    }
}
