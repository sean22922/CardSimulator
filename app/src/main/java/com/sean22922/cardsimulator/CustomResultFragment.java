package com.sean22922.cardsimulator;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CustomResultFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CustomResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomResultFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private EditText times;
    private Button chou,chou1,chou10;
    private ListView lv;
    private ListAdapter la;

    public ProbabilityRandom pr=null;

    public CustomResultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CustomResultFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CustomResultFragment newInstance(String param1, String param2) {
        CustomResultFragment fragment = new CustomResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_custom_result, container, false);
        lv = (ListView) view.findViewById(R.id.result);
        times=(EditText) view.findViewById(R.id.times);
        chou=(Button)view.findViewById(R.id.chou);
        chou1=(Button)view.findViewById(R.id.chou1);
        chou10=(Button)view.findViewById(R.id.chou10);
        chou.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i("CSCSCSC",String.valueOf(pr.getTotal()));
                if(pr.getTotal()!=1.0){
                    Toast.makeText(getActivity().getApplicationContext(),getString(R.string.wrongtimes),Toast.LENGTH_SHORT);
                    return;
                }
                int t=1;
                try{
                    t=Integer.parseInt(times.getText().toString());
                }catch (Exception  e){
                    Toast.makeText(getActivity().getApplicationContext(),getString(R.string.wrongtimes),Toast.LENGTH_SHORT);
                }
                la=new ArrayAdapter<String>(lv.getContext(),R.layout.result_list,pr.randList(t));
                lv.setAdapter(la);
            }
        });
        chou1.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i("CSCSCSC",String.valueOf(pr.getTotal()));
                if(pr.getTotal()!=1.0){
                    Toast.makeText(getActivity().getApplicationContext(),getString(R.string.wrongtimes),Toast.LENGTH_SHORT);
                    return;
                }
                la=new ArrayAdapter<String>(lv.getContext(),R.layout.result_list,pr.randList(1));
                lv.setAdapter(la);
            }
        });
        chou10.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i("CSCSCSC",String.valueOf(pr.getTotal()));
                if(pr.getTotal()!=1.0){
                    Toast.makeText(getActivity().getApplicationContext(),getString(R.string.wrongtimes),Toast.LENGTH_SHORT);
                    return;
                }
                la=new ArrayAdapter<String>(lv.getContext(),R.layout.result_list,pr.randList(10));
                lv.setAdapter(la);
            }
        });
        if(pr==null)
            pr=new ProbabilityRandom();
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
