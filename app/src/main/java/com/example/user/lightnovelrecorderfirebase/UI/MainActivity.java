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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Adapter.ItemClickCallBack {

    Button button_add_data;
    Adapter adapter;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button_add_data = (Button) findViewById(R.id.button_add_data);
        button_add_data.setOnClickListener(this);



        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(getList(), this);
        recyclerView.setAdapter(adapter);
        adapter.setItemClickCallBack(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_add_data:
                Intent intent = new Intent(this, AddNovelActivity.class);
                startActivityForResult(intent, 1);
                break;

        }
    }

    //TODO--DELETE
    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }



    public List<ListItem> getList() {


        return new ArrayList<ListItem>();

    }

    @Override
    public void onItemClick(int position) {
        ListItem listItem = getList().get(position);

        Intent intent = new Intent(this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ID", listItem.getId());
        bundle.putString("Name", listItem.getName());
        bundle.putString("Progress", listItem.getProgress());
        bundle.putString("Other", listItem.getOther());
        bundle.putString("Date", listItem.getDate());
        intent.putExtra("Extra", bundle);
        startActivity(intent);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent it) {
        adapter = new Adapter(getList(), this);
        recyclerView.setAdapter(adapter);
        adapter.setItemClickCallBack(this);
    }
}
