package com.example.user.locistest.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.user.locistest.Adapters.FriendsAdapter;
import com.example.user.locistest.FriendInList;
import com.example.user.locistest.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendsFragment extends Fragment {
    ListView listView;
    ArrayAdapter adapter;
    ArrayList<FriendInList> friendsList;


    public FriendsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friends, container, false);
    }
    public void onResume() {
        super.onResume();
        initListView();

    }

    private void initListView(){
        friendsList = new ArrayList<>();
        /*friendsList.add(new FriendInList("Тетя Мотя"));
        friendsList.add(new FriendInList("PHARAOH"));
        friendsList.add(new FriendInList("ХАСКИ"));
        friendsList.add(new FriendInList("СЛАВА КПСС"));*/

        adapter = new FriendsAdapter(getActivity(), R.layout.item_friend, friendsList);
        listView = (ListView) getActivity().findViewById(R.id.lv_my_friends);
        listView.setAdapter(adapter);
      //  listView.setOnItemClickListener(this);



    }

}
