package com.example.tzach.onclicklistner;


import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class album_fragment extends Fragment implements View.OnClickListener {

   Button btn1;
    private Context context;

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

        context = getActivity();

        return rootView;
    }


    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.creat_album_btn:


                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("album name");

// Set up the input
                final EditText input = new EditText(context);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                builder.setView(input);

// Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String m_Text = input.getText().toString();
                        if(m_Text!=null && !m_Text.equals("")) {
                            Fragment fragment = new Image_picker_Fragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("albumname", m_Text);
                            fragment.setArguments(bundle);
                            replaceFragment((Image_picker_Fragment) fragment);
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();


                break;

        }}

    public void replaceFragment(Image_picker_Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.albumContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
