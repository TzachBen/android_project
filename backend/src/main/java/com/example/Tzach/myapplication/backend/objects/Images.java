package com.example.Tzach.myapplication.backend.objects;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by Tzach on 1/17/2018.
 */

public class Images {

    private String imgName;
    private String date;
    private String albumId;
    private byte[] image = null;


    public Images(String imgName, String date, String albumId, byte[] image) {
        this.imgName = imgName;
        this.date = date;
        this.albumId = albumId;
        this.image = image;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


    public JSONObject toJson() {

        JSONObject iObj = new JSONObject();
        iObj.put("name", getImgName());
        iObj.put("date", getDate());
        iObj.put("albumId", getAlbumId());
        iObj.put("image", getImage());


        return iObj;
    }

    public static String toJson(List<Images> list) {

        JSONObject jsonObj = new JSONObject();

        if (list == null) {
            return null;
        }

        if (list.size() == 0) {
            return null;
        }

        JSONArray jsonArray = new JSONArray();

        for (Images images : list) {

            if (images != null) {

                JSONObject itemObj = images.toJson();

                jsonArray.add(itemObj);
            }

        }

        jsonObj.put("images", jsonArray);

        return jsonObj.toString(2);
    }



}
