package com.sean22922.cardsimulator;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class CC extends Fragment {

    private OnFragmentInteractionListener mListener;
    private EditText times;
    private Button chou,chou1,chou10;
    private ListView result;
    private ProbabilityRandom pr;
    private ArrayAdapter<String> listAdapter;
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
        View view=inflater.inflate(R.layout.standard_draw_layout, container, false);
        times=(EditText) view.findViewById(R.id.times);
        chou=(Button)view.findViewById(R.id.chou);
        chou1=(Button)view.findViewById(R.id.chou1);
        chou10=(Button)view.findViewById(R.id.chou10);
        result=(ListView)view.findViewById(R.id.result);
        chou.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                int t=1;
                try{
                    t=Integer.parseInt(times.getText().toString());
                }catch (Exception  e){
                    Toast.makeText(getView().getContext(),getString(R.string.wrongtimes),Toast.LENGTH_SHORT).show();
                }
                listAdapter=new ArrayAdapter<String>(result.getContext(),R.layout.result_list,pr.randList(t));
                result.setAdapter(listAdapter);
            }
        });
        chou1.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                listAdapter=new ArrayAdapter<String>(result.getContext(),R.layout.result_list,pr.randList(1));
                result.setAdapter(listAdapter);
            }
        });
        chou10.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                listAdapter=new ArrayAdapter<String>(result.getContext(),R.layout.result_list,pr.randList(10));
                result.setAdapter(listAdapter);
            }
        });
        result.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
                Toast.makeText(getView().getContext(),result.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();
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
