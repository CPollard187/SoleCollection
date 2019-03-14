package com.example.codypollard.shoecollection;

import android.content.Context;
import android.support.annotation.NonNull;
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