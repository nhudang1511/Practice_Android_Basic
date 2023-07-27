package vn.isostart.bt04.Multiple_View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.isostart.bt04.Animation_View.MainAnimationActivity;
import vn.isostart.bt04.R;

public class MutipleActivity extends AppCompatActivity {

    private static final String TAG = "MutipleActivity";
    private RecyclerView rvMultipleViewType;
    Button btnNext;
    private List<Object> mData;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutiple_main);
        rvMultipleViewType = (RecyclerView) findViewById(R.id.rv_multipe_view_type);
        //Anh Xa
        AnhXa();
        CustomAdapter adapter = new CustomAdapter(this,mData);
        rvMultipleViewType.setAdapter(adapter);
        rvMultipleViewType.setLayoutManager(new LinearLayoutManager(this));
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MutipleActivity.this, MainAnimationActivity.class);
                startActivity(intent);
            }
        });

    }

    private void AnhXa() {
        mData = new ArrayList<>();
        btnNext = (Button) findViewById(R.id.btnNext);
        mData.add(new UserModel("123","Quan 1"));
        mData.add(R.drawable.php);
        mData.add("Text 0");
        mData.add("Text 1");
        mData.add(new UserModel("456","Quan 2"));
        mData.add("Text 2");
        mData.add(R.drawable.c);
        mData.add(R.drawable.java);
        mData.add(new UserModel("789","Quan 3"));
        mData.add("Text 3");
        mData.add("Text 4");
        mData.add(new UserModel("147","Quan 4"));
        mData.add(R.drawable.kotlin);
    }
}
