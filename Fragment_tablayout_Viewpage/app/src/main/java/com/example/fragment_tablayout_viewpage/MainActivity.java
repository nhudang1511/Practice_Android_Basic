package com.example.fragment_tablayout_viewpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.example.fragment_tablayout_viewpage.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ViewPager2Adapter viewPager2Adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //viewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //toolbar
        setSupportActionBar(binding.toolBar);

        FloatingActionButton fab =  binding.fabAction;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();

            }
        });

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Xác nhân"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Lấy hàng"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Đang giao"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Đánh giá"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Hủy"));

        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager2Adapter = new ViewPager2Adapter(fragmentManager, getLifecycle());
        binding.viewPager2.setAdapter(viewPager2Adapter);

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager2.setCurrentItem(tab.getPosition());
                changeFabIcon(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        binding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position));
            }
        });

    }

    private void changeFabIcon(final int index) {
        binding.fabAction.hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (index){
                    case 0: binding.fabAction.setImageDrawable(getDrawable(R.drawable.ic_baseline_chat24)); break;
                    case 1: binding.fabAction.setImageDrawable(getDrawable(R.drawable.ic_baseline_camera_alt_24)); break;
                    case 2: binding.fabAction.setImageDrawable(getDrawable(R.drawable.ic_baseline_call_24)); break;
                }
                binding.fabAction.show();
            }
        }, 2000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();
        switch (id){
            case R.id.menuSearch:
                Toast.makeText(this, "Bạn đang chọn Search", Toast.LENGTH_SHORT).show(); break;
            case R.id.menuNewGroup:
                Toast.makeText(this, "Bạn đang chọn More", Toast.LENGTH_SHORT).show(); break;
            case R.id.menuBroadcast:
                Toast.makeText(this, "Bạn đang chọn More", Toast.LENGTH_SHORT).show(); break;
            case R.id.menuWeb:
                Toast.makeText(this, "Bạn đang chọn More", Toast.LENGTH_SHORT).show(); break;
            case R.id.menuMessages:
                Toast.makeText(this, "Bạn đang chọn More", Toast.LENGTH_SHORT).show(); break;
            case R.id.menuSetting:
                Toast.makeText(this, "Bạn đang chọn Setting", Toast.LENGTH_SHORT).show(); break;
        }
        return super.onOptionsItemSelected(item);
    }
}