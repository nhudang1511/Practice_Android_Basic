package com.example.sqlite;

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

public class MainActivity extends AppCompatActivity {

    DatabaseHandler databaseHandler;
    ListView listView;
    ArrayList<NotesModel> arrayList;
    NotesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView1);
        adapter= new NotesAdapter(this, R.layout.row_notes,arrayList);
        listView.setAdapter(adapter);
        InitDatabaseSQLite();
//        createDatabaseSQLite();
        databaseSQLite();
    }
    private void createDatabaseSQLite(){
        databaseHandler.QueryData("INSERT INTO Notes VALUES(null,' Ví dụ SQLite 1')");
        databaseHandler.QueryData("INSERT INTO Notes VALUES(null,' Ví dụ SQLite 2')");
    }
    private void InitDatabaseSQLite(){
        databaseHandler = new DatabaseHandler(this, "notes.sqlite", null, 1);
        databaseHandler.QueryData("CREATE TABLE IF NOT EXISTS Notes(Id INTEGER PRIMARY KEY AUTOINCREMENT, NameNotes VARCHAR(200))");
    }
    private void databaseSQLite(){
        arrayList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView1);
        adapter= new NotesAdapter(this, R.layout.row_notes,arrayList);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuAddNotes){
            DialogThem();
        }
        return super.onOptionsItemSelected(item);
    }

    private void DialogThem(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_add);

        EditText editTextName = dialog.findViewById(R.id.editTextName);
        Button btnThem = dialog.findViewById(R.id.btnThem);
        Button btnHuy = dialog.findViewById(R.id.btnHuy);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                if (name.equals("")) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập tên Notes", Toast.LENGTH_SHORT).show();
                } else  {
                    databaseHandler.QueryData("INSERT INTO Notes VALUES(null, '" + name + "')");
                    Toast.makeText(MainActivity.this, "Đã thêm Notes", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    databaseSQLite();
                }
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

    public void DialogCapNhatNote(String name, int id) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_update);

        EditText editTextName = dialog.findViewById(R.id.editTextName);
        Button btnCapNhat = dialog.findViewById(R.id.btnCapNhat);
        Button btnHuy = dialog.findViewById(R.id.btnHuy);

        editTextName.setText(name);
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                databaseHandler.QueryData("Update Notes SET NameNotes ='"+ name + "' WHERE id = '" + id + "'");
                Toast.makeText(MainActivity.this, "Đã cập nhật Notes thành công", Toast.LENGTH_SHORT).show();
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

    public void DialogDelete(String name, final int id) {
        AlertDialog.Builder builder = new  AlertDialog.Builder(this);
        builder.setMessage("Bạn có muốn xóa Notes " + name + " này không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                databaseHandler.QueryData("DELETE FROM Notes WHERE Id = '" + id +"'");
                Toast.makeText(MainActivity.this, "Đã xóa Notes " + name + " thành công", Toast.LENGTH_SHORT).show();
                databaseSQLite();
            }
        });
        builder.show();
    }
}
