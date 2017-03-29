package com.example.user.locistest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.locistest.Adapters.RoomsAdapter;

import java.util.ArrayList;

public class UserPage extends AppCompatActivity {
    String token;
    Button createRoomButton;
    ArrayList<RoomInList>  roomsList;
    ArrayAdapter adapter;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user_page_room);
        final Intent intent = getIntent();
        token = intent.getStringExtra("token");

        initListView();
    }
    protected void onResume() {
        super.onResume();
        bindViews();
    }
    private void bindViews(){
        createRoomButton = (Button) findViewById(R.id.button2);

        System.out.println(token);
        createRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createRoomIntent = new Intent(getWindow().getContext(), CreateRoomActivity.class);
                createRoomIntent.putExtra("token",token);
                startActivity(createRoomIntent);
            }
        });
        listView = (ListView) findViewById(R.id.lv_rooms);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(UserPage.this, CreateRoomActivity.class);
                intent.putExtra("token", token);
                startActivity(intent);
            }
        });

    }
    private void initListView(){
        roomsList = new ArrayList<>();
        roomsList.add(new RoomInList("Вписон в М2", "25"));
        roomsList.add(new RoomInList("Вписон в М2", "35"));
        roomsList.add(new RoomInList("Вписон в М2", "35"));
        roomsList.add(new RoomInList("Вписон в М2", "35"));
        roomsList.add(new RoomInList("Вписон в М2", "35"));
        roomsList.add(new RoomInList("Вписон в М2", "35"));
        roomsList.add(new RoomInList("Вписон в М2", "35"));

        adapter = new RoomsAdapter(this, R.layout.item_room, roomsList);

        listView = (ListView) findViewById(R.id.lv_rooms);
        listView.setAdapter(adapter);
    }

}
