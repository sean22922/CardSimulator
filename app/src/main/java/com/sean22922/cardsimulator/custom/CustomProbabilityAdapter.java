package com.sean22922.cardsimulator.custom;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import com.sean22922.cardsimulator.MyInputFilter;
import com.sean22922.cardsimulator.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/8.
 */

public class CustomProbabilityAdapter extends BaseAdapter {
    private LayoutInflater inf;
    private ArrayList<CustomItem> items;
    public CustomProbabilityAdapter(Context ctx){
        inf=LayoutInflater.from(ctx);
        items=new ArrayList<>();
    }

    @Override
    public View getView(final int position, View cv, ViewGroup parent) {
        final EditText item;
        final EditText probability;
        cv=inf.inflate(R.layout.probability_input_item,null);
        CustomItem ci=items.get(position);
        item=(EditText)cv.findViewById(R.id.item_name);
        if(ci.name!=null&&!ci.name.isEmpty())
            item.setText(items.get(position).name);
        probability=(EditText)cv.findViewById(R.id.item_probability);
        probability.setText(String.valueOf((int)(ci.p*100)));
        probability.setFilters(new InputFilter[]{new MyInputFilter(0,100)});

        item.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {}
            @Override
            public void afterTextChanged(Editable s) {
                double p=0;
                try {
                    p=((double)Integer.parseInt(probability.getText().toString()))/100;
                }
                catch (NumberFormatException e){

                }
                items.set(position,new CustomItem(item.getText().toString(),p));
            }
        });
        probability.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {}
            @Override
            public void afterTextChanged(Editable s) {
                double p=0;
                try {
                    p=((double)Integer.parseInt(probability.getText().toString()))/100;
                }
                catch (NumberFormatException e){

                }
                items.set(position,new CustomItem(item.getText().toString(),p));
            }
        });
        return cv;
    }
    public void push(CustomItem i){
        items.add(i);
        notifyDataSetChanged();
    }
    public void pop(){
        if(items.size()>0){
            items.remove(items.size()-1);
            notifyDataSetChanged();
        }
    }
    public ArrayList<CustomItem> getList(){
        return this.items;
    }
    public void updateList(ArrayList<CustomItem> l){
        this.items=l;
    }
    @Override
    public int getCount() {
        return items.size();
    }
    @Override
    public Object getItem(int position) {
        return items.get(position);
    }
    @Override
    public long getItemId(int position) {
        return items.indexOf(getItem(position));
    }
}
