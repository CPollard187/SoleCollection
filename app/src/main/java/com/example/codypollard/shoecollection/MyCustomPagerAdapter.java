package com.example.codypollard.shoecollection;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.codypollard.shoecollection.JavaBeans.Shoe;

import java.util.ArrayList;

public class MyCustomPagerAdapter extends FragmentPagerAdapter {
    Context context;
    ArrayList<Shoe> shoeList;

//    LayoutInflater layoutInflater;

    public MyCustomPagerAdapter(FragmentManager fm, Context context, ArrayList<Shoe> shoeList) {
        super(fm);
        //System.out.println("Constructor");
        this.context = context;
        this.shoeList = shoeList;
//        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return shoeList.size();
    }


    public Fragment getItem(int position) {

        //return FavShoeFragment.newInstance(brand.getText().toString(), name.getText().toString(), price.getText().toString(), 0);
        //Shoe shoe = new Shoe();
        Shoe shoe = shoeList.get(position);
        return FavShoeFragment.newInstance(
                shoe.getName(),
                shoe.getBrand(),
                shoe.getRetailPrice(),

                shoe.getPicture());
    }


//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return view == ((LinearLayout) object);
//    }
//
//    @Override
//    public Object instantiateItem(ViewGroup container, final int position) {
//
//        View view = layoutInflater.inflate(R.layout.fragment_fav_shoe, container, false);
//        ImageView imageView = (ImageView) view.findViewById(R.id.shoeImage);
//        final TextView name = view.findViewById(R.id.nameText);
//        final TextView brand = view.findViewById(R.id.brandText);
//        final TextView price = view.findViewById(R.id.priceText);
//
////        imageView.setImageResource(images[position]);
////        container.addView(view);
//        return view;
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView((LinearLayout) object);
//    }
}