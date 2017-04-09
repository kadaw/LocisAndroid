package com.example.user.locistest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.locistest.Adapters.RoomsAdapter;
import com.example.user.locistest.Api.AcceptInvitationTask;
import com.example.user.locistest.Api.CreateRoomTask;

import java.util.ArrayList;

public class UserPage extends AppCompatActivity {
    String token;
    Button createRoomButton;
    ArrayList<RoomInList>  roomsList;
    ArrayAdapter adapter;
    ListView listView;
    EditText createRoomET;
    public String response;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user_page_room);
        final Intent intent = getIntent();
        token = intent.getStringExtra("token");


        initListView();
        bindViews();

    }
    protected void onResume() {
        super.onResume();
    }
    private void bindViews(){
        createRoomButton = (Button) findViewById(R.id.button2);
        createRoomET = (EditText) findViewById(R.id.room_name_up_et);




        createRoomButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                System.out.println(token);
                final AcceptInvitationTask api = new AcceptInvitationTask(token);
                api.execute(getWindow().getContext());
              //  Intent intent = new Intent(UserPage.this, CreateRoomActivity.class);
               // startActivity(intent);



                createNotification();

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

    public void createNotification(){
        String roomNameget = getIntent().getStringExtra("RoomName");

        Intent notifIntent = new Intent();
        PendingIntent pIntent = PendingIntent.getActivity(UserPage.this,0,notifIntent,0);
        Notification noti = new Notification.Builder(UserPage.this)
                .setTicker("Вас пригласили в комнату")
                .setContentTitle("Вас пригласили в комнату")
                .setContentText("%username% в " + roomNameget)
                .setSmallIcon(R.drawable.ic_menu_gallery)
                .addAction(R.drawable.ic_menu_manage,"Принять", pIntent)
                .addAction(R.drawable.ic_menu_manage,"Отклонить", pIntent)
                .setContentIntent(pIntent).getNotification();

        noti.flags = Notification.FLAG_AUTO_CANCEL;

        NotificationManager nManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        nManager.notify(0,noti);
    }

    public void getToken(String token,int responseCode) {
        switch (responseCode) {
            case 200: {
                int length = token.length() - 1;
                response = token.substring(1, length);
                Intent intent = new Intent(UserPage.this, CreateRoomActivity.class);
                intent.putExtra("token", response);

                startActivity(intent);
                finish();

            }
            ;
            break;
            case 403:
                ;
                break;
            case 400:
                ;
                break;
            case 500:
              ;
                break;
            default:
                break;
        }
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
