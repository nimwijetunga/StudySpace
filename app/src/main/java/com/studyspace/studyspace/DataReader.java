package com.studyspace.studyspace;


import android.content.Context;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class DataReader {

    private Context ctx;
    private ArrayList<String> build, room;
    private Set<String> build_unique;


    public DataReader(Context ctx){
        this.ctx = ctx;
        this.build = new ArrayList<String>();
        this.room = new ArrayList<String>();
        this.build_unique = new HashSet<String>();
        this.readFile();
    }

    public Set<String> getBuild_unique() {
        return build_unique;
    }

    public ArrayList<String> getBuild() {
        return build;
    }

    public ArrayList<String> getRoom() {
        return room;
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
                build_unique.add(parts[0]);
                build.add(parts[0]);
                room.add(parts[1]);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }




}
