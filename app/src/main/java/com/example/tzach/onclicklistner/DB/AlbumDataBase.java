package com.example.tzach.onclicklistner.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tzach.onclicklistner.core.Albums;
import com.example.tzach.onclicklistner.core.Images;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Tzach on 12/19/2017.
 */

public class AlbumDataBase extends SQLiteOpenHelper {

    private  static final String DATABASE_NAME = "albums_data_base";
    private static final int VERSION = 1;
    private SQLiteDatabase db;
    private static long date;

    //ALBUMS
    private  static final String ALBUM_TABLE = "albums";
    private  static final String ALBUM_ID = "id";
    private  static final String ALBUM_NAME = "name";

    //IMAGES
    private  static final String IMAGES_TABLE = "images";
    private  static final String IMAGES_NAME = "imagename";
    private  static final String RELATED_ALBUM_ID = "albumid";
    private  static final String IMG_DATE ="mdate" ;
    //private  static final String IMG_PATH = "imgpath";



    private static final String[] ALBUM_COLUMNS = {ALBUM_ID, ALBUM_NAME,};
    private static final String[] IMAGE_COLUMNS = {IMAGES_NAME, RELATED_ALBUM_ID, IMG_DATE};



    public AlbumDataBase(Context context) {

        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createAlbumsTableSqL =
                "create table if not exists "+ ALBUM_TABLE +"("
                        + ALBUM_ID + " text primary key, "
                        + ALBUM_NAME+ " text "
                        +")";
        sqLiteDatabase.execSQL(createAlbumsTableSqL);

        String createImagesTableSqL =
                "create table if not exists "+ IMAGES_TABLE +"("
                        + IMAGES_NAME + " text primary key, "
                        + RELATED_ALBUM_ID +" text, "
                        + IMG_DATE+ " text"
                        +")";
        sqLiteDatabase.execSQL(createImagesTableSqL);



    }

    public void open(){
        db = getWritableDatabase();
    }

    public void close(){
        if(db!=null){
            db.close();
        }
    }
//ALBUM FUNCTIONS
    public boolean updateALBUM(Albums albums) {

        long result = -1;
        ContentValues values = new ContentValues();
        values.put(ALBUM_ID, albums.getId());
        values.put(ALBUM_NAME, albums.getName());
        String [] whereArg = {albums.getId()};
        result = db.update(ALBUM_TABLE,values, ALBUM_ID + "=?", whereArg);
        if(result > 0){
            return true;
        }
        return false;
    }

    public boolean addNewAlbum(Albums albums){

        long result = -1;
        ContentValues values = new ContentValues();
        values.put(ALBUM_ID, albums.getId());
        values.put(ALBUM_NAME, albums.getName());


        result = db.insert(ALBUM_TABLE,null,values);
        if(result > 0){
            return true;
        }
        return false;

    }

    public List<Albums> getAllAlbums(){
        List<Albums> result = new ArrayList<Albums>();

        Cursor cursor = null;
        try {
            cursor = db.query(ALBUM_TABLE, ALBUM_COLUMNS, null, null, null, null, null);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    Albums albums = new Albums();
                    albums.setId(cursor.getString(0));
                    albums.setName(cursor.getString(1));
                    result.add(albums);
                    cursor.moveToNext();
                }
            }
        }catch (Throwable t){
            t.printStackTrace();
        }
        finally {
            if(cursor!=null){
                cursor.close();
            }
        }
        return result;
    }


    public boolean updateImagesAlbum(String imgId, String albumId) {
        long result = -1;
        try {
            ContentValues values = new ContentValues();
            values.put(IMAGES_NAME, imgId);
            values.put(RELATED_ALBUM_ID, albumId);
            String[] whereArg = {imgId};
            result = db.update(IMAGES_TABLE, values, IMAGES_NAME + "=?", whereArg);
            if (result > 0) {
                return true;
            }
        }catch (Throwable t){
            t.printStackTrace();
        }
        return false;
    }

//IMAGE FUNCTIONS
public boolean updateImage(Images images) {

    long result = -1;
    ContentValues values = new ContentValues();
    values.put(IMAGES_NAME, images.getImgName());
    values.put(RELATED_ALBUM_ID, images.getAlbumId());
    values.put(IMG_DATE, images.getDate());
    String [] whereArg = {images.getImgName()};
    result = db.update(IMAGES_TABLE,values, IMAGES_NAME + "=?", whereArg);
    if(result > 0){
        return true;
    }
    return false;
}

    public boolean addNewImage(Images images){

        long result = -1;
        ContentValues values = new ContentValues();
        values.put(IMAGES_NAME, images.getImgName());
        values.put(RELATED_ALBUM_ID, images.getAlbumId());
       values.put(IMG_DATE, images.getDate());

        result = db.insert(IMAGES_TABLE,null,values);
        if(result > 0){
            return true;
        }
        return false;

    }

    public List<Images> getAllImages(){
        List<Images> result = new ArrayList<Images>();

        Cursor cursor = null;
        try {
            cursor = db.query(IMAGES_TABLE, IMAGE_COLUMNS, null, null, null, null, null);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                   Images img = new Images();
                    img.setImgName(cursor.getString(0));
                    img.setAlbumId(cursor.getString(1));
                    img.setDate(cursor.getString(2));
                   // img.setImgPath(cursor.getString(3));

                    result.add(img);
                    cursor.moveToNext();
                }
            }
        }catch (Throwable t){
            t.printStackTrace();
        }
        finally {
            if(cursor!=null){
                cursor.close();
            }
        }
        return result;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
