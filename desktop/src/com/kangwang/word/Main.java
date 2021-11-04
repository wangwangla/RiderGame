package com.kangwang.word;

import com.badlogic.gdx.math.MathUtils;

import java.util.Date;
import java.util.Random;

/**
 * 得到星星的计算公式   第一页和第二页
 */
public class Main {
    public static void main(String[] args) {
//        System.out.println(MathUtils.random(0));
//        Date d = new Date();
//        System.out.println(d.getTime()+" before");

//        System.out.println(d.getYear() +"===="+d.getMonth()+"===="+d.getDay() +"==="+d.getHours());
//        d.setHours(0);
//        d.setMinutes(0);
//        d.setSeconds(0);
//        System.out.println(d.getTime()+"   end");
//        System.out.println(d.getYear() +"===="+d.getMonth()+"===="+d.getDay() +"==="+d.getHours());
//        String ss = "It’s been a long time! Come back and play with fun!";
//        String[] split = ss.split("!");
//        System.out.println(split[0]+"!      "+split[1]+"!");
//        for (int i = 0; i < 100; i++) {
//            System.out.println(MathUtils.random(6));
//
//        }
        //        int i = calAngle(0, 0, -360,  120);
//        System.out.println(i);
//        for (int i = 0; i < 100; i++) {
//            System.out.println(MathUtils.random(5));
//         }
//        for (int i = 0; i < 100; i++) {
//            secondPage(i);
//        }
//        for (int i = 0; i < 100; i++) {
//            fristPage(i);
//        }

//        int numx = 0;
//        for (int i = 0; i < 1000; i++) {
//            int random = MathUtils.random(0, 100);
//            if(random<=20){
//                numx++;
//            }
//        }
//        System.out.println(numx);
//        int num = 0;
//        for (int i2 = 0; i2 < 50; i2++) {
//            num = 0;
//            for (int i = 0; i < 50; i++) {
//                int i1 = new Random().nextInt(2);
//                if (i1 == 1) {
//                    num++;
//                }
////            System.out.println(i1);
//            }
//            System.out.println(num);
//        }
//        double v = Math.toRadians(90);
//        System.out.println(Math.sin(v));
//
//        int starNum = 1002;
//        int i = starNum / 50;
//        int num =  i * 50;
//        float v = starNum % 50.0F;
//        if (v>=0) {
//            if (v<=2){
//                System.out.println("jilu");
//            }
//        }


        System.out.println(1.0e-5);

//        int num1 = 51;
//        int getNum = 2;
//        float temp = num1/50.0F;
//        int i = Math.round(temp);
//        int target = i*50;
//        if (MathUtils.between(num1-getNum,num1,target)) {
//            System.out.println("记录");
//        }

    }

    public static void secondPage(int x){
        int xx = MathUtils.floor(x/10.0F)*550 + (int)((x % 10.0F)+1) * (x % 10) *5;
    }

    public static void fristPage(int x){
        int v1 = 10;
        if ((x-4)%5==0){
            v1 = 0;
        }
        int value= 0;
        if (x<4)value = 10;
        else value= (MathUtils.floor((x - 4) / 5)*15 + 15) * (MathUtils.floor((x - 4) /5) *3  + 2)+
                (MathUtils.ceil(((x - 4.0F)%5.0F)/2.0F))*(10+MathUtils.floor((x - 4)/5.0F)*30+
                        MathUtils.ceil(((x-4)%5.0F)/2)*10)+v1;
    }

    public static int calAngle(double x1, double y1, double x2, double y2) {
        double x = Math.abs(x1 - x2);
        double y = Math.abs(y1 - y2);
        double z = Math.sqrt(x * x + y * y);
        int angle = Math.round((float) (Math.asin(y / z) / Math.PI * 180));
        if (x2<x1){
            if (y1>y2){
                angle += 180;
            }else {
                angle = 180 - angle;
            }
        }else {
            if (y1>y2){
                angle = 360 - angle;
            }
        }

        return angle;
    }

}
