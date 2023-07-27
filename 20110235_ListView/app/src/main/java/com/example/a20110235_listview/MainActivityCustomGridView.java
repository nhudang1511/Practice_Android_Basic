package com.example.a20110235_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivityCustomGridView extends AppCompatActivity {

    GridView gridView;
    ArrayList<MonHoc> arrayList;
    MonhocAdapter adapter;
    EditText editText1;
    Button btnNhap;
    Button btnCapNhat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_custom_grid_view);
        AnhXa();
        adapter = new MonhocAdapter(MainActivityCustomGridView.this, R.layout.row_monhoc_grid, arrayList);
        gridView.setAdapter(adapter);
    }

    private void AnhXa() {
        gridView = (GridView) findViewById(R.id.gridview1);
        editText1 = (EditText) findViewById(R.id.editText1);
        btnNhap = (Button) findViewById(R.id.btnNhap);
        btnCapNhat = (Button) findViewById(R.id.btnCapnhat);
        //Thêm dữ liệu vào List
        arrayList = new ArrayList<>();
        arrayList.add(new MonHoc("Java","Java 1",R.drawable.java1));
        arrayList.add(new MonHoc("C#","C# 1",R.drawable.c));
        arrayList.add(new MonHoc("PHP","PHP 1",R.drawable.php));
        arrayList.add(new MonHoc("Kotlin","Kotlin 1",R.drawable.kotlin));
        arrayList.add(new MonHoc("Dart","Dart 1",R.drawable.dart));
    }
}