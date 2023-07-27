package com.example.databinding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SQLite_Example1 extends AppCompatActivity {

    DatabaseHandler databaseHandler;
    ListView listView;
    ArrayList<NotesModel> arrayList;
    NotesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_example1);

        arrayList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView1);
        adapter= new NotesAdapter(this, R.layout.row_danhsach,arrayList);
        listView.setAdapter(adapter);

        //gọi hàm databaseSQLite
        InitDatabaseSQLite();
        //createDatabaseSQLite();
        databaseSQLite();


    }

    private void createDatabaseSQLite(){
        databaseHandler.QuerryData("Insert into Notes Values(null,'Ví dụ về SQLite 1')");
        databaseHandler.QuerryData("Insert into Notes Values(null,'Ví dụ về SQLite 2')");

    }
    private void databaseSQLite() {
        arrayList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView1);
        adapter= new NotesAdapter(this, R.layout.row_danhsach,arrayList);
        listView.setAdapter(adapter);
        try {
            Cursor cursor = databaseHandler.GetData("SELECT * FROM Notes");

            while (cursor.moveToNext()){
                String name = cursor.getString(1);
                int id = cursor.getInt(0);

                NotesModel note = new NotesModel(id,name);
                arrayList.add(note);
                //Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
            }
            adapter.notifyDataSetChanged();
        }
        catch (Exception e){
            Log.d("error", e.toString());
        }
    }

    private void InitDatabaseSQLite() {
        //khởi tạo database
        databaseHandler = new DatabaseHandler(this,"notes.sqlite",null,1);
        //tạo bảng Notes
        databaseHandler.QuerryData("Create table if not exists Notes(Id INTEGER PRIMARY KEY AUTOINCREMENT, NameNotes Varchar(200) )");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

   @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuAddNotes){
            DialogThem();
            //Toast.makeText(SQLite_Example1.this, "Đã thêm Notes", Toast.LENGTH_SHORT).show();

        }

        return super.onOptionsItemSelected(item);
    }

    private void DialogThem() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_notes);

        EditText editText = dialog.findViewById(R.id.editTextName);
        Button buttonAdd = dialog.findViewById(R.id.buttonThem);
        Button buttonHuy = dialog.findViewById(R.id.buttonHuy);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText.getText().toString().trim();
                if(name.equals("")){
                    Toast.makeText(SQLite_Example1.this, "Vui lòng nhập tên Notes", Toast.LENGTH_SHORT).show();
                }
                else {
                    databaseHandler.QuerryData("Insert into Notes Values(null,'"+name+"')");
                    Toast.makeText(SQLite_Example1.this, "Đã thêm Notes", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    databaseSQLite();
                    //adapter.notifyDataSetChanged();
                }
            }
        });
        buttonHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void DialogDelete(String name, final int id){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có muốn xóa Notes"+name+"này không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                databaseHandler.QuerryData("DELETE From Notes where Id='"+id+"'");
                Toast.makeText(SQLite_Example1.this, "Đã xóa Notes"+name+"thành công", Toast.LENGTH_SHORT).show();
                databaseSQLite();
                //adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
    public void DialogCapNhatNote(String name, int id) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_modify_notes);

        EditText editTextName = dialog.findViewById(R.id.editTextName);
        Button btnCapNhat = dialog.findViewById(R.id.buttonCapNhat);
        Button btnHuy = dialog.findViewById(R.id.buttonHuy);

        editTextName.setText(name);
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                databaseHandler.QuerryData("Update Notes SET NameNotes ='"+ name + "' WHERE id = '" + id + "'");
                Toast.makeText(SQLite_Example1.this, "Đã cập nhật Notes thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                databaseSQLite();
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}