package com.wyp.common;


public class RealRoom implements Room {
    private String roomname;

    public RealRoom(String roomname) {
        this.roomname = roomname;
    }

    public void rent() {
        System.out.println("租了"+roomname);
    }

}