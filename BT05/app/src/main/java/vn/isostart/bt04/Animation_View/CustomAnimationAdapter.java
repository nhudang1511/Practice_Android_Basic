package vn.isostart.bt04.Animation_View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.isostart.bt04.R;

public class CustomAnimationAdapter extends RecyclerView.Adapter<CustomAnimationAdapter.ViewHolder> {
    private List<String> mDatas;

    public CustomAnimationAdapter(List<String> mDatas) {
        this.mDatas = mDatas;
    }
    //tạo ViewHolder
    @Override
    public CustomAnimationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View itemView = li.inflate(R.layout.row_animation,parent,false);
        return new ViewHolder(itemView);
    }
    //Gắn dữ liệu vào ViewHolder
    @Override
    public void onBindViewHolder(CustomAnimationAdapter.ViewHolder holder, int position) {
        String item = mDatas.get(position);
        holder.tvItem.setText(item);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
    //Thêm item vào cuối list
    public void addItem(String item){
        mDatas.add(item);
        notifyItemInserted(mDatas.size()-1);
    }
    //Thêm item vào một vị trí nào đó
    public void addItem(int postition, String item){
        mDatas.add(postition,item);
        notifyItemInserted(postition);
    }
    //Loại bỏ một item tại vị trí hiện tại
    public void removeItem(int position){
        mDatas.remove(position);
        notifyItemRemoved(position);
    }
    //Loại bỏ một item nào đó (không biết vị trí)
    public void removeItem(String item){
        int index = mDatas.indexOf(item);
        if(index < 0){
            return;
        }
        mDatas.remove(index);
        notifyItemRemoved(index);
    }
    //Thay thế một item bằng một item khác tại một vị trí cụ thể nào đó
    public void replaceItem(int position, String item){
        mDatas.remove(position);
        mDatas.add(position, item);
        notifyItemChanged(position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvItem;
        public ViewHolder (final View itemView){
            super(itemView);
            tvItem = (TextView) itemView.findViewById(R.id.tv_item);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    removeItem(getAdapterPosition());
                    Toast.makeText(itemView.getContext(),"Removed Animation",Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    replaceItem(getAdapterPosition(),"item changed");
                    Toast.makeText(itemView.getContext(),"Changed animation", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}