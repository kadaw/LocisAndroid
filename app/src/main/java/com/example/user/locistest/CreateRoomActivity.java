package com.example.user.locistest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.user.locistest.Api.CreateRoomTask;

import java.util.ArrayList;

public class CreateRoomActivity extends AppCompatActivity {

ArrayList<FriendInList> friendsList;
    ListView listViewFriends;
    String response;
    EditText roomLabel;
    Button createRoomBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);
        friendsListView();
        roomLabel = (EditText) findViewById(R.id.room_name_et);
        createRoomBtn = (Button) findViewById(R.id.create_room_button);
        createRoomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CreateRoomTask api = new CreateRoomTask(roomLabel.getText().toString());
                api.execute(getWindow().getContext());
            }
        });
    }

    private void friendsListView(){
        friendsList = new ArrayList<>();
        friendsList.add(new FriendInList("Борян"));
        friendsList.add(new FriendInList("Антоха"));
        friendsList.add(new FriendInList("ВиВиВи"));
        friendsList.add(new FriendInList("Кадаш"));

        listViewFriends = (ListView) findViewById(R.id.create_room_friends_lv);
    }

    public void getToken(String token, int responseCode) {

    }
}
