package com.example.tzach.onclicklistner;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class album_fragment extends Fragment implements View.OnClickListener {

   Button btn1;


    public album_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_album_fragment, container, false);
        btn1 = rootView.findViewById(R.id.creat_album_btn);
        btn1.setOnClickListener(this);


        return rootView;
    }


    public void onClick(View v) {

        Fragment fragment = null;
        switch (v.getId()) {
            case R.id.creat_album_btn:
                fragment = new Image_picker_Fragment();
                replaceFragment( (Image_picker_Fragment)fragment);
                break;

        }}

    public void replaceFragment(Image_picker_Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.albumContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
