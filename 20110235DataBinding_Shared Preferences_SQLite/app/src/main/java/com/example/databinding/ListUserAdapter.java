package com.example.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databinding.databinding.ItemListUserBinding;

import java.util.List;

public class ListUserAdapter extends RecyclerView.Adapter<ListUserAdapter.MyViewHolder> {
    private List<UserModel> userList;

    private OnItemClickListener onItemClickListener;
    public ListUserAdapter(List<UserModel> userList)
    {
        this.userList = userList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //gắn Data Binding cho ViewHolder. sử dụng DataBindingUtil.inflate() để chuyển đổi layout thành một đối tượng binding
        ItemListUserBinding itemListUserBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_list_user,parent,false);
        return new MyViewHolder(itemListUserBinding,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ListUserAdapter.MyViewHolder holder, int position) {
        //truyền dữ liệu từ userList vào holder
        holder.setBinding(userList.get(position),position);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ObservableField<String> stt = new ObservableField<>();
        public ObservableField<String> firstName = new ObservableField<>();
        public ObservableField<String> lastName = new ObservableField<>();
        public   ItemListUserBinding itemListUserBinding;
        private OnItemClickListener onItemClickListener;
        private UserModel user;

        public MyViewHolder(ItemListUserBinding itemView, OnItemClickListener onItemClickListener){
            super(itemView.getRoot());
            this.itemListUserBinding=itemView;
            this.onItemClickListener = onItemClickListener;
            itemView.getRoot().setOnClickListener(this);
        }
        public void setBinding(UserModel user, int position){
            if(itemListUserBinding.getViewHolder()==null)
            {
                itemListUserBinding.setViewHolder(this);
            }
            this.user = user;
            stt.set(String.valueOf(position));
            firstName.set(user.getFirstName());
            lastName.set(user.getLastName());
        }
        @Override
        public void onClick(View v){
            this.onItemClickListener.itemClick(user);
        }
    }
    public interface OnItemClickListener{
        void itemClick(UserModel user);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
