package com.wyp.dateFormat;

import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA.
 * User: leizhimin
 * Date: 2007-11-30
 * Time: 11:20:58
 * To change this template use File | Settings | File Templates.
 */
public class TestSimpleDateFormat {
    public static void main(String args[]) throws ParseException {
        TestSimpleDateFormat test = new TestSimpleDateFormat();
        test.testDateFormat();

    }

    public void testDateFormat() throws ParseException {
        //创建日期
        Date date = new Date();

        //创建不同的日期格式
        DateFormat df1 = DateFormat.getInstance();
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss EE");
        DateFormat df3 = DateFormat.getDateInstance(DateFormat.FULL, Locale.CHINA);     //产生一个指定国家指定长度的日期格式，长度不同，显示的日期完整性也不同
        DateFormat df4 = new SimpleDateFormat("yyyy年MM月dd日 hh时mm分ss秒 EE", Locale.CHINA);
        DateFormat df5 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss EEEEEE", Locale.US);
        DateFormat df6 = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat df7 = new SimpleDateFormat("yyyy年MM月dd日");

        //将日期按照不同格式进行输出
        System.out.println("-------将日期按照不同格式进行输出------");
        System.out.println("按照Java默认的日期格式，默认的区域                      : " + df1.format(date));
        System.out.println("按照指定格式 yyyy-MM-dd hh:mm:ss EE ，系统默认区域      :" + df2.format(date));
        System.out.println("按照日期的FULL模式，区域设置为中文                      : " + df3.format(date));
        System.out.println("按照指定格式 yyyy年MM月dd日 hh时mm分ss秒 EE ，区域为中文 : " + df4.format(date));
        System.out.println("按照指定格式 yyyy-MM-dd hh:mm:ss EE ，区域为美国        : " + df5.format(date));
        System.out.println("按照指定格式 yyyy-MM-dd ，系统默认区域                  : " + df6.format(date));

        //将符合该格式的字符串转换为日期，若格式不相配，则会出错
        Date date1 = df1.parse("07-11-30 下午2:32");
        Date date2 = df2.parse("2007-11-30 02:51:07 星期五");
        Date date3 = df3.parse("2007年11月30日 星期五");
        Date date4 = df4.parse("2007年11月30日 02时51分18秒 星期五");
        Date date5 = df5.parse("2007-11-30 02:51:18 Friday");
        Date date6 = df6.parse("2007-11-30");

        System.out.println("-------输出将字符串转换为日期的结果------");
        System.out.println(date1);
        System.out.println(date2);
        System.out.println(date3);
        System.out.println(date4);
        System.out.println(date5);
        System.out.println(date6);
    }
}