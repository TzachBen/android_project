package com.example.tzach.onclicklistner.core;

import android.content.Context;

import com.example.tzach.onclicklistner.DB.AlbumDataBase;

import java.util.List;

/**
 * Created by Tzach on 12/19/2017.
 */

public class MyInfoManager {

    private static MyInfoManager instance;

    private AlbumDataBase albumDataBase;

    private Albums editAlbum;

    private Images selectedImage;

    public void setSelectedImage(Images selectedImage) {
        this.selectedImage = selectedImage;
    }

    public Images getSelectedImage() {
        return selectedImage;
    }

    public static MyInfoManager getInstance(){

        if(instance == null){
            instance = new MyInfoManager();
        }
        return instance;
    }

    private MyInfoManager(){

    }

    public void openDatabase(Context ctx){
        albumDataBase = new AlbumDataBase(ctx);
        albumDataBase.open();
    }

    public void closeDatabase(){
        if(albumDataBase !=null){
            albumDataBase.close();
        }
    }

    public boolean addNewAlbum(Albums albums){
        return albumDataBase.addNewAlbum(albums);
    }

    public List<Albums> getAllPosts(){
        return albumDataBase.getAllAlbums();
    }


    public List<Images> getAllImages(){
        return albumDataBase.getAllImages();
    }



    public void setEditAlbum(Albums editAlbum) {
        this.editAlbum = editAlbum;
    }

    public Albums getEditAlbum() {
        return editAlbum;
    }

    public void updatePost(Albums albums) {
        if(albumDataBase !=null){
            albumDataBase.updateALBUM(albums);
        }
    }

    public boolean addNewImage(Images img){
        boolean res = false;
        if(albumDataBase !=null){
            res = albumDataBase.addNewImage(img);
        }
        return res;
    }
}

