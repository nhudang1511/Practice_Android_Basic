package vn.isostart.bt04.Multiple_View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.isostart.bt04.R;

public class CustomAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Object> mObjects;
    public static final int TEXT = 0;
    public static final int IMAGE = 1;
    public static final int USER = 2;

    public CustomAdapter(Context mContext, List<Object> mObjects) {
        this.mContext = mContext;
        this.mObjects = mObjects;
    }
    //Tao 3 lớp viewHolder
    public class TextViewHolder extends RecyclerView.ViewHolder{
        private TextView tvText;
        public TextViewHolder(View itemView){
            super(itemView);
            tvText = (TextView) itemView.findViewById(R.id.tv_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,mObjects.get(getAdapterPosition()).toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public class ImageViewHolder extends RecyclerView.ViewHolder{
        private ImageView imvImage;
        public ImageViewHolder(View itemView){
            super(itemView);
            imvImage = (ImageView) itemView.findViewById(R.id.imv_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,mObjects.get(getAdapterPosition()).toString(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvAddress;
        public UserViewHolder(View itemView){
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvAddress = (TextView) itemView.findViewById(R.id.tv_address);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserModel user = (UserModel) mObjects.get(getAdapterPosition());
                    Toast.makeText(mContext,user.getName() + ", " + user.getAddress(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    //Đè lại phương thức getItemViewType
    @Override
    public int getItemViewType(int position){
        if (mObjects.get(position) instanceof String)
            return TEXT;
        else if (mObjects.get(position) instanceof Integer)
            return IMAGE;
        else if (mObjects.get(position) instanceof UserModel)
            return USER;
        return -1;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(mContext);
        switch (viewType){
            case TEXT:
                View itemView0 = li.inflate(R.layout.row_text, parent, false);
                return new TextViewHolder(itemView0);
            case IMAGE:
                View itemView1 = li.inflate(R.layout.row_image,parent,false);
                return new ImageViewHolder(itemView1);
            case USER:
                View itemView2 = li.inflate(R.layout.row_user,parent,false);
                return new UserViewHolder(itemView2);
            default:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch(getItemViewType(position)){
            case TEXT:
                TextViewHolder textViewHolder = (TextViewHolder) holder;
                textViewHolder.tvText.setText(mObjects.get(position).toString());
                break;
            case USER:
                UserModel user = (UserModel) mObjects.get(position);
                UserViewHolder userViewHolder = (UserViewHolder) holder;
                userViewHolder.tvName.setText(user.getName());
                userViewHolder.tvAddress.setText(user.getAddress());
                break;
            case IMAGE:
                ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
                imageViewHolder.imvImage.setImageResource((int) mObjects.get(position));
                break;

        }
    }

    @Override
    public int getItemCount() {
        return mObjects.size();
    }



}