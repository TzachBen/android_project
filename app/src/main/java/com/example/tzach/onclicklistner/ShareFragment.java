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
public class ShareFragment extends Fragment implements View.OnClickListener {

    Button btn;


    public ShareFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_share, container, false);
        btn= view.findViewById(R.id.createSharing);
        btn.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {

            Fragment fragment = null;
            switch (v.getId()) {
                case R.id.createSharing:
                    fragment = new Image_picker_Fragment();
                    replaceFragment( (Image_picker_Fragment)fragment);
                    break;

            }}

    public void replaceFragment(Image_picker_Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.share_frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
