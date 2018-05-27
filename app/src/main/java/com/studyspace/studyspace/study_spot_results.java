package com.studyspace.studyspace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;


import java.util.ArrayList;


public class study_spot_results extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_spot_results);

    }
    public void viewMore(View view){

        Log.d("Here ", "View More");
        ArrayList<FrontendData> data = MainActivity.db.get_data_list();
        Intent refresh = new Intent(this,study_spot_results_location.class);

        int x = R.id.location_result_one;

        for(FrontendData i : data){
            Log.d("Data: ", i.getBuild()+"-"+i.getRoom());
        }

        Button location_button_one = (Button)findViewById(R.id.location_result_one);
        location_button_one.setText(data.get(0).getBuild()+"-"+data.get(0).getRoom());
        Button location_button_two = (Button)findViewById(R.id.location_result_two);
        location_button_two.setText(data.get(1).getBuild()+"-"+data.get(1).getRoom());
        Button location_button_three = (Button)findViewById(R.id.location_result_three);
        location_button_three.setText(data.get(2).getBuild()+"-"+data.get(2).getRoom());
        Button location_button_four = (Button)findViewById(R.id.location_result_four);
        location_button_four.setText(data.get(3).getBuild()+"-"+data.get(3).getRoom());
        Button location_button_five = (Button)findViewById(R.id.location_result_five);
        location_button_five.setText(data.get(4).getBuild()+"-"+data.get(4).getRoom());
        Button location_button_six = (Button)findViewById(R.id.location_result_six);
        location_button_six.setText(data.get(5).getBuild()+"-"+data.get(5).getRoom());
        Button location_button_seven = (Button)findViewById(R.id.location_result_seven);
        location_button_seven.setText(data.get(6).getBuild()+"-"+data.get(6).getRoom());
        Button location_button_eight = (Button)findViewById(R.id.location_result_eight);
        location_button_eight.setText(data.get(7).getBuild()+"-"+data.get(7).getRoom());
        Button location_button_nine = (Button)findViewById(R.id.location_result_nine);
        location_button_nine.setText(data.get(8).getBuild()+"-"+data.get(8).getRoom());
        Button location_button_ten = (Button)findViewById(R.id.location_result_ten);
        location_button_ten.setText(data.get(9).getBuild()+"-"+data.get(9).getRoom());

    }
    public void location_button_one(View view){
        android.content.Intent location_button_one = new android.content.Intent(this,individual_location_page.class);
        startActivity(location_button_one);

    }
    public void location_button_two(View view){
        android.content.Intent location_button_two = new android.content.Intent(this,individual_location_page.class);
        startActivity(location_button_two);

    }
    public void location_button_three(View view){
        android.content.Intent location_button_three = new android.content.Intent(this,individual_location_page.class);
        startActivity(location_button_three);

    }
    public void location_button_four(View view){
        android.content.Intent location_button_four = new android.content.Intent(this,individual_location_page.class);
        startActivity(location_button_four);

    }
    public void location_button_five(View view){
        android.content.Intent location_button_five= new android.content.Intent(this,individual_location_page.class);
        startActivity(location_button_five);

    }
    public void location_button_six(View view){
        android.content.Intent location_button_six = new android.content.Intent(this,individual_location_page.class);
        startActivity(location_button_six);

    }
    public void location_button_seven(View view){
        android.content.Intent location_button_seven = new android.content.Intent(this,individual_location_page.class);
        startActivity(location_button_seven);

    }
    public void location_button_eight(View view){
        android.content.Intent location_button_eight = new android.content.Intent(this,individual_location_page.class);
        startActivity(location_button_eight);

    }
    public void location_button_nine(View view){
        android.content.Intent location_button_nine = new android.content.Intent(this,individual_location_page.class);
        startActivity(location_button_nine);

    }
    public void location_button_ten(View view){
        android.content.Intent location_button_ten = new android.content.Intent(this,individual_location_page.class);
        startActivity(location_button_ten);
    }

    public void menu_button(View view){
        android.content.Intent menu_button = new android.content.Intent(this,MainActivity.class);
        startActivity(menu_button);
    }

}
