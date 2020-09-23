package com.wyp.staticProxy;

import com.wyp.common.RealRoom;
import com.wyp.common.Room;

public class ProxyClass implements Room {
    private RealRoom realRoom;

    public ProxyClass(RealRoom realRoom) {
        this.realRoom = realRoom;
    }

    public void rent() {
        System.out.println("租房前收取中介费");
        realRoom.rent();
        System.out.println("租房后收取服务费");
    }
}