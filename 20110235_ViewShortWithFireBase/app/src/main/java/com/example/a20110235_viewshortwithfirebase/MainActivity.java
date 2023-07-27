package com.example.a20110235_viewshortwithfirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private VideoFireBaseAdapter videoFireBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        viewPager2 = findViewById(R.id.vpager);
        getVideos();
    }
    private void getVideos() {
        /*Set database*/
        DatabaseReference mDataBase = FirebaseDatabase.getInstance().getReference("videos");
        FirebaseRecyclerOptions<VideoModel> options = new FirebaseRecyclerOptions.Builder<VideoModel>().setQuery(mDataBase, VideoModel.class).build();

        /*Set Adapter*/
        videoFireBaseAdapter = new VideoFireBaseAdapter(options);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        viewPager2.setAdapter(videoFireBaseAdapter);

    }

    @Override
    protected void onStart(){
        super.onStart();
        videoFireBaseAdapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        videoFireBaseAdapter.stopListening();
    }

    @Override
    protected void onResume(){
        super.onResume();
        videoFireBaseAdapter.notifyDataSetChanged();
    }
}