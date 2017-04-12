package com.example.user.locistest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.user.locistest.Adapters.RoomsAdapter;
import com.example.user.locistest.Api.CreateRoomTask;
import com.example.user.locistest.Api.RoomsViewTask;

import java.util.ArrayList;
import java.util.List;

public class UserPage extends AppCompatActivity {
    String token;
    Button createRoomButton;
    Button invitesButton;
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
        final RoomsViewTask api = new RoomsViewTask(token);
        api.execute(getWindow().getContext());


        bindViews();

    }
    protected void onResume() {
        super.onResume();
    }
    private void bindViews(){
        createRoomButton = (Button) findViewById(R.id.button2);
        createRoomET = (EditText) findViewById(R.id.room_name_up_et);
        invitesButton = (Button) findViewById(R.id.invites_btn);
        final RoomsViewTask api = new RoomsViewTask(token);
        api.execute(getWindow().getContext());



        createRoomButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                System.out.println(token);
                final CreateRoomTask api = new CreateRoomTask(createRoomET.getText().toString(),token);
                api.execute(getWindow().getContext());
                Intent intent = new Intent(UserPage.this, SearchUsersActivity.class);
                startActivity(intent);



                //createNotification();

            }
        });
        invitesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent invitePageIntent = new Intent(UserPage.this, InvitesPage.class);
                invitePageIntent.putExtra("token_invite", token);
                startActivity(invitePageIntent);

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
                /*.addAction(R.drawable.ic_menu_manage,"Принять", pIntent)
                .addAction(R.drawable.ic_menu_manage,"Отклонить", pIntent)*/
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
                Intent intent = new Intent(UserPage.this, SearchUsersActivity.class);
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

    private void initListView(List<RoomInList> rooms){

//        roomsList.add(new RoomInList("Вписон в М2"));
//        roomsList.add(new RoomInList("Вписон в М2"));
//        roomsList.add(new RoomInList("Вписон в М2"));
//        roomsList.add(new RoomInList("Вписон в М2"));

        roomsList = new ArrayList<>();
        roomsList.addAll(rooms);
        RoomsAdapter roomsAdapter = new RoomsAdapter(this,0,roomsList,token);
        listView = (ListView) findViewById(R.id.lv_rooms);
        listView.setAdapter(roomsAdapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void onJSONParsed(List<RoomInList> rooms) {
        initListView(rooms);
    }

}
