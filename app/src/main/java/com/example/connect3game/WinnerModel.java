package com.example.connect3game;

public class WinnerModel {

    private String name;


    public WinnerModel(String name) {
        this.name = name;

    }

    public WinnerModel() {
    }

    @Override
    public String toString() {
        return "WinnerModel{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
