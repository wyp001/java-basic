package com.wyp.staticProxy;

import com.wyp.common.RealSell;
import com.wyp.common.Sell;

public class ProxyClass2 implements Sell {
    private RealSell realSell;

    public ProxyClass2(RealSell realSell) {
        this.realSell = realSell;
    }

    public void sellRoom() {
        System.out.println("------------卖房前----------");
        realSell.sellRoom();
        System.out.println("------------卖房后----------");
    }

}