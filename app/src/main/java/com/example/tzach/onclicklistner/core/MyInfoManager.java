//package com.example.tzach.onclicklistner.core;
//
//import android.content.Context;
//
//import com.example.tzach.onclicklistner.DB.AlbumDataBase;
//
//import java.util.List;
//
///**
// * Created by Tzach on 12/19/2017.
// */
//
//public class MyInfoManager {
//
//    private static MyInfoManager instance;
//
//    private AlbumDataBase albumDataBase;
//
//    private Albums editAlbum;
//
//    public static MyInfoManager getInstance(){
//
//        if(instance == null){
//            instance = new MyInfoManager();
//        }
//        return instance;
//    }
//
//    private MyInfoManager(){
//
//    }
//
//    public void openDatabase(Context ctx){
//        albumDataBase = new AlbumDataBase(ctx);
//        albumDataBase.open();
//    }
//
//    public void closeDatabase(){
//        if(albumDataBase !=null){
//            albumDataBase.close();
//        }
//    }
//
//    public boolean addNewAlbum(Albums albums){
//        return albumDataBase.addNewAlbum(albums);
//    }
//
//    public List<Albums> getAllPosts(){
//        return albumDataBase.getAllAlbums();
//    }
//
//
//    public void setEditAlbum(Albums editAlbum) {
//        this.editAlbum = editAlbum;
//    }
//
//    public Albums getEditAlbum() {
//        return editAlbum;
//    }
//
//    public void updatePost(Albums albums) {
//        if(albumDataBase !=null){
//            albumDataBase.updateaLBUM(albums);
//        }
//    }
//}
//
