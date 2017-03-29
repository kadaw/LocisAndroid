package com.example.user.locistest.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.locistest.Api.CreateRoomTask;
import com.example.user.locistest.Api.SendInvitationTask;
import com.example.user.locistest.FriendInList;
import com.example.user.locistest.R;

import java.util.List;

/**
 * Created by User on 01.03.2017.
 */

public class FriendsAdapter extends ArrayAdapter {
    Activity friendsActivity;
    int resource;
    List<FriendInList> friendInLists;
    String token;
    Context context;

    public FriendsAdapter(Context context, int resource, List<FriendInList> objects,String token){
        super(context, resource, objects);
        this.friendsActivity = (Activity) context;
        this.resource = resource;
        friendInLists = objects;
        this.token=token;
    }

    public View getView(final int position, View convertView, final ViewGroup parent){
        ViewHolder viewHolder;
        if(convertView==null){
            LayoutInflater inflater = friendsActivity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_friend, null);
            viewHolder = new ViewHolder();
            viewHolder.nameTextView = (TextView) convertView.findViewById(R.id.tv_friend_name);
            FriendInList selectedFriend = friendInLists.get(position);
            viewHolder.nameTextView.setText(selectedFriend.name);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Button addToRomButton = (Button)  convertView  .findViewById(R.id.button_add_to_room);

        addToRomButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FriendInList selectedFriend = friendInLists.get(position);
                int userId = selectedFriend.userId;
                final SendInvitationTask api = new SendInvitationTask(userId,14,token);
                api.execute(context);
                addToRomButton.setText("Добавлен");
                addToRomButton.setBackgroundColor(0);
                addToRomButton.setClickable(false);

            }

        });

        return convertView;

    }
    class ViewHolder{
        TextView nameTextView;


    }

}
