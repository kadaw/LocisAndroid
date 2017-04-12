package com.example.user.locistest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OpenedRoomActivity extends AppCompatActivity {
    TextView roomNameTV;
    String roomNameString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opened_room);
        Intent Intent = getIntent();
        roomNameString = Intent.getStringExtra("roomName");
        roomNameTV = (TextView) findViewById(R.id.room_name_opened_room_tv);
        roomNameTV.setText(roomNameString);
        System.out.println(roomNameTV);
    }
}
