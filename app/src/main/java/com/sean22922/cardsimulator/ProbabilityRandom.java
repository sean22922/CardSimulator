package com.sean22922.cardsimulator;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/3.
 */

public class ProbabilityRandom{
    private HashMap<String,Double> p=new HashMap<>();
    private double total=0;
    ProbabilityRandom(){

    }
    void add(String s,Double probability){
        p.put(s,probability);
        total+=probability;
        Log.i(s,String.valueOf(probability));
    }
    double getTotal(){
        return total;
    }
    String rand(){
        double r=Math.random(),now=0;
        for(Map.Entry<String,Double> e:p.entrySet()){
            if(r<now+e.getValue()){
                return e.getKey();
            }
            now+=e.getValue();
        }
        return null;
    }

    String rand(int times,String prefixtemplate,String suffix){
        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=times;i++){
            sb.append(String.format(prefixtemplate,i));
            sb.append(this.rand());
            sb.append(suffix);
        }
        return sb.toString();
    }

    ArrayList randList(int times){
        ArrayList ar=new ArrayList();
        for(int i=1;i<=times;i++){
            ar.add(this.rand());
        }
        return ar;
    }
}
