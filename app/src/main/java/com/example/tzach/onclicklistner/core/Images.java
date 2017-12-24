package com.example.tzach.onclicklistner.core;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by Tzach on 12/19/2017.
 */

public class Images {

    private String id;
    private String albumId;
    private Bitmap img;



    public Images(Bitmap img){
        this.id = generateId();
        this.img=img;
        this.albumId=null;

    }

    private String generateId() {
        return "image_" + System.currentTimeMillis();
    }

    public Images(){
        this.id = generateId();
        this.albumId=null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public void setAlbumId(String albumId){
        this.albumId=albumId;
    }
    public String getAlbumId(){
        return this.albumId;
    }
    public byte[] getImgAsByteArray() {
        byte[] res = new byte[0];
        if(img!=null){
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            img.compress(Bitmap.CompressFormat.PNG,0,outputStream);
            res = outputStream.toByteArray();
        }
        return res;

    }

    public void setImgFromByteArray(byte[] imgFromByteArray) {
        if(imgFromByteArray!=null) {
            img = BitmapFactory.decodeByteArray(imgFromByteArray, 0, imgFromByteArray.length);
        }
    }
}
