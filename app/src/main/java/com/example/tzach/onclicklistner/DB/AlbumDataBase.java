package com.example.tzach.onclicklistner.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tzach.onclicklistner.core.Albums;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Tzach on 12/19/2017.
 */

public class AlbumDataBase extends SQLiteOpenHelper {

    private  static final String DATABASE_NAME = "albuns_data_base";
    private static final int VERSION = 1;
    private SQLiteDatabase db;
    private static Date date;

    private  static final String ALBUM_TABLE = "albums";
    private  static final String ALBUM_ID = "id";
    private  static final String ALBUM_NAME = "name";


    private static final String[] ALBUM_COLUMNS = {ALBUM_ID, ALBUM_NAME,};

    public AlbumDataBase(Context context) {

        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createPostsTableSqL =
                "create table if not exists "+ ALBUM_TABLE +"("
                        + ALBUM_ID + " text primary key, "
                        + ALBUM_NAME+ " text "
                        +")";
        sqLiteDatabase.execSQL(createPostsTableSqL);

    }

    public void open(){
        db = getWritableDatabase();
    }

    public void close(){
        if(db!=null){
            db.close();
        }
    }

    public boolean updateaLBUM(Albums albums) {

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




    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
