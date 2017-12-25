package com.example.tzach.onclicklistner;


import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Image_picker_Fragment extends DialogFragment {


    private static final int PICKFILE_REQUEST_CODE =2 ;

    public Image_picker_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_image_picker_, container, false);

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("file/*");
        startActivityForResult(intent, PICKFILE_REQUEST_CODE);

        return rootView;
    }

}
