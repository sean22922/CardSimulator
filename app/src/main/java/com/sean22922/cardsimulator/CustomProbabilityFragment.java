package com.sean22922.cardsimulator;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CustomProbabilityFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CustomProbabilityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomProbabilityFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private SharedPreferences sp;
    private OnFragmentInteractionListener mListener;
    private ListView lv;
    private Button add,del,save;

    public CustomProbabilityAdapter pa;
    public CustomProbabilityFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static CustomProbabilityFragment newInstance(String param1, String param2) {
        CustomProbabilityFragment fragment = new CustomProbabilityFragment();
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


    private void load(){
        Set<String> s=sp.getStringSet("list",new HashSet<String>());
        if(s.size()!=0){
            for(String ss:s){
                pa.push(new CustomItem(ss));
            }
        }
    }
    private void save(ArrayList<CustomItem> l){
        HashSet<String> hs=new HashSet<>();
        for(CustomItem i:l){
            if(i.vaild())
                hs.add(i.toString());
        }
        sp.edit().putStringSet("list",hs).commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_custom_probability, container, false);
        sp=this.getActivity().getSharedPreferences(getActivity().getApplicationContext().getPackageName(),Context.MODE_PRIVATE);
        lv=(ListView)view.findViewById(R.id.list_input);
        add=(Button) view.findViewById(R.id.addbtn);
        del=(Button)view.findViewById(R.id.delbtn);
        save=(Button)view.findViewById(R.id.save_p);
        pa=new CustomProbabilityAdapter(lv.getContext());
        add.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                pa.push(new CustomItem());
            }
        });
        del.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                pa.pop();
            }
        });
        save.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                save(pa.getList());
            }
        });
        lv.setAdapter(pa);
        load();
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
