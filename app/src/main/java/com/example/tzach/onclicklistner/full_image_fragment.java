package com.example.tzach.onclicklistner;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class full_image_fragment extends Fragment {


    public static final String ARGUMENT_IMAGE_RES_ID = "imageResId";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_full_image, container, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);


        Bundle args = getArguments();
        imageView.setImageResource(args.getInt(ARGUMENT_IMAGE_RES_ID));
        return view;
    }
}
