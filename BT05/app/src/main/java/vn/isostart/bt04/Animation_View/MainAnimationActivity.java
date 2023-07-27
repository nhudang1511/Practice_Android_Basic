package vn.isostart.bt04.Animation_View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.isostart.bt04.Grid_View.Grid_Activity;
import vn.isostart.bt04.ListView.MainActivity;
import vn.isostart.bt04.R;


public class MainAnimationActivity extends AppCompatActivity {
    private Button btnAddItem;
    private RecyclerView rvItems;
    Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_main);
        AnhXa();
        List<String> data = new ArrayList<>(); //Tạo data cho adapter
        for (int i = 0; i< 5; i++){
            data.add("item" + i);
        }
        final CustomAnimationAdapter adapter = new CustomAnimationAdapter(data);
        rvItems.setAdapter(adapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));
        //set itemAnimation for RecyclerView
        rvItems.setItemAnimator(new DefaultItemAnimator());
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addItem("new item");
                rvItems.scrollToPosition(adapter.getItemCount() - 1);
            }
        });
    }

    private void AnhXa() {
        btnAddItem = (Button) findViewById(R.id.btn_add_item);
        rvItems = (RecyclerView) findViewById(R.id.rv_items);
        btnNext = (Button) findViewById(R.id.btnNext);
    }
}