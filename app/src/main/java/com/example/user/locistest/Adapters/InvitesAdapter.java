package com.example.user.locistest.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.locistest.Api.InvitationAnswerTask;
import com.example.user.locistest.Api.SendInvitationTask;
import com.example.user.locistest.InviteInList;
import com.example.user.locistest.R;
import com.example.user.locistest.RoomInList;

import java.util.List;

/**
 * Created by User on 12.04.2017.
 */

public class InvitesAdapter extends ArrayAdapter {
    Activity invitesActivity;
    int resource;
    List<InviteInList> invitesInList;
    String token;
    Context context;
    public InvitesAdapter(Context context, int resource, List<InviteInList> objects, String token){
        super(context, resource, objects);
        this.invitesActivity = (Activity) context;
        this.resource = resource;
        this.token = token;
        invitesInList = objects;
    }
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = invitesActivity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_invite, null);
            viewHolder = new ViewHolder();

            viewHolder.roomName = (TextView) convertView.findViewById(R.id.room_name_invite_tv);
            viewHolder.senderName = (TextView) convertView.findViewById(R.id.sender_name_invite_tv);
            InviteInList selectedInvite = invitesInList.get(position);
            viewHolder.roomName.setText(selectedInvite.roomName);
            viewHolder.senderName.setText(selectedInvite.senderName);

        }
        Button acceptInviteButton = (Button) convertView.findViewById(R.id.accept_invite_btn);
        acceptInviteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InviteInList selectedInvitation = invitesInList.get(position);
                int invitationId = selectedInvitation.invitationId;
                final InvitationAnswerTask api = new InvitationAnswerTask(token,invitationId,"true");
                api.execute(context);


            }
        });
        Button declineInviteButton = (Button) convertView.findViewById(R.id.decline_invite_btn);
        declineInviteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InviteInList selectedInvitation = invitesInList.get(position);
                int invitationId = selectedInvitation.invitationId;
                final InvitationAnswerTask api = new InvitationAnswerTask(token,invitationId,"false");
                api.execute(context);
            }
        });



        return convertView;

    }
    class ViewHolder{
        TextView roomName;
        TextView senderName;
    }
}
