package com.studyspace.studyspace;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.studyspace.rest.client.GetData_UW;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String room_num = "1085", build_code = "MC";

        GetData_UW data = new GetData_UW(this,build_code, room_num);
    }
}
