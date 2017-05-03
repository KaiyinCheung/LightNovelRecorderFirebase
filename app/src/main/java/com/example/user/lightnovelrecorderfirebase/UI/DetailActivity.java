package com.example.user.lightnovelrecorderfirebase.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.user.lightnovelrecorderfirebase.R;

public class DetailActivity extends AppCompatActivity implements View.OnLongClickListener{

    private TextView text_nameDetail,text_progressDetail,text_otherDetail,text_dateDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setTitle("詳情");


        Bundle bundle = getIntent().getBundleExtra("Extra");

        int id = bundle.getInt("ID");

        text_nameDetail = (TextView) findViewById(R.id.text_nameDetail);
        text_nameDetail.setText(bundle.getString("Name"));
        text_progressDetail = (TextView) findViewById(R.id.text_progressDetail);
        text_progressDetail.setText(bundle.getString("Progress"));
        text_progressDetail.setOnLongClickListener(this);
        text_otherDetail = (TextView) findViewById(R.id.text_otherDetail);
        text_otherDetail.setText(bundle.getString("Other"));
        text_dateDetail = (TextView) findViewById(R.id.text_dateDetail);
        text_dateDetail.setText(bundle.getString("Date"));





    }

    @Override
    public boolean onLongClick(View v) {

        return false;
    }


}
