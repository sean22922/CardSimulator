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


public class CC extends Fragment {

    private OnFragmentInteractionListener mListener;
    private EditText times;
    private Button chou;
    private TextView result;
    private ProbabilityRandom pr;
    public CC() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CC newInstance(int id) {
        CC fragment = new CC();
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
                StringBuilder sb=new StringBuilder();
                for(int i=1;i<=t;i++){
                    sb.append(getString(R.string.tx).replaceFirst("times",String.valueOf(i))+": ");
                    sb.append(pr.rand()+"\n");
                }
                result.setText(sb.toString());
            }
        });

        pr=new ProbabilityRandom();
        pr.add(getString(R.string.cc_ssr),0.07);
        pr.add(getString(R.string.cc_sr),0.2);
        pr.add(getString(R.string.cc_r),0.73);

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
