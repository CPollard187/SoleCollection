package com.example.codypollard.shoecollection;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.codypollard.shoecollection.JavaBeans.Shoe;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

/**
 * Author = Cody Pollard
 * Date = 2019
 */


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
    //Declare Strings and Layouts
    private String name;
    private String brand;
    private String price;
    private String shoeImage;
    private LinearLayout shoeLayout;
    private int i;

    private OnFragmentInteractionListener mListener;

    public FavShoeFragment() {
        // Required empty public constructor
    }

    /**
     * These are all of the aspects that are in the viewpager
     * @return A new instance of fragment FavShoeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavShoeFragment newInstance(String name, String brand, String price, String shoeImage) {
        FavShoeFragment fragment = new FavShoeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, name);
        args.putString(ARG_PARAM2, brand);
        args.putString(ARG_PARAM3, price);
        args.putString(ARG_PARAM4, shoeImage);
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
            shoeImage = getArguments().getString(ARG_PARAM4);
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

        /**
         * Get All Shoes from the DB
         * WE need this so we can populate the viewpager
         */
        ArrayList<Shoe> shoeList = db.getAllShoes();

        /**
         * This Holds the Image of the Shoe from the DB
         */
        shoeLayout = (LinearLayout) view.findViewById(R.id.shoeImage);

        /**
         * Set the string to the corresponding record from the DB / ShoeList Array
         */

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
            priceText.setText("$" + price);
        }
        if(shoeImage != null) {
            //Get the position of the ShoeList
            shoe = shoeList.get(i);
            //Make sure the layout will be seen
                shoeLayout.setVisibility(View.VISIBLE);
                //If the Layout has nothing in it then do this
                if (shoeLayout.getChildCount() == 0) {
                    //Gets the id of that shoes
                    Shoe pics = db.getShoe(shoe.getId());
                    //If there is a record there then do this
                    if (pics != null) {
                        //Declare an imageview to hold the image from the DB
                        ImageView image = new ImageView(getContext());
                        //Get the Picture from the database
                        File pic = new File(shoe.getPicture());
                        //Load the picture into Picasso
                        Picasso.with(getContext()).load(pic)
                                .resize(800, 900)
                                .centerCrop().into(image);
                        //Display it to the screen
                        shoeLayout.addView(image);
                    }
                }
            }
        db.close();
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
