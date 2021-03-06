package com.example.tzach.onclicklistner.core;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Tzach on 12/19/2017.
 */

public class Images {

    private String imgName;
    private String date;
    private String albumId;
   // private Bitmap img;

   // private String imgPath;

   // public void setImgPath(String imgPath) {
   //     this.imgPath = imgPath;
   // }

   // public String getImgPath() {
  //      return imgPath;
  //  }

    public Images(){
        super();
        this.imgName = generateId();
    }

    public Images(String imgName, String albumId,
                  String date,  Bitmap img, String imgPath) {
        this.imgName = generateId();
        this.albumId=albumId;
        this.date=date;
       // this.img = img;
       // this.imgPath = imgPath;
    }




    private String generateId() {
        return "img_" + System.currentTimeMillis();
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }


    public Bitmap getImg() {
        Bitmap bitmap = null;
        try {

            File dcimDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            //  File photosDir = new File(dcimDirectory,"Camera");
            File img = new File(dcimDirectory, getImgName());
            if (img.exists()) {
                BitmapFactory.Options bmOptions = new BitmapFactory.Options();

                Bitmap mbitmap = BitmapFactory.decodeFile(img.getAbsolutePath(), bmOptions);

                bitmap = Bitmap.createScaledBitmap(mbitmap, 300, 500, true);
            }
        }catch(Throwable t){
            t.printStackTrace();
        }
        return bitmap;
    }


    public static byte[] getImgAsByteArray(Bitmap bm){
        byte[] res= new byte[0];
        if(bm != null){
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG,0,outputStream);
            res = outputStream.toByteArray();
        }
        return res;
    }

    public void setImg(byte[] imgeArray){
        if(imgeArray!=null){
            Bitmap bitmap= BitmapFactory.decodeByteArray(imgeArray, 0, imgeArray.length);

            File dcimDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            File photosDir = new File(dcimDirectory,"Camera");
            File img = new File(photosDir, getImgName());

                try{
                    OutputStream  os = new FileOutputStream(img);
                    bitmap.compress(Bitmap.CompressFormat.PNG,90,os);
                }
                catch(Throwable t){
                    t.printStackTrace();
                }
        }
    }

    public static List<Images> parseJson(JSONObject json) {

        List<Images> images  = null;
        try {

            images = new ArrayList<Images>();

            JSONArray foldersJsonArr = json.getJSONArray("images");

            for (int i = 0; i < foldersJsonArr.length(); i++) {
                try {
                    JSONObject iObj = foldersJsonArr.getJSONObject(i);
                    Images img = new Images();
                    img.setImgName(iObj.getString("imgName"));
                    img.setAlbumId(iObj.getString("albumId"));
                    img.setDate(iObj.getString("date"));
                    //img.setImgPath(iObj.getString("img_path"));
                  //  img.setImg(iObj.("img"));


                    images.add(img);

                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }

        } catch (Throwable e) {
            e.printStackTrace();
        }

        return images;
    }
}
