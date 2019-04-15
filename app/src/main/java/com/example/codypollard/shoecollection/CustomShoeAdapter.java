package com.example.codypollard.shoecollection;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.codypollard.shoecollection.JavaBeans.Shoe;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import java.io.File;
import java.util.ArrayList;

public class CustomShoeAdapter extends RecyclerView.Adapter<CustomShoeAdapter.CustomViewHolder>{
    private ArrayList<Shoe> shoes;
    private Context context;

    public CustomShoeAdapter(@NonNull ArrayList<Shoe> shoes, Context context){
        this.shoes = shoes;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomShoeAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.shoe_view, viewGroup, false);

        final ImageView deleteButton = view.findViewById(R.id.deleteButton);
        final ImageView updateButton = view.findViewById(R.id.updateButton);
        final CustomViewHolder customViewHolder = new CustomViewHolder(view);
        /**
         * Make the entire CardView Clickable
         */
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView type = view.findViewById(R.id.type);
                TextView condition = view.findViewById(R.id.condition);
                TextView colourway = view.findViewById(R.id.colourway);
                int shoe = customViewHolder.getAdapterPosition();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete")
                        .setMessage("Are you sure you want to delete?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int shoe = customViewHolder.getAdapterPosition();
                                DatabaseHandler db = new DatabaseHandler(context);
                                db.deleteShoe(shoes.get(shoe).getId());
                                shoes.remove(shoe);
                                notifyItemRemoved(shoe);
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) context;
                int shoe = customViewHolder.getAdapterPosition();
                FragmentManager fm = mainActivity.getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.content, UpdateShoeFragment.newInstance(shoes.get(shoe)));
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        final ImageView share = view.findViewById(R.id.shareButton);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TweetComposer.Builder builder = new TweetComposer.Builder(context)
                        .text("Check out my newest Pick Up!");
//                        .image(imageUri);
                builder.show();
            }
        });

        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewHolder, int i) {
        Shoe shoe = shoes.get(i);
        //viewHolder.collectionImage.setVisibility(View.VISIBLE);
        viewHolder.name.setText(shoe.getName());
        viewHolder.brand.setText(shoe.getBrand());
        viewHolder.condition.setText(shoe.getCondition());
        viewHolder.colourway.setText(shoe.getColourWay());
        viewHolder.type.setText(shoe.getType());
        if(viewHolder.collectionImage.getChildCount() == 0) {
            //Grab all the photos that match the ID of the current shoe
            DatabaseHandler db = new DatabaseHandler(context);
            Shoe pics = db.getShoe(shoe.getId());
            System.out.println("Pics =  SHoe Adapter" + pics);
//            for (int j = 0; j < pics.size(); j++) {
            if(pics != null){
                ImageView image = new ImageView(context);
                File pic = new File(pics.getPicture());
                Picasso.with(context).load(pic)
                        .resize(400, 280)
                        .centerCrop().into(image);
                viewHolder.collectionImage.addView(image);
            }
        }
    }

    @Override
    public int getItemCount() {
        return shoes.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView name;
        protected TextView type;
        protected TextView brand;
        protected TextView colourway;
        protected TextView condition;
        protected LinearLayout collectionImage;
        //protected TextView retailPrice;
        //protected TextView description;

        public CustomViewHolder(View view){
            super(view);
            this.name  = view.findViewById(R.id.name);
            this.type  = view.findViewById(R.id.type);
            this.colourway  = view.findViewById(R.id.colourway);
            this.brand  = view.findViewById(R.id.brand);
            this.condition  = view.findViewById(R.id.condition);
            this.collectionImage = view.findViewById(R.id.collectionImage);
            //this.retailPrice  = view.findViewById(R.id.retailPrice);
            //this.description = view.findViewById(R.id.description);

        }
    }

}