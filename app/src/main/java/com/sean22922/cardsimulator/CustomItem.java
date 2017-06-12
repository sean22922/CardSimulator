package com.sean22922.cardsimulator;

/**
 * Created by Administrator on 2017/6/8.
 */

public class CustomItem {
    String name;
    double p;
    CustomItem(){

    }
    CustomItem(String s,double p){
        this.name=s;
        this.p=p;
    }
    CustomItem(String s){
        String ar[]=s.split(" ");
        name=ar[0];
        p=Double.parseDouble(ar[1]);
    }
    boolean vaild(){
        return p>0&&(!name.isEmpty());
    }
    @Override
    public String toString(){
        return name+" "+p;
    }
}
