package com.example.sivani.squad_up;

public class Contact {

    public static final String NAME = "name";
    public static final String NUMBER = "number";
    public static final String CODE = "code";
    public static final long TIME = 0;

    private String name;
    private String number;
    private String code;
    private long time;

    public Contact() {}

    public Contact(String name, String number, String code, long time) {
        this.name = name;
        this.number = number;
        this.code = code;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

}