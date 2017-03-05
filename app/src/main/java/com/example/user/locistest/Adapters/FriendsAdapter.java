package com.example.user.locistest.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.user.locistest.Fragments.FriendsFragment;
import com.example.user.locistest.FriendInList;
import com.example.user.locistest.R;

import java.util.List;

import static android.support.v7.appcompat.R.styleable.View;

/**
 * Created by User on 01.03.2017.
 */

public class FriendsAdapter extends ArrayAdapter {
    Activity friendsActivity;
    int resource;
    List list;

    public FriendsAdapter(Context context, int resource, List objects){
        super(context, resource, objects);
        friendsActivity = (Activity) context;
        this.resource = resource;
        list = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder;
        if(convertView==null){
            LayoutInflater inflater = friendsActivity.getLayoutInflater();
            convertView = inflater.inflate(resource, null);
            viewHolder = new ViewHolder();
            viewHolder.nameTextView = (TextView) convertView.findViewById(R.id.tv_friend_name);

            FriendInList selectdFriend = (FriendInList) list.get(position);
            viewHolder.nameTextView.setText(selectdFriend.name);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }


        return convertView;

    }
    class ViewHolder{
        TextView nameTextView;


    }

}
