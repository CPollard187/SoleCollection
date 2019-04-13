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
import com.example.codypollard.shoecollection.JavaBeans.Search;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

public class CustomSearchShoeAdapter extends RecyclerView.Adapter<CustomSearchShoeAdapter.CustomViewHolder> {
    private ArrayList<Ebay> ebays;
    private Context context;
    RequestQueue requestQueue;
    String TOKEN = "v^1.1#i^1#r^0#p^3#f^0#I^3#t^H4sIAAAAAAAAAOVYa2wUVRTu9AUNFgnII8THMtAqktmdOzu7szN2V7ft1q70ZbcUbMTmzsyddujszGZmlnY1alNIUSKJYtSkiTxiQmKoAiGEBCSKCQ8boqE8fki0RAmQGBITDAT5oXe2r21VoC0/NnF/7GbOPa/vO+fcvXfo7sKiZ3ure28XE7Nyd3XT3bkEAebQRYUFq+bm5S4tyKEzFIhd3Su683vyrpdZMK4lhEZkJQzdQq6uuKZbQloYJJOmLhjQUi1Bh3FkCbYkxMK1NQLjpoWEadiGZGikK1oZJEXklyGCkuj38jLn82GpPuqzyQiSEvRyiPYzoigxPp4T8bplJVFUt2yo20GSoQFP0SwFQBPNCIxPYIHbD9gW0tWMTEs1dKzipslQOl0hbWtm5HrvVKFlIdPGTshQNFwVqw9HKyN1TWWeDF+hER5iNrST1sSnCkNGrmaoJdG9w1hpbSGWlCRkWaQnNBxholMhPJrMNNJPUy0BH+C8Xk70ssAXUB4OlVWGGYf2vfNwJKpMKWlVAem2aqfuxyhmQ9yAJHvkqQ67iFa6nJ+Xk1BTFRWZQTJSHn5lTSzSSLpiDQ2msVGVkewgBTzrZ2kQCDBkSDIShtYKuJEYw45GGJ4UpMLQZdXhy3LVGXY5wgmjybSADFqwUr1eb4YV20kmU48do49uceo5XMCk3a47JUVxzIEr/Xh/8ke7Ybz+D60fZIXx0ggADgIWsuDf+8GZ9an1RMgpS7ihwePkgkSYouLQ7EB2QoMSoiRMbzKOTFUWvD6cQEBBlOznFYrlFYUSfbKfAgpCNEJ45PnA/6Q1bNtUxaSNxtpj8kIaX5B06BRUqAi20YH0plQCkZM10xvOSE90WUGy3bYTgsfT2dnp7vS6DbPNw9A08KyrrYlJ7SgOyTFd9f7KlJruDglhK0sVbJxAkOzCzYeD621kqDFS1RiJVbc21a+O1I027oTMQpOl/4E0hquDGgxNlVLZBdFryg3QtFMxpGlYMCOQlgMye+A5s+5AdHxY2AlMqG6n49ySEfcYEG9Yjqg1nbXrQZQ8FibJPTz+mCu3iaBs6FpqOsZTsFH1jXiEDDM1nYBjxlOwgZJkJHV7OuFGTKdgoSQ1RdU0Z5eYTsAM86mkqUMtZauSNRZyRo0fTiSi8XjShqKGonL2TEB6wAM8x7AzhpdlqPBhNIW3U42KGRqqMDRn1jWqobGS4jjeD2VZxn9RCJ/BA/zMsNe2qVkGHeB7RcAXYAI+mvbOCFsl2phtdZVEBLkAy1ABhE9SbAACCtKcRLGKSEusV+QZZWb1rNBUvFVk31Gj2rBsJM8MGj4OZxeo9DyOjKMkc4hCkJcplmMgJQL8xfN+/kEhjwvyweTD5T+uFJ6J1/lQTvoDeohDdA9xIJcgaA9dApbTywrz1uTnPbLUUm3kxgdRt6W26fiWaiJ3B0oloGrmFhLq9sEtFzJeIOxaTy8Ze4VQlAfmZLxPoB8fXykAjy4uBjzNAkAzjI8FLfTy8dV8sCj/sdcXvn3+1Wt/XC7b077ki6ddC25vI7rp4jElgijIye8hcpZVN827dEQ/Ud7cfHLbZ9z8tXt3hvf/+eQPCyp2fx89OPj5N1t6362+HrkmXqxIrV1dvrK4v3TW3PLST74+1z/4ZclXZ49Ffiy6fuGnu8wdYs/ygat6h564u2/Hme4rhwa+W3jm97+OUgOnD7ZaHxzduRi8tKhjcEHL+cUDl9ftOzX03v7+Rcc3XelNlPRduvmRNjRUSuzt39pXX9C6edPKPcc3PXXw4u2tZ0r6Krp6j+a0N86O3YIfn0K/xU6cfP/Gu6e3z7t565c3Dz2XXHGu/+ynZYdXacZqsT4RfuJbfX3fkResO+/M375Zqar5OTy048VnavfXrDS33tgQGNhdWvjhG88feOu1tg2/zj529fBw+f4GfzUWd9oRAAA=";

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
        Search search = new Search();
        DatabaseHandler db = new DatabaseHandler(context);
        Search keyword = db.getSearch(search.getId());
        requestQueue = Volley.newRequestQueue(context);
        String url = "https://api.ebay.com/buy/browse/v1/item_summary/search?q="
                + keyword.toString()
                + "&limit=10/Authorizantion=" + TOKEN;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject object = response.getJSONObject("itemSummaries");
                    System.out.println(response);
                    /*
                    * FIIIIIIIIIIIIIXXXXXXXXXXXXXX MEEEEEEEEEEEEEEEEE HEEEEEEEEEEEEEEEEERERERERERERER
                     */
                    String name = object.getString("title");
                    String id = object.getString("itemId");
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

        Ebay ebay = ebays.get(i);
        //FIX ME HERE
//        viewHolder.searchName.setText(ebay.getName());
//        viewHolder.itemId.setText(ebay.getItemId());
        //viewHolder.searchShoe.setText(ebay.getImage());
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



