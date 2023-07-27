package com.example.a20110235_viewshortwithfirebase;

import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class VideoFireBaseAdapter extends FirebaseRecyclerAdapter<VideoModel, VideoFireBaseAdapter.MyHolder> {

    private boolean isFav = false;

    public VideoFireBaseAdapter(@NonNull FirebaseRecyclerOptions<VideoModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyHolder holder, int position, @NonNull VideoModel model) {
        holder.textViewTitle.setText(model.getTitle());
        holder.textViewDiscription.setText(model.getDesc());
        holder.videoView.setVideoURI(Uri.parse(model.getUrl()));
        holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                holder.videoProgressBar.setVisibility(View.GONE);
                mp.start();
                float videoRatio = mp.getVideoWidth() /(float) mp.getVideoHeight();
                float screenRatio = holder.videoView.getWidth() / (float) holder.videoView.getHeight();
                float scale = videoRatio / screenRatio;
                if(scale >= 1f){
                    holder.videoView.setScaleX(scale);
                }
                else{
                    holder.videoView.setScaleY(1f/scale);
                }
            }
        });

        holder.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });

        holder.favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFav){
                    holder.favorites.setImageResource(R.drawable.ic_fill_favorite);
                    isFav = true;
                }
                else{
                    holder.favorites.setImageResource(R.drawable.ic_favorite);
                    isFav = false;
                }
            }
        });
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_video_row, parent, false);

        return new MyHolder(view);
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        private VideoView videoView;
        private ProgressBar videoProgressBar;
        private TextView textViewTitle;
        private TextView textViewDiscription;
        private ImageView imPerson, favorites, imMore, imShare;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
            videoProgressBar = itemView.findViewById(R.id.videoProgressBar);
            textViewTitle = itemView.findViewById(R.id.textVideoTitle);
            textViewDiscription = itemView.findViewById(R.id.textViewDiscription);
            imPerson = itemView.findViewById(R.id.imPerson);
            favorites = itemView.findViewById(R.id.favorites);
            imMore = itemView.findViewById(R.id.imMore);
            imShare = itemView.findViewById(R.id.imShare);
        }
    }
}
