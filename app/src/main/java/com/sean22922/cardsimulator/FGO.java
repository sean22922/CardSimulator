package com.sean22922.cardsimulator;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;


public class FGO extends Fragment {

    private OnFragmentInteractionListener mListener;
    private EditText times;
    private Button chou,chou1,chou10;
    private TextView result;
    private ProbabilityRandom pr;
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
        chou1=(Button)view.findViewById(R.id.chou1);
        chou10=(Button)view.findViewById(R.id.chou10);
        result=(TextView)view.findViewById(R.id.result);
        result.setMovementMethod(new ScrollingMovementMethod());
        chou.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                int t=1;
                try{
                    t=Integer.parseInt(times.getText().toString());
                }catch (NumberFormatException  e){
                    result.setText(getString(R.string.wrongtimes));
                }
                result.setText(pr.rand(t,getString(R.string.template_times),getString(R.string.linebreak)));
            }
        });
        chou1.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                result.setText(pr.rand(1,getString(R.string.template_times),getString(R.string.linebreak)));
            }
        });
        chou10.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                result.setText(pr.rand(10,getString(R.string.template_times),getString(R.string.linebreak)));
            }
        });

        pr=new ProbabilityRandom();
        pr.add(getString(R.string.hero5),0.01);
        pr.add(getString(R.string.hero4),0.03);
        pr.add(getString(R.string.hero3),0.4);
        pr.add(getString(R.string.cloth5),0.04);
        pr.add(getString(R.string.cloth4),0.12);
        pr.add(getString(R.string.cloth3),0.4);

        return view;
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
