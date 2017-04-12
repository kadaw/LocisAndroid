package com.example.user.locistest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.user.locistest.Adapters.InvitesAdapter;
import com.example.user.locistest.Api.InvitationViewTask;

import java.util.ArrayList;
import java.util.List;

public class InvitesPage extends AppCompatActivity {
    ArrayList<InviteInList> invitesList;
    String token;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_invites);

       Intent Intent = getIntent();
        token = Intent.getStringExtra("token_invite");
        System.out.println(token);
        final InvitationViewTask api = new InvitationViewTask(token);
         api.execute(getWindow().getContext());
    }
    private void initListView(List<InviteInList> invites){


        invitesList = new ArrayList<>();
        invitesList.addAll(invites);
        InvitesAdapter invitesAdapter = new InvitesAdapter(this,0,invitesList,token);
        listView = (ListView) findViewById(R.id.lv_invites);
        listView.setAdapter(invitesAdapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

    }

    public void onJSONParsed(List<InviteInList> invites) {
        initListView(invites);
    }
}
