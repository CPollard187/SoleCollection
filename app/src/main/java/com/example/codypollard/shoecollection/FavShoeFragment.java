package com.example.codypollard.shoecollection;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.codypollard.shoecollection.JavaBeans.Shoe;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FavShoeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FavShoeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavShoeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "name";
    private static final String ARG_PARAM2 = "brand";
    private static final String ARG_PARAM3 = "price";
    private static final String ARG_PARAM4 = "shoeImage";

    // TODO: Rename and change types of parameters
    private String name;
    private String brand;
    private String price;
    private int shoeImage;

    private OnFragmentInteractionListener mListener;

    public FavShoeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FavShoeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavShoeFragment newInstance(String name, String brand, String price, int shoeImage) {
        FavShoeFragment fragment = new FavShoeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, name);
        args.putString(ARG_PARAM2, brand);
        args.putString(ARG_PARAM3, price);
        args.putInt(ARG_PARAM4, shoeImage);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_PARAM1);
            brand = getArguments().getString(ARG_PARAM2);
            price = getArguments().getString(ARG_PARAM3);
            shoeImage = getArguments().getInt(ARG_PARAM4);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fav_shoe, container, false);
        MainActivity.fab.hide();
        Shoe shoe = new Shoe();
        DatabaseHandler db = new DatabaseHandler(getContext());
        ArrayList<Shoe> shoeList = db.getAllShoes();
        db.close();
        if(brand != null){
            TextView brandText = view.findViewById(R.id.brandText);
            brandText.setText(brand);
        }
        if(name != null){
            TextView nameText =
                    view.findViewById(R.id.nameText);
            nameText.setText(name);
        }
        if(price != null){
            TextView priceText =
                    view.findViewById(R.id.priceText);
            priceText.setText(price);
        }
        if(shoeImage != 0){
            ImageView shoeImage = view.findViewById(R.id.shoeImage);
            shoeImage.setImageResource(R.mipmap.ic_launcher);
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
