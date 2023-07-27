package com.example.a20110235_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> arrayList;
    int vitri=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //khởi tạo list view
        Button btnNhap = (Button) findViewById(R.id.btnNhap);
        EditText editText = (EditText) findViewById(R.id.editText1);
        listView = (ListView) findViewById(R.id.listview1);
        Button btnCapnhat = (Button) findViewById(R.id.btnCapnhat);

        arrayList=new ArrayList<>();
        arrayList.add("Java");
        arrayList.add("C#");
        arrayList.add("PHP");
        arrayList.add("Kotlin");
        arrayList.add("Dart");

        ArrayAdapter adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);

        //bắt sự kiện trong list view

        //click nhanh
        String item;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, ""+i, Toast.LENGTH_SHORT).show();
                //lấy dữ liệu đưa lên edit text
                editText.setText(arrayList.get(i));
                vitri=i;

            }
        });

        //nhấn giữ
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Bạn đang nhấn giữ" +i+"-"+arrayList.get(i), Toast.LENGTH_SHORT).show();
                //Xóa item
                arrayList.remove(i);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        //them du lieu vao list view


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