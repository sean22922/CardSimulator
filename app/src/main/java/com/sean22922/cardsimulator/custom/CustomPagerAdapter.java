package com.sean22922.cardsimulator.custom;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import java.util.List;

public class CustomPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> ml;

    public CustomPagerAdapter(FragmentManager fm){
        super(fm);
    }

    void setList(List<Fragment> l){
        ml=l;
    }
    @Override
    public Fragment getItem(int position) {
        Log.i("CPA",String.valueOf(position));
        return ml.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)   {
        super.destroyItem(container, position, object);
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return ml.get(position);
    }

    @Override
    public int getCount() {
        return ml.size();
    }

}