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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.codypollard.shoecollection.JavaBeans.Shoe;

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
                TextView showContent = view.findViewById(R.id.showContent);
                TextView type = view.findViewById(R.id.type);
                TextView condition = view.findViewById(R.id.condition);
                TextView colourway = view.findViewById(R.id.colourway);
                //Grab the location in the list
                int shoe = customViewHolder.getAdapterPosition();
                //Check if
                if(type.getText()
                        != shoes.get(shoe).getType() && condition.getText()
                        != shoes.get(shoe).getCondition() && colourway.getText()
                        != shoes.get(shoe).getColourWay()){
                    //if it does not then set it to match
                    type.setText(shoes.get(shoe).getType());
                    colourway.setText(shoes.get(shoe).getColourWay());
                    condition.setText(shoes.get(shoe).getCondition());
                    //swap the showContent text
                    showContent.setText("Click for less");
                    //swap the chevron
                    customViewHolder.chevron.setImageResource(
                            R.drawable.ic_expand_less_black_24dp);
                }
                else{
                    type.setText("");
                    condition.setText("");
                    colourway.setText("");
                    showContent.setText("Click for more");
                    customViewHolder.chevron.setImageResource(
                            R.drawable.ic_expand_more_black_24dp);
                }

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

        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewHolder, int i) {
        Shoe shoe = shoes.get(i);
        viewHolder.name.setText(shoe.getName());
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
        //protected TextView retailPrice;
        protected ImageView chevron;
        protected TextView showContent;
        //protected TextView description;

        public CustomViewHolder(View view){
            super(view);
            this.name  = view.findViewById(R.id.name);
            this.type  = view.findViewById(R.id.type);
            this.brand  = view.findViewById(R.id.brand);
            this.colourway  = view.findViewById(R.id.colourway);
            this.condition  = view.findViewById(R.id.condition);
            //this.retailPrice  = view.findViewById(R.id.retailPrice);
            this.showContent = view.findViewById(R.id.showContent);
            this.chevron = view.findViewById(R.id.showChevron);
            //this.description = view.findViewById(R.id.description);

        }
    }

}