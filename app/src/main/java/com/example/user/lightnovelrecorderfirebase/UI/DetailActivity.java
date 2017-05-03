package com.example.user.lightnovelrecorderfirebase.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.user.lightnovelrecorderfirebase.ListItem;
import com.example.user.lightnovelrecorderfirebase.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailActivity extends AppCompatActivity implements View.OnLongClickListener{

    private TextView text_nameDetail,text_progressDetail,text_otherDetail,text_dateDetail;

    //DB
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setTitle("詳情");


        Bundle bundle = getIntent().getBundleExtra("Extra");
        String key = bundle.getString("Key");

        String name = bundle.getString("Name");
        String progress = bundle.getString("Progress");
        String other = bundle.getString("Other");
        String date = bundle.getString("Date");

        text_nameDetail = (TextView) findViewById(R.id.text_nameDetail);
        text_nameDetail.setText(name);
        text_progressDetail = (TextView) findViewById(R.id.text_progressDetail);
        text_progressDetail.setText(progress);
        text_progressDetail.setOnLongClickListener(this);
        text_otherDetail = (TextView) findViewById(R.id.text_otherDetail);
        text_otherDetail.setText(other);
        text_dateDetail = (TextView) findViewById(R.id.text_dateDetail);
        text_dateDetail.setText(date);

        mDatabase = FirebaseDatabase.getInstance().getReference().child(key);
    }

    @Override
    public boolean onLongClick(View v) {

        return false;
    }


}
