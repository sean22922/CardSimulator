package com.sean22922.cardsimulator;

import com.sean22922.cardsimulator.custom.CustomItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Administrator on 2017/6/3.
 */

public class ProbabilityRandom{
    private HashMap<String,Double> p=new HashMap<>();
    private double total=0;
    public ProbabilityRandom(){

    }
    public void add(String s,Double probability){
        p.put(s,probability);
        total+=probability;
    }
    public void add(CustomItem ci){
        p.put(ci.name,ci.p);
        total+=ci.p;
    }
    public double getTotal(){
        return total;
    }
    public String rand(){
        double rmin=0,rmax=getTotal();
        Random rd=new Random();
        double r= rmin+(rmax-rmin)*rd.nextDouble(),now=0;
        for(Map.Entry<String,Double> e:p.entrySet()){
            if(r<now+e.getValue()){
                return e.getKey();
            }
            now+=e.getValue();
        }
        return null;
    }


    public ArrayList randList(int times){
        ArrayList ar=new ArrayList();
        for(int i=1;i<=times;i++){
            ar.add(this.rand());
        }
        return ar;
    }
}
