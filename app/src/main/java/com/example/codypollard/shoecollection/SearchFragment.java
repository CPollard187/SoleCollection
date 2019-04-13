package com.example.codypollard.shoecollection;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.codypollard.shoecollection.JavaBeans.Ebay;
import com.example.codypollard.shoecollection.JavaBeans.Search;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.codypollard.shoecollection.MainActivity.fab;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
     String name;
     String id;
    String imageUrl;
    CustomSearchShoeAdapter adapter;

    ArrayList<Ebay> ebayList = new ArrayList<>();
    RequestQueue requestQueue;
    String TOKEN = "v^1.1#i^1#r^0#p^3#f^0#I^3#t^H4sIAAAAAAAAAOVYa2wUVRTu9AUNFgnII8THMtAqktmdOzu7szN2V7ft1q70ZbcUbMTmzsyddujszGZmlnY1alNIUSKJYtSkiTxiQmKoAiGEBCSKCQ8boqE8fki0RAmQGBITDAT5oXe2r21VoC0/NnF/7GbOPa/vO+fcvXfo7sKiZ3ure28XE7Nyd3XT3bkEAebQRYUFq+bm5S4tyKEzFIhd3Su683vyrpdZMK4lhEZkJQzdQq6uuKZbQloYJJOmLhjQUi1Bh3FkCbYkxMK1NQLjpoWEadiGZGikK1oZJEXklyGCkuj38jLn82GpPuqzyQiSEvRyiPYzoigxPp4T8bplJVFUt2yo20GSoQFP0SwFQBPNCIxPYIHbD9gW0tWMTEs1dKzipslQOl0hbWtm5HrvVKFlIdPGTshQNFwVqw9HKyN1TWWeDF+hER5iNrST1sSnCkNGrmaoJdG9w1hpbSGWlCRkWaQnNBxholMhPJrMNNJPUy0BH+C8Xk70ssAXUB4OlVWGGYf2vfNwJKpMKWlVAem2aqfuxyhmQ9yAJHvkqQ67iFa6nJ+Xk1BTFRWZQTJSHn5lTSzSSLpiDQ2msVGVkewgBTzrZ2kQCDBkSDIShtYKuJEYw45GGJ4UpMLQZdXhy3LVGXY5wgmjybSADFqwUr1eb4YV20kmU48do49uceo5XMCk3a47JUVxzIEr/Xh/8ke7Ybz+D60fZIXx0ggADgIWsuDf+8GZ9an1RMgpS7ihwePkgkSYouLQ7EB2QoMSoiRMbzKOTFUWvD6cQEBBlOznFYrlFYUSfbKfAgpCNEJ45PnA/6Q1bNtUxaSNxtpj8kIaX5B06BRUqAi20YH0plQCkZM10xvOSE90WUGy3bYTgsfT2dnp7vS6DbPNw9A08KyrrYlJ7SgOyTFd9f7KlJruDglhK0sVbJxAkOzCzYeD621kqDFS1RiJVbc21a+O1I027oTMQpOl/4E0hquDGgxNlVLZBdFryg3QtFMxpGlYMCOQlgMye+A5s+5AdHxY2AlMqG6n49ySEfcYEG9Yjqg1nbXrQZQ8FibJPTz+mCu3iaBs6FpqOsZTsFH1jXiEDDM1nYBjxlOwgZJkJHV7OuFGTKdgoSQ1RdU0Z5eYTsAM86mkqUMtZauSNRZyRo0fTiSi8XjShqKGonL2TEB6wAM8x7AzhpdlqPBhNIW3U42KGRqqMDRn1jWqobGS4jjeD2VZxn9RCJ/BA/zMsNe2qVkGHeB7RcAXYAI+mvbOCFsl2phtdZVEBLkAy1ABhE9SbAACCtKcRLGKSEusV+QZZWb1rNBUvFVk31Gj2rBsJM8MGj4OZxeo9DyOjKMkc4hCkJcplmMgJQL8xfN+/kEhjwvyweTD5T+uFJ6J1/lQTvoDeohDdA9xIJcgaA9dApbTywrz1uTnPbLUUm3kxgdRt6W26fiWaiJ3B0oloGrmFhLq9sEtFzJeIOxaTy8Ze4VQlAfmZLxPoB8fXykAjy4uBjzNAkAzjI8FLfTy8dV8sCj/sdcXvn3+1Wt/XC7b077ki6ddC25vI7rp4jElgijIye8hcpZVN827dEQ/Ud7cfHLbZ9z8tXt3hvf/+eQPCyp2fx89OPj5N1t6362+HrkmXqxIrV1dvrK4v3TW3PLST74+1z/4ZclXZ49Ffiy6fuGnu8wdYs/ygat6h564u2/Hme4rhwa+W3jm97+OUgOnD7ZaHxzduRi8tKhjcEHL+cUDl9ftOzX03v7+Rcc3XelNlPRduvmRNjRUSuzt39pXX9C6edPKPcc3PXXw4u2tZ0r6Krp6j+a0N86O3YIfn0K/xU6cfP/Gu6e3z7t565c3Dz2XXHGu/+ynZYdXacZqsT4RfuJbfX3fkResO+/M375Zqar5OTy048VnavfXrDS33tgQGNhdWvjhG88feOu1tg2/zj529fBw+f4GfzUWd9oRAAA=";

    private OnFragmentInteractionListener mListener;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        fab.hide();

        Search search = new Search();
        DatabaseHandler db = new DatabaseHandler(getContext());
        Search keyword = db.getSearch(search.getId());

        requestQueue = Volley.newRequestQueue(getContext());
        String url = "https://api.ebay.com/buy/browse/v1/item_summary/search?q="
                + keyword.toString()
                + "&limit=10/Authorizantion=" + TOKEN;
        System.out.println(url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        //final Ebay ebay = new Ebay();
                        JSONObject object;
                        try
                        {
                            object = new JSONObject(response);

                            JSONArray jsonArray = object.getJSONArray("itemSummaries");

                            for (int i = 0; i < jsonArray.length(); i++){

                                JSONObject ebay = jsonArray.getJSONObject(i);

                                ebayList.add(new Ebay(
                                        ebay.getString("title"),
                                        ebay.getString("itemId"),
                                        ebay.getString("imageUrl")
                                ));
                            }
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getLocalizedMessage());
            }
        });



        //DatabaseHandler db = new DatabaseHandler(getContext());
        RecyclerView list = view.findViewById(R.id.searchList);
        ArrayList<Ebay> ebayList = db.getAllEbays();
        db.close();
        CustomSearchShoeAdapter adapter = new CustomSearchShoeAdapter(ebayList, getContext());
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(getContext()));

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
