package com.sean22922.cardsimulator;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;


public class FGO extends Fragment {

    private OnFragmentInteractionListener mListener;
    private EditText times;
    private Button chou;
    private TextView result;
    private enum Prize{
        hero5,hero4,hero3,cloth5,cloth4,cloth3
    }
    private Prize[] seed=new Prize[100];

    public FGO() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FGO newInstance(int id) {
        FGO fragment = new FGO();
        Bundle args = new Bundle();
        args.putInt("id",id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fgo, container, false);
        times=(EditText) view.findViewById(R.id.times);
        chou=(Button)view.findViewById(R.id.chou);
        result=(TextView)view.findViewById(R.id.result);
        chou.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                int t=1;
                try{
                    t=Integer.parseInt(times.getText().toString());
                }catch (NumberFormatException  e){
                    result.setText(getString(R.string.wrongtimes));
                }
                StringBuilder sb=new StringBuilder();
                for(int i=1;i<=t;i++){
                    sb.append(getString(R.string.tx).replaceFirst("times",String.valueOf(i))+": ");
                    sb.append(random()+"\n");
                }
                result.setText(sb.toString());
            }
        });

        //gen seed
        int idx=0;
        seed[idx++]=Prize.hero5;
        for(int i=0;i<3;i++){
            seed[idx++]=Prize.hero4;
        }
        for(int i=0;i<40;i++){
            seed[idx++]=Prize.hero3;
        }
        for(int i=0;i<4;i++){
            seed[idx++]=Prize.cloth5;
        }
        for(int i=0;i<12;i++){
            seed[idx++]=Prize.cloth4;
        }
        for(int i=0;i<40;i++){
            seed[idx++]=Prize.cloth3;
        }

        return view;
    }
    private String random(){
        Random rand=new Random();
        switch(seed[rand.nextInt(100)]){
            case hero5:
                return getString(R.string.hero5);
            case hero4:
                return getString(R.string.hero4);
            case hero3:
                return getString(R.string.hero3);
            case cloth5:
                return getString(R.string.cloth5);
            case cloth4:
                return getString(R.string.cloth4);
            case cloth3:
                return getString(R.string.cloth3);
        }
        return null;
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
