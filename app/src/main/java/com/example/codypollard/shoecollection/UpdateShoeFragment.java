package com.example.codypollard.shoecollection;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.codypollard.shoecollection.JavaBeans.Shoe;

/**
 * Author = Cody Pollard
 * Date = 2019
 */

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UpdateShoeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UpdateShoeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateShoeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Shoe mParam1;
    FragmentManager fm;

    private OnFragmentInteractionListener mListener;

    public UpdateShoeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment UpdateShoeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdateShoeFragment newInstance(Parcelable param1) {
        UpdateShoeFragment fragment = new UpdateShoeFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getParcelable(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MainActivity.fab.hide();
        View view = inflater.inflate(R.layout.fragment_update_shoe, container, false);

        /**
         * Declare All of the Edit Text
         */
        final EditText brands = view.findViewById(R.id.brandEdit);
        final EditText type = view.findViewById(R.id.typeEdit);
        final EditText name = view.findViewById(R.id.nameEdit);
        final EditText description = view.findViewById(R.id.descriptionEdit);
        final EditText colourway = view.findViewById(R.id.colourwayEdit);
        final EditText condition = view.findViewById(R.id.conditionEdit);
        final EditText retailPrice = view.findViewById(R.id.retailEdit);
        Button updateButton = view.findViewById(R.id.updateButton);

        /**
         *  Get the entries of the Card you clicked on and set the Edit texts
         */

        if(mParam1 != null){
            name.setText(mParam1.getName());
            description.setText(mParam1.getDescription());
            type.setText(mParam1.getType());
            brands.setText(mParam1.getBrand());
            colourway.setText(mParam1.getColourWay());
            condition.setText(mParam1.getCondition());
            retailPrice.setText(mParam1.getRetailPrice());
        }
        /**
         *Sends the user back to the Collection screen with the next data that the user changed
         */

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mParam1.setName(name.getText().toString());
                mParam1.setDescription(description.getText().toString());
                mParam1.setBrand(brands.getText().toString());
                mParam1.setType(type.getText().toString());
                mParam1.setColourWay(colourway.getText().toString());
                mParam1.setCondition(condition.getText().toString());
                mParam1.setRetailPrice(retailPrice.getText().toString());
                DatabaseHandler db = new DatabaseHandler(getContext());
                db.updateShoe(mParam1);
                db.close();
                fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
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
