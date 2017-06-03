package com.sean22922.cardsimulator;

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
}
