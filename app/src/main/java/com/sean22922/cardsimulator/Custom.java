package com.sean22922.cardsimulator;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.yanzm.mth.MaterialTabHost;

import java.util.ArrayList;
import java.util.List;


public class Custom extends Fragment {

    private OnFragmentInteractionListener mListener;
    public Custom() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Custom newInstance(int id) {
        Custom fragment = new Custom();
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

        View view=inflater.inflate(R.layout.fragment_custom, container, false);
        final MaterialTabHost tabHost=(MaterialTabHost)view.findViewById(R.id.tabhost);
        tabHost.addTab(getString(R.string.tab_p));
        tabHost.addTab(getString(R.string.tab_r));
        tabHost.setType(MaterialTabHost.Type.FullScreenWidth);
        tabHost.onPageScrolled(0,0,0);
        final List<Fragment> l=new ArrayList<>();
        l.add(new CustomProbabilityFragment());
        l.add(new CustomResultFragment());
        getChildFragmentManager().beginTransaction().add(R.id.fl,l.get(0)).commit();
        tabHost.setOnTabChangeListener(new MaterialTabHost.OnTabChangeListener(){
            @Override
            public void onTabSelected(int position) {
                tabHost.onPageScrolled(position,0,0);
                getChildFragmentManager().beginTransaction().replace(R.id.fl,l.get(position)).commit();
            }
        });
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
