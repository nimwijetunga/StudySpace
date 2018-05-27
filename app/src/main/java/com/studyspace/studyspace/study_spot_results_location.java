package com.studyspace.studyspace;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class study_spot_results_location extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_spot_results_location);
    }

    public void menu_icon(View view){
        android.content.Intent menu_icon = new android.content.Intent(this,MainActivity.class);
        startActivity(menu_icon);
    }

}
