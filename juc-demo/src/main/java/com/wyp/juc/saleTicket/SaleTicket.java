package com.wyp.juc.saleTicket;

class Ticket {//资源类：火车票
    private int number = 30;

    public synchronized void saleTicket() {
        if(number > 0){
            System.out.println(Thread.currentThread().getName()+ "\t卖出第："+ (number --)+ "\t还剩下："+ number);
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
