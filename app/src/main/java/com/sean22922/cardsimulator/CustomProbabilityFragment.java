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
import android.widget.Toast;

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
    private ArrayList<CustomItem> ar;
    private ListView lv;
    private Button add,del;
    private CustomProbabilityAdapter pa;

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

    public void onListChangeHandler(ArrayList<CustomItem> l){
        HashSet<String> hs=new HashSet<>();
        CustomResultFragment crf=(CustomResultFragment)getFragmentManager().findFragmentByTag(getString(R.string.tab_r));
        crf.pr=new ProbabilityRandom();
        for(CustomItem i:l){
            crf.pr.add(i.name,i.p);
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
        ar=new ArrayList<CustomItem>();
        pa=new CustomProbabilityAdapter(lv.getContext(),ar);
        add.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                ar.add(new CustomItem());
                pa.updateList(ar);
                lv.setAdapter(pa);
            }
        });
        del.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                if(ar.size()>0){
                    ar.remove(ar.size()-1);
                    pa.updateList(ar);
                    lv.setAdapter(new CustomProbabilityAdapter(lv.getContext(),ar));
                }
                else Toast.makeText(getActivity().getApplicationContext(),getString(R.string.no_item_can_be_remove),Toast.LENGTH_SHORT);
            }
        });
        lv.setAdapter(pa);
        pa.setOnChangeListener(this);
        Set<String> s=sp.getStringSet("list",new HashSet<String>());
        if(s.size()!=0){
            for(String ss:s){
                ar.add(new CustomItem(ss));
            }
            pa.updateList(ar);
        }
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
