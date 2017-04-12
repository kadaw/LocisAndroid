package com.example.user.locistest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.user.locistest.Adapters.FriendsAdapter;
import com.example.user.locistest.Api.SearchUsersTask;

import java.util.ArrayList;
import java.util.List;

public class SearchUsersActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

ArrayList<FriendInList> friendsList;
    ListView listViewFriends;
    String token;
    Button backButton;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);
        backButton = (Button) findViewById(R.id.create_room_button);
        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(this);
        Intent intent = getIntent();
        token = intent.getStringExtra("token");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void friendsListView(List<FriendInList> friends){
        friendsList = new ArrayList<>();
        friendsList.addAll(friends);
        FriendsAdapter friendsAdapter = new FriendsAdapter(this,0,friendsList,token);
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



    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
            System.out.println(s);
            final SearchUsersTask api = new SearchUsersTask(token, s);
            api.execute(getWindow().getContext());


        return false;
    }

    public void onJSONParsed(List<FriendInList> friends) {
        friendsListView(friends);
    }
}
