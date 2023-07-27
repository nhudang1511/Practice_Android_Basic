package com.example.a20110235_listview;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivityGridView extends AppCompatActivity {

    //khai báo
    GridView gridView;
    ArrayList<String> arrayList;
    int vitri=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_grid_view);
        Button btnNhap = (Button) findViewById(R.id.btnNhap);
        EditText editText = (EditText) findViewById(R.id.editText1);
        Button btnCapnhat = (Button) findViewById(R.id.btnCapnhat);

        //ánh xạ
        gridView = (GridView) findViewById(R.id.gridview1);
        //Thêm dữ liệu vào List
        arrayList = new ArrayList<>();
        arrayList.add("Java");
        arrayList.add("C#");
        arrayList.add("PHP");
        arrayList.add("Kotlin");
        arrayList.add("Dart");

        //Tạo ArrayAdapter
        ArrayAdapter adapter = new ArrayAdapter(MainActivityGridView.this,
                android.R.layout.simple_list_item_1,
                arrayList
        );

        gridView.setAdapter(adapter);

        //bat su kien
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivityGridView.this,"" + i + "-" +arrayList.get(i), Toast.LENGTH_SHORT).show();
                editText.setText(arrayList.get(i));
                vitri=i;
            }
        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivityGridView.this,"Bạn đang nhấn giữ" + i + arrayList.get(i),
                        Toast.LENGTH_SHORT).show();
                arrayList.remove(i);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText.getText().toString();
                arrayList.add(name);
                adapter.notifyDataSetChanged();
                editText.setText("");
            }
        });
        btnCapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.set(vitri, editText.getText().toString());
                adapter.notifyDataSetChanged();
                editText.setText("");
            }
        });

    }
}