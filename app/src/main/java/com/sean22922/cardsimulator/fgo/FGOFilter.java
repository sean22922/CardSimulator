package com.sean22922.cardsimulator.fgo;

import android.content.Context;

import com.sean22922.cardsimulator.ProbabilityRandom;
import com.sean22922.cardsimulator.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/21.
 */

public class FGOFilter {
    static ArrayList<String> filter(ArrayList<String> al,Context ctx){
        String h3,h4,h5,c3;
        h3=ctx.getString(R.string.hero3);
        h4=ctx.getString(R.string.hero4);
        h5=ctx.getString(R.string.hero5);
        c3=ctx.getString(R.string.cloth3);
        boolean all3=true;
        for(String i:al){
            if(!i.equals(h3)&&!i.equals(c3)){
                all3=false;
                break;
            }
        }
        if(all3){
            al.remove(al.size()-1);
            ProbabilityRandom pr=new ProbabilityRandom();
            pr.add(ctx.getString(R.string.hero5),0.01);
            pr.add(ctx.getString(R.string.hero4),0.03);
            pr.add(ctx.getString(R.string.cloth5),0.04);
            pr.add(ctx.getString(R.string.cloth4),0.12);
            al.add(pr.rand());
        }

        boolean allcloth=true;
        for(String i:al){
            if(i.equals(h3)||i.equals(h4)||i.equals(h5)){
                allcloth=false;
                break;
            }
        }
        if(allcloth){
            al.remove(al.size()-2);
            ProbabilityRandom pr=new ProbabilityRandom();
            /*I am not sure about these chances*/
            pr.add(ctx.getString(R.string.hero5),0.01);
            pr.add(ctx.getString(R.string.hero4),0.03);
            pr.add(ctx.getString(R.string.hero3),0.4);
            al.add(pr.rand());
        }
        return al;
    }
}
