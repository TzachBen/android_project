package com.example.tzach.onclicklistner;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tzach.onclicklistner.core.Images;
import com.example.tzach.onclicklistner.core.MyInfoManager;

import java.io.File;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment {

    private int[] mImageResIds;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // Get  names and descriptions.
        final Resources resources = context.getResources();

        String[] mNames = resources.getStringArray(R.array.names);
        // Get images.

        final TypedArray typedArray = resources.obtainTypedArray(R.array.images);
        final int imageCount = mNames.length;
        mImageResIds = new int[imageCount];
        for (int i = 0; i < imageCount; i++) {
            mImageResIds[i] = typedArray.getResourceId(i, 0);
        }
        typedArray.recycle();
    }


    private void updatePhotos(){

        File f= new  File(Environment.getExternalStorageDirectory(),Environment.DIRECTORY_DCIM);
        if(f.exists()){
            File fList[] = f.listFiles();
            if(fList!=null){
                for(File picFile:fList){

                    Images imgInfo = new Images();
                    imgInfo.setImgName(picFile.getName());
                    imgInfo.setDate(String.valueOf(picFile.lastModified()));
                   // Bitmap bm=BitmapFactory.decodeFile(picFile.getAbsolutePath());
                    //imgInfo.setImgPath(picFile.getPath());

                    MyInfoManager.getInstance().addNewImage(imgInfo);
                }
            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_fragment, container, false);
        Activity activity = getActivity();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(activity, 3));
        recyclerView.setAdapter(new dataAdapter(activity, mImageResIds));
        return view;
    }

}
