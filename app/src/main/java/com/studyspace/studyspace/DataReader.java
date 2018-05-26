package com.studyspace.studyspace;


import android.content.Context;
import android.util.Log;

import java.io.InputStream;
import java.util.ArrayList;


public class DataReader {

    private Context ctx;
    private ArrayList<String> build, room;

    public DataReader(Context ctx){
        this.ctx = ctx;
        this.build = new ArrayList<String>();
        this.room = new ArrayList<String>();
    }

    public void readFile() {
        String text = "";
        try{
            InputStream is = ctx.getAssets().open("data.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            text = new String(buffer);
            String lines[] = text.split("\\r?\\n");
            for(int i = 0; i < lines.length; i++){
                String line = lines[i];
                String[] parts = line.split(" ");
                build.add(parts[0]);
                room.add(parts[1]);
            }
            for(int i = 0; i < build.size(); i++){
                Log.d("Text: " + i, build.get(i) + " " + room.get(i));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }




}
