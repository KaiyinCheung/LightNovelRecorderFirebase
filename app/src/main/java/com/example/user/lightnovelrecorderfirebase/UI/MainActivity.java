package com.example.user.lightnovelrecorderfirebase.UI;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.user.lightnovelrecorderfirebase.Adapter;
import com.example.user.lightnovelrecorderfirebase.ListItem;
import com.example.user.lightnovelrecorderfirebase.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Adapter.ItemClickCallBack {

    //UI
    private Button button_add_data;
    private Adapter adapter;
    private RecyclerView recyclerView;
    //FireBase
    private DatabaseReference mDatabase;
    private List<ListItem> list_novels = new ArrayList<>();
    private List<String> list_key = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //UI related
        button_add_data = (Button) findViewById(R.id.button_add_data);
        button_add_data.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(list_novels, this);
        recyclerView.setAdapter(adapter);
        adapter.setItemClickCallBack(this);
        //FireBase
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String name = dataSnapshot.child("name").getValue(String.class);
                String progress = dataSnapshot.child("progress").getValue(String.class);
                String other = dataSnapshot.child("other").getValue(String.class);
                String date = dataSnapshot.child("date").getValue(String.class);

                String key = dataSnapshot.getKey();

                ListItem listItem = new ListItem();
                listItem.setName(name);
                listItem.setProgress(progress);
                listItem.setOther(other);
                listItem.setDate(date);

                list_novels.add(listItem);
                list_key.add(key);

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String name = dataSnapshot.child("name").getValue(String.class);
                String progress = dataSnapshot.child("progress").getValue(String.class);
                String other = dataSnapshot.child("other").getValue(String.class);
                String date = dataSnapshot.child("date").getValue(String.class);

                String key = dataSnapshot.getKey();

                ListItem listItem = new ListItem();
                listItem.setName(name);
                listItem.setProgress(progress);
                listItem.setOther(other);
                listItem.setDate(date);

                int index = list_key.indexOf(key);
                list_novels.set(index, listItem);

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                int index = list_key.indexOf(key);
                list_key.remove(index);
                list_novels.remove(index);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_add_data:
                Intent intent = new Intent(this, AddNovelActivity.class);
                startActivity(intent);
                break;

        }
    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, DetailActivity.class);

        ListItem listItem = list_novels.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("Name", listItem.getName());
        bundle.putString("Progress", listItem.getProgress());
        bundle.putString("Other", listItem.getOther());
        bundle.putString("Date", listItem.getDate());
        bundle.putString("Key", list_key.get(position));
        intent.putExtra("Extra", bundle);

        startActivity(intent);

    }
    /*
    protected void onActivityResult(int requestCode, int resultCode, Intent it) {
        adapter = new Adapter(getList(), this);
        recyclerView.setAdapter(adapter);
        adapter.setItemClickCallBack(this);
    }
    */
}
