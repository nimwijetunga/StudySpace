package com.studyspace.studyspace;

public class FrontendData {

    private String build, room;
    private double dist;

    public FrontendData(String build, String room, double dist){
        this.build = build;
        this.room = room;
        this.dist = dist;
    }

    public double getDist() {
        return dist;
    }

    public String getBuild() {
        return build;
    }

    public String getRoom() {
        return room;
    }
}
