package com.example.databinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.databinding.databinding.ActivityDataBindingInRecycleViewBinding;
import com.example.databinding.databinding.ActivityDataBindingInRecycleViewBindingImpl;
import com.example.databinding.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class DataBinding_in_RecycleView extends AppCompatActivity {

    public ObservableField<String> title = new ObservableField<>();
    private ListUserAdapter listUserAdapter;

    //xml đã khai báo là sd databinding lên tạo ra binding có kiểu
    private ActivityDataBindingInRecycleViewBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // sử dụng đối tượng binding để truy cập các view trong layout
        binding = DataBindingUtil.setContentView(this,R.layout.activity_data_binding_in_recycle_view);
        title.set("Ví dụ về DataBinding cho RecycleView");

        //đặt activity hiện tại làm nguồn dữ liệu (data source) cho Data Binding
        binding.setHome(this);
        setData();
        binding.rcView.setLayoutManager(new LinearLayoutManager(this));
        binding.rcView.setAdapter(listUserAdapter);
        //listUserAdapter.setOnItemClickListener(this);
    }
    private void setData(){
        List<UserModel> userList = new ArrayList<>();
        for(int i =0;i<20;i++)
        {
            UserModel user = new UserModel();
            user.setFirstName("Nguyễn"+i);
            user.setLastName("Như"+i);
            userList.add(user);
        }
        listUserAdapter = new ListUserAdapter(userList);
    }


    public void itemClick(User user){
        Toast.makeText(this, "bạn vừa click", Toast.LENGTH_SHORT).show();
    }
}