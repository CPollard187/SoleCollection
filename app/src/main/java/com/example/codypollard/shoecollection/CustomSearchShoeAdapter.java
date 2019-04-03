package com.example.codypollard.shoecollection;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.codypollard.shoecollection.JavaBeans.Shoe;
import com.example.codypollard.shoecollection.JavaBeans.Ebay;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

public class CustomSearchShoeAdapter extends RecyclerView.Adapter<CustomSearchShoeAdapter.CustomViewHolder> {
    private ArrayList<Ebay> ebays;
    private Context context;
    RequestQueue requestQueue;

    public CustomSearchShoeAdapter(@NonNull ArrayList<Ebay> ebays, Context context) {
        this.ebays = ebays;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomSearchShoeAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.search_view, viewGroup, false);


        final CustomViewHolder customViewHolder = new CustomViewHolder(view);
        /**
         * Make the entire CardView Clickable
         */
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //final ImageView share = view.findViewById(R.id.shareButton);
        return customViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder viewHolder, int i) {
        Ebay ebay = ebays.get(i);
        //FIX ME HERE
        //viewHolder.searchName.setText(ebay.getName());
        viewHolder.itemId.setText(ebay.getItemId());
        //viewHolder.searchShoe.setText(ebay.getImage());


        requestQueue = Volley.newRequestQueue(context);
        String url = "https://api.ebay.com/buy/browse/v1/item_summary/search?q=nike&limit=3/oauth2/TOKENHERE";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject object = response.getJSONObject("itemSummaries");
                    System.out.println(response);
                    String name = "title";
                    String id = "itemId";
                    viewHolder.searchName.setText(name);
                    viewHolder.itemId.setText(id);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getLocalizedMessage());
            }
        });
        requestQueue.add(request);
    }




    @Override
    public int getItemCount() {
        return ebays.size();
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView searchName;
        protected ImageView searchShoe;
        protected TextView itemId;


        public CustomViewHolder(View view) {
            super(view);
            this.itemId = view.findViewById(R.id.itemID);
            this.searchName = view.findViewById(R.id.searchName);
            this.searchShoe = view.findViewById(R.id.searchShoe);
        }
    }
}



