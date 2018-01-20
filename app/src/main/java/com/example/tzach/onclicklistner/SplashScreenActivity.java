package com.example.tzach.onclicklistner;



        import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


        import com.example.tzach.onclicklistner.core.Images;
        import com.example.tzach.onclicklistner.core.MyInfoManager;

        import java.io.File;
import java.util.Date;

public class SplashScreenActivity extends AppCompatActivity implements  ActivityCompat.OnRequestPermissionsResultCallback {


    private final int MY_PERMISSIONS_REQ= 1212;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        MyInfoManager.getInstance().openDatabase(this);

        permissionsRequest();



//        Thread myThread = new Thread(){
//            @Override
//            public void run() {
//                try {
//                    sleep(1000);
//                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        myThread.start();
    }

    private void permissionsRequest() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.INTERNET)
                        != PackageManager.PERMISSION_GRANTED
                ||
                ContextCompat.checkSelfPermission(this,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED
                ){

            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.INTERNET},
                    MY_PERMISSIONS_REQ);
        }
        else{
            // all granted already do another stack
            collectDCIMpictures();
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
            // start MainActivity and close this activity

        }
    }

    private void collectDCIMpictures() {
        File dcimDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
       // File photosDir = new File(dcimDirectory,"Camera");
        if(dcimDirectory.exists()){
            File photosList[] = dcimDirectory.listFiles();

            if(photosList!=null && photosList.length>0){
                for(File image:photosList){

                    try {
                        if(!image.getName().endsWith(".mp4")) {
                            // BitmapFactory.Options bmOptions = new BitmapFactory.Options();

//                            Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(), bmOptions);
//
//                            bitmap = Bitmap.createScaledBitmap(bitmap, 300, 500, true);
                            Date date = new Date(image.lastModified());
                            Images pictureInfo = new Images();
                            pictureInfo.setImgName(image.getName());
                            MyInfoManager.getInstance().addNewImage(pictureInfo);
                        }

                    }catch (Throwable e){
                        e.printStackTrace();
                    }
                }
            }

        }




    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQ: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0) {
                    int grantsOkcount = 0;
                    for(int i=0; i<grantResults.length;i++){
                        if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                            grantsOkcount++;
                        }
                    }
                    if(grantsOkcount==grantResults.length) {
                        Toast.makeText(this, "all permissions granted!", Toast.LENGTH_LONG).show();
                        // permission was granted, yay! Do the
                        // contacts-related task you need to do.

                        // start MainActivity and close this activity
                        collectDCIMpictures();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        finish();

                    }
                    else{
                        Toast.makeText(this, "some permissions was not granted!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


}