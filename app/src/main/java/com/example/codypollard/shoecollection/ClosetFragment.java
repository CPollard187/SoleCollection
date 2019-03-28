package com.example.codypollard.shoecollection;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.codypollard.shoecollection.JavaBeans.Shoe;

import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ClosetFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ClosetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClosetFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ClosetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClosetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClosetFragment newInstance(String param1, String param2) {
        ClosetFragment fragment = new ClosetFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_closet, container, false);
        MainActivity.fab.hide();
        final ViewPager viewPager = view.findViewById(R.id.shoeViewPager);
        final ShoeViewPagerAdapter adapter = new ShoeViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(adapter);
        return view;
    }

    class ShoeViewPagerAdapter extends FragmentPagerAdapter {

        public ShoeViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }


//        DatabaseHandler db = new DatabaseHandler(getContext());
//        ArrayList<Shoe> shoeList = db.getAllShoes();
//        db.close();
        @Override
        public Fragment getItem(int position) {

            //return FavShoeFragment.newInstance(brand.getText().toString(), name.getText().toString(), price.getText().toString(), 0);
            switch (position) {
                case 0:
                    //name of the item, picture of the item, description of item
                    return FavShoeFragment.newInstance("Zoom 3987",
                            "Nike",
                            "24356",
                            0);
                case 1:
                    return FavShoeFragment.newInstance("Boomz 867",
                            "Nike",
                            "99",
                            0);
                case 2:
                    return FavShoeFragment.newInstance("Moons 2345",
                            "Adidas",
                            "99",
                            0);
                case 3:
                    return FavShoeFragment.newInstance("Toonz 97",
                            "Under Armour",
                            "99",
                            0);
                default:
                    return FavShoeFragment.newInstance("Shoe", "brand", "99", 0);
            }
        }
        //shoeList.size();
        public int getCount() {
            return 4;
        }
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
