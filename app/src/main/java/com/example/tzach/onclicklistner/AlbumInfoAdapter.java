package com.example.tzach.onclicklistner;

/**
 * Created by Tzach on 1/21/2018.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.tzach.onclicklistner.core.Albums;
import com.example.tzach.onclicklistner.core.MyInfoManager;
import com.example.tzach.onclicklistner.utils.NetworkConnector;
import com.example.tzach.onclicklistner.utils.NetworkResListener;
import com.example.tzach.onclicklistner.utils.ResStatus;

import org.json.JSONObject;

import java.util.List;


public class AlbumInfoAdapter extends ArrayAdapter<Albums> {

    private Context context;
    private List<Albums> postsList;


    public AlbumInfoAdapter(@NonNull Context context,
                            @LayoutRes int resource,
                            List<Albums> postsList) {
        super(context, resource);

        this.context = context;
        this.postsList = postsList;

    }

    @Override
    public int getCount() {

        return postsList.size();
    }


    @Nullable
    @Override
    public Albums getItem(int position) {

        return postsList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        LayoutInflater inflter = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View itemView=inflter.inflate(R.layout.list_view_row,
                null,false);
        final Albums albums= postsList.get(position);


        final ImageView postImageView = (ImageView) itemView.findViewById(R.id.imageView);



        NetworkConnector.getInstance().sendRequestToServer(NetworkConnector.GET_POST_IMAGE_REQ, albums, new NetworkResListener() {
            @Override
            public void onPreUpdate() {
                //Toast.makeText(context,"start download img... id=" + post.getId(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPostUpdate(byte[] res, ResStatus status) {

            }

            @Override
            public void onPostUpdate(JSONObject res, ResStatus status) {

            }

            @Override
            public void onPostUpdate(Bitmap res, ResStatus status) {

            }


        });


//        ImageButton deleteBtn = (ImageButton) itemView.findViewById(R.id.delete_btn);
//        deleteBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(MyInfoManager.getInstance().deletePost(post)) {
//                    postsList.remove(post);
//                    AlbumInfoAdapter.this.notifyDataSetChanged();
//                }
//            }
//        });
        return itemView;

    }
}
