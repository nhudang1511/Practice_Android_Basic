package com.example.sqlite_example;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

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
        createDatabaseSQLite();
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
}