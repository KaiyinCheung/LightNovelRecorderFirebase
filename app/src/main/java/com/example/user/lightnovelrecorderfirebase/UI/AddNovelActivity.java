package com.example.user.lightnovelrecorderfirebase.UI;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.lightnovelrecorderfirebase.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.HashMap;

public class AddNovelActivity extends AppCompatActivity implements View.OnClickListener, DialogInterface.OnClickListener {
    //UI
    private Button button_confirm, button_cancel;
    private EditText edit_name, edit_progress, edit_other;
    //Firebase
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_novel);

        //UI
        setTitle("新增小說");

        button_confirm = (Button) findViewById(R.id.button_confirm);
        button_cancel = (Button) findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(this);
        button_confirm.setOnClickListener(this);
        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_other = (EditText) findViewById(R.id.edit_other);
        edit_progress = (EditText) findViewById(R.id.edit_progress);
        //DB
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    @Override
    public void onBackPressed() {
        if (edit_other.getText().length() + edit_name.getText().length() + edit_progress.getText().length() > 0) {
            new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_menu_edit)
                    .setMessage("你要離開編輯嗎?")
                    .setPositiveButton("返回編輯頁面", this)
                    .setNegativeButton("離開(不保存)", this)
                    .setCancelable(true)
                    .show();
        } else {
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_cancel:
                if (edit_other.getText().length() + edit_name.getText().length() + edit_progress.getText().length() > 0) {
                    new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_menu_edit)
                            .setMessage("你要離開編輯嗎?")
                            .setPositiveButton("返回編輯頁面", this)
                            .setNegativeButton("離開(不保存)", this)
                            .setCancelable(true)
                            .show();
                } else {
                    finish();
                }
                break;
            case R.id.button_confirm:
                if (edit_name.getText().length() == 0) {
                    Toast.makeText(AddNovelActivity.this, "笨蛋! 你未輸入小說名啊! ", Toast.LENGTH_SHORT).show();
                } else if (edit_progress.getText().length() == 0) {
                    Toast.makeText(AddNovelActivity.this, "笨蛋! 你未輸入進度啊! ", Toast.LENGTH_SHORT).show();
                } else {
                    //put data into database
                    String name = edit_name.getText().toString();
                    String progress = edit_progress.getText().toString();
                    String other = edit_other.getText().toString();
                    String date = new Date().toString();
                    HashMap<String, String> dataMap = new HashMap<String, String>();
                    dataMap.put("name", name);
                    dataMap.put("progress", progress);
                    dataMap.put("other", other);
                    dataMap.put("date", date);

                    mDatabase.push().setValue(dataMap);
                    finish();
                }

                break;
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                return;
            case DialogInterface.BUTTON_NEGATIVE:
                finish();
                return;
        }
    }
}

