package com.example.user.locistest;

import com.orm.SugarRecord;

/**
 * Created by User on 22.02.2017.
 */


public class FriendInList extends SugarRecord {
   public String name;
    public int userId;
    public boolean room;

    public FriendInList(String name, int userId, boolean room) {
        this.name = name;
        this.userId = userId;
        this.room = room;
    }
    public FriendInList(){}
}
