package com.example.viikko9;

public class TheaterInfo {
    private String name;
    private String IDn;
    public TheaterInfo(String inn, String inid) {
        name = inn;
        IDn = inid;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return IDn;
    }

    @Override
    public String toString() {
        return name;
    }
}
