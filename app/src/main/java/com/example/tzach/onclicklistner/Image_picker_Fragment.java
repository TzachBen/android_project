package com.example.tzach.onclicklistner;


import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tzach.onclicklistner.core.Albums;
import com.example.tzach.onclicklistner.core.MyInfoManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class Image_picker_Fragment extends DialogFragment {

    int SELECT_PICTURES = 1;
    //List<Uri> imagesEncodedList;
private Context context;
    private String albumId;


    public Image_picker_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_image_picker_, container, false);

        context = getActivity();

        Bundle bundle = getArguments();
        if(bundle!=null){

            String albumName = bundle.getString("albumname");
            Albums album = new Albums();
            albumId = album.getId();
            album.setName(albumName);
            if(MyInfoManager.getInstance().addNewAlbum(album)==false){
                MyInfoManager.getInstance().updateAlbum(album);
            }

            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*"); //allows any image file type. Change * to specific extension to limit it
    //**These following line is the important one!
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURES); //SELECT_PICTURES is simply a global int used to check the calling intent in onActivityResult
        }
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SELECT_PICTURES) {
            if(resultCode == Activity.RESULT_OK) {
                if(data.getClipData() != null) {
                   // imagesEncodedList = new ArrayList<Uri>();
                    int count = data.getClipData().getItemCount();
                    int currentItem = 0;
                    while(currentItem < count) {
                        Uri imageUri = data.getClipData().getItemAt(currentItem).getUri();
                        //do something with the image (save it to some directory or whatever you need to do with it here)
                      // imagesEncodedList.add(imageUri);
                         String result= getImageName(imageUri);
                        MyInfoManager.getInstance().updateImagesAlbum(result, albumId);

                        currentItem = currentItem + 1;
                    }

                   // for(Uri imagesEncodedList)

                } else if(data.getData() != null) {
                      Uri uri = data.getData();
                        String result= getImageName(uri);
                    //do something with the image (save it to some directory or whatever you need to do with it here

                        MyInfoManager.getInstance().updateImagesAlbum(result, albumId);
                }
            }
        }

    }

    private String getImageName(Uri uri){
        String fileName = null;
        String scheme = uri.getScheme();
        if (scheme.equals("file")) {
            fileName = uri.getLastPathSegment();
        }
        else if (scheme.equals("content")) {
            String[] proj = { MediaStore.Images.Media.DISPLAY_NAME };
            Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
            if (cursor != null && cursor.getCount() != 0) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME);
                cursor.moveToFirst();
                fileName = cursor.getString(columnIndex);
            }
            if (cursor != null) {
                cursor.close();
            }
        }
        return fileName;
    }

}



