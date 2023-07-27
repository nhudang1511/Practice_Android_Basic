package com.example.a20110235_listview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewWithMulti extends AppCompatActivity {

    private static final String TAG = "MutipleActivity";
    private RecyclerView rvMultipleViewType;
    Button btnNext;
    private List<Object> mData;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_with_multi);
        rvMultipleViewType = (RecyclerView) findViewById(R.id.rv_multipe_view_type);
        //Anh Xa
        AnhXa();
        CustomAdapterMulti adapter = new CustomAdapterMulti(this,mData);
        rvMultipleViewType.setAdapter(adapter);
        rvMultipleViewType.setLayoutManager(new LinearLayoutManager(this));
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void AnhXa() {
        mData = new ArrayList<>();
        btnNext = (Button) findViewById(R.id.btnNext);
        mData.add(new UserModel("Nguyen Nhu","Thu Duc"));
        mData.add(R.drawable.c);
        mData.add("Student 2");
        mData.add(new UserModel("Duyen","Quan 9"));
        mData.add("Student 3");
        mData.add(R.drawable.kotlin);
        mData.add(new UserModel("Khiem","Quan 9"));
        mData.add("Student 4");
        mData.add("Student 5");
        mData.add(new UserModel("Giang","Quan 4"));
        mData.add(R.drawable.ic_launcher_foreground);
    }
}