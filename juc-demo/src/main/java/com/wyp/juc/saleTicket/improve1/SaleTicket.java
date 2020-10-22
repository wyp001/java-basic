package com.wyp.juc.saleTicket.improve1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket {//资源类：火车票
    private int number = 30;
    private Lock lock = new ReentrantLock();

    //去掉了synchronized关键字，使用lock的方式实现加锁
    public void saleTicket() {
        lock.lock();
        try {
            if(number > 0){
                System.out.println(Thread.currentThread().getName()+ "\t卖出第："+ (number --)+ "\t还剩下："+ number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

public class SaleTicket {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 40; i++) {
//                    ticket.saleTicket();
//                }
//            }
//        },"A").start();
        //该写法等价于上面的匿名内部类的写法
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.saleTicket();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.saleTicket();
            }
        },"B").start();

    }


}
