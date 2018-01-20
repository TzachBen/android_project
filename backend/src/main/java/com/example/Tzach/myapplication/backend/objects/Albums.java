package com.example.Tzach.myapplication.backend.objects;

import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Created by Tzach on 1/17/2018.
 */

public class Albums {


    private String id;
    private String name;


    public Albums( String name) {

        this.id = generateId();
        this.name = name;

    }

    private String generateId() {
        return "album_" + System.currentTimeMillis();
    }

    public Albums(){
        this.id = generateId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JSONObject toJson() {

        JSONObject iObj = new JSONObject();
        iObj.put("id", getId());
        iObj.put("name", getName());


        return iObj;
    }



    public static String toJson(List<Albums> list) {

        JSONObject jsonObj = new JSONObject();

        if (list == null) {
            return null;
        }

        if (list.size() == 0) {
            return null;
        }

        JSONArray jsonArray = new JSONArray();

        for (Albums albums : list) {

            if (albums != null) {

                JSONObject itemObj = albums.toJson();

                jsonArray.add(itemObj);
            }

        }

        jsonObj.put("albums", jsonArray);

        return jsonObj.toString(2);
    }

}
