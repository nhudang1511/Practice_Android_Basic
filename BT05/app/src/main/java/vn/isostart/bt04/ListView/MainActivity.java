package vn.isostart.bt04.ListView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import vn.isostart.bt04.Grid_View.Grid_Activity;
import vn.isostart.bt04.Grid_View.MonHoc;
import vn.isostart.bt04.Grid_View.MonHocAdapter;
import vn.isostart.bt04.R;

public class MainActivity extends AppCompatActivity {
    // Khai báo
    ListView listView;
    ArrayList<MonHoc> arrayList;
    MonHocAdapter adapter;
    Button btnNext ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.row_monhoc);

        //ánh xạ
        AnhXa();
        //Tạo Adapter
        adapter = new MonHocAdapter(MainActivity.this, R.layout.row_monhoc, arrayList);
        //truyền dữ liệu từ adapter ra listview
        listView.setAdapter(adapter);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Grid_Activity.class);
                startActivity(intent);
            }
        });
    }
    private void AnhXa() {
        listView = (ListView) findViewById(R.id.listView);
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