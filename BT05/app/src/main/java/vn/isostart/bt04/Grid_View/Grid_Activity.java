package vn.isostart.bt04.Grid_View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import vn.isostart.bt04.R;
import vn.isostart.bt04.Recycler_View.SongRecyclerActivity;

public class Grid_Activity extends AppCompatActivity  {
    GridView gridView;
    ArrayList<MonHoc> arrayList;
    MonHocAdapter adapter;

    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.row_monhoc_grid);
        //ánh xạ
        AnhXa();
        //Tạo Adapter
        adapter = new MonHocAdapter(Grid_Activity.this, R.layout.row_monhoc_grid, arrayList);
        //truyền dữ liệu từ adapter ra gridview
        gridView.setAdapter(adapter);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Grid_Activity.this, SongRecyclerActivity.class);
                startActivity(intent);
            }
        });

    }
    private void AnhXa() {
        gridView = (GridView) findViewById(R.id.gridView);
        btnNext = (Button) findViewById(R.id.btnNext);
        //editText1 = (EditText) findViewById(R.id.editText1);
        //btnNhap = (Button) findViewById(R.id.btnNhap);
        //btnCapNhat = (Button) findViewById(R.id.btnCapNhat);
        //Thêm dữ liệu vào List
        arrayList = new ArrayList<>();
        arrayList.add(new MonHoc("Java","Java 1",R.drawable.java));
        arrayList.add(new MonHoc("C#","C# 1",R.drawable.c));
        arrayList.add(new MonHoc("PHP","PHP 1",R.drawable.php));
        arrayList.add(new MonHoc("Kotlin","Kotlin 1",R.drawable.kotlin));
        arrayList.add(new MonHoc("Dart","Dart 1",R.drawable.dart));
    }
}
