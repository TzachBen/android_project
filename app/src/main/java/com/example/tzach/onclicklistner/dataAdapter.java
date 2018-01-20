package com.example.tzach.onclicklistner;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tzach.onclicklistner.core.Images;
import com.example.tzach.onclicklistner.core.MyInfoManager;

import java.util.List;

/**
 * Created by Tzach on 12/14/2017.
 */

public class dataAdapter extends RecyclerView.Adapter<ViewHolder> {

    private LayoutInflater mLayoutInflater;


    protected  List<Images> pictures;
    protected Context context;


    public dataAdapter(Context context, List<Images> pictures) {
        mLayoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.pictures=pictures;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.recycler_item, viewGroup, false));
    }




    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        final Images imageResId= pictures.get(position);
        viewHolder.setData(imageResId);

        viewHolder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                full_image_fragment detailsFragment = new full_image_fragment();
                // send data to fragment
                //Bundle args = new Bundle();
                //args.putInt(full_image_fragment.ARGUMENT_IMAGE_RES_ID, imageResId);
                //detailsFragment.setArguments(args);
                // open fragment
                MyInfoManager.getInstance().setSelectedImage(imageResId);
                FragmentManager fm = ((Activity)context).getFragmentManager();
                FragmentTransaction tr= fm.beginTransaction();
                tr.replace(R.id.content, detailsFragment);
                tr.addToBackStack(null);
                tr .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

}

class ViewHolder extends RecyclerView.ViewHolder {
    // Views
    public ImageView mImageView;


    public ViewHolder(View itemView) {
        super(itemView);

        // Get references to image and name.
        mImageView = (ImageView) itemView.findViewById(R.id.imageView);
    }

    public void setData(Images imageResId) {
        Bitmap bm = imageResId.getImg();
        if(bm!=null) {
            mImageView.setImageBitmap(bm);
        }

    }
}
