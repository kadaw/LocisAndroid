package com.example.user.locistest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.user.locistest.Adapters.FriendsAdapter;
import com.example.user.locistest.Api.CreateRoomTask;
import com.example.user.locistest.Api.SearchUsersTask;
import com.orm.SugarContext;

import java.util.ArrayList;

public class CreateRoomActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

ArrayList<FriendInList> friendsList;
    ListView listViewFriends;
    String response;
    EditText roomLabel;
    Button createRoomBtn;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);
        friendsListView();
        roomLabel = (EditText) findViewById(R.id.room_name_et);
        createRoomBtn = (Button) findViewById(R.id.create_room_button);
        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(this);

        createRoomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //final CreateRoomTask api = new CreateRoomTask(roomLabel.getText().toString());
                final SearchUsersTask api = new SearchUsersTask(response,"kadaw");
                api.execute(getWindow().getContext());
            }
        });
    }

    private void friendsListView(){
        friendsList = new ArrayList<>();
        friendsList.add(new FriendInList("Борян",1,false));
        friendsList.add(new FriendInList("Антоха",2,false));
        friendsList.add(new FriendInList("ВиВиВи",3,false));
        friendsList.add(new FriendInList("Кадаш",4,false));
        FriendsAdapter friendsAdapter = new FriendsAdapter(this,0,friendsList);
        listViewFriends = (ListView) findViewById(R.id.create_room_friends_lv);
        listViewFriends.setAdapter(friendsAdapter);
        listViewFriends.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    public void getToken(String token, int responseCode) {

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}
