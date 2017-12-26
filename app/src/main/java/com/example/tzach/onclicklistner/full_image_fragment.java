package com.example.tzach.onclicklistner;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class full_image_fragment extends Fragment implements View.OnClickListener {


    public static final String ARGUMENT_IMAGE_RES_ID = "imageResId";
    private ImageButton shareBtn;
    private ImageButton  deleteBtn;
    private ImageView imageView;
    private AlertDialog dialog;
    private View view;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_full_image, container, false);
        shareBtn= view.findViewById(R.id.share_image_btn);
        deleteBtn = view.findViewById(R.id.delete_image_btn);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        Bundle args = getArguments();
        imageView.setImageResource(args.getInt(ARGUMENT_IMAGE_RES_ID));
        shareBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.share_image_btn:
                    startShare();

                break;

            case R.id.delete_image_btn:
                dialog= new AlertDialog.Builder(this.getContext()).create();
                dialog.setTitle("DELETE IMAGE");
                dialog.setMessage("are you sure?");
                dialog.setButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {



                    }
                });
                dialog.setButton2("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialog.dismiss();

                    }

                });

                dialog.show();

                break;

        }


    }
    public void startShare(){
        Bitmap bitmap = viewToBitmap(imageView,imageView.getWidth(),imageView.getHeight());
        Intent shareIntent = new Intent((Intent.ACTION_SEND));
        shareIntent.setType("image/jpeg");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        File file = new File(Environment.getExternalStorageDirectory()+File.separator+"ImageDeme.jpg");
        try {
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        shareIntent.putExtra(Intent.EXTRA_STREAM,Uri.parse("file:///sdcard/Imagedeme.jpg"));
        startActivity(Intent.createChooser(shareIntent,"share with"));
    }

    public static Bitmap viewToBitmap(View view, int h , int w){
        Bitmap bitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }
}
