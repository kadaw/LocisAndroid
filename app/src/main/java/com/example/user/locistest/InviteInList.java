package com.example.user.locistest;

import android.content.Intent;

/**
 * Created by User on 12.04.2017.
 */

public class InviteInList {
    public String senderName;
    public String roomName;
    public int invitationId;
    public InviteInList(String senderName, String  roomName, int invitationId){
        this.senderName = senderName;
        this.roomName = roomName;
        this.invitationId = invitationId;
    }
}
