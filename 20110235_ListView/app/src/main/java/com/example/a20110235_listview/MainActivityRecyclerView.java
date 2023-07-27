package com.example.a20110235_listview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivityRecyclerView extends AppCompatActivity {

    private RecyclerView rvSongs;
    private SongAdapter mSongAdapter;
    private List<SongModel> mSongs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recycler_view);
        rvSongs = (RecyclerView)findViewById(R.id.rv_songs);

        mSongs = new ArrayList<>();
        mSongs.add(new SongModel("60696","Một Bước Yêu Vạn Dặm Đau","Một thế giới hư ảo, nhưng thật ấm áp","Mr.Siro"));
        mSongs.add(new SongModel("60701","Tình Sầu Thiên Thu Muôn Lối ","Giờ người nơi đâu em hỡi","Doãn Hiếu "));
        mSongs.add(new SongModel("60650","Độc Thân Không Phải Là Ế","Hai mươi mùa thu rồi anh vẫn chưa có người yêu ","Nguyễn Trung Đức"));
        mSongs.add(new SongModel("60610","QUẢ PHỤ TƯỚNG","Trút lớp thanh y khoác lên thân ngọc chiếc chiến bào","REYVIN"));
        mSongs.add(new SongModel("60656","THUYỀN QUYÊN","Xa xa bóng người thương, thấp thoáng trước thềm nhà đang đưa dâu","DIỆU KIÊN"));
        mSongs.add(new SongModel("60752","Lữ Khách Qua Thời Gian ","Đám ma nàng tiền vàng đưa tang.","Tuyên Chính"));
        mSongs.add(new SongModel("60608","ĐẾ VƯƠNG","Một bậc quân vương mang trong con tim hình hài đất nước","ĐÌNH DŨNG"));
        mSongs.add(new SongModel("60603","Lấy Chồng Sớm Làm Gì","Tôi đã yêu em lâu rồi, sao em chẳng nhận ra","Huy R"));
        mSongs.add(new SongModel("60720","Tướng Quân","Ngàn vạn binh đao giương cao hướng về phía trước ta","Nhật Phong"));
        mSongs.add(new SongModel("60856","NẾU EM CÒN TỒN TẠI","Khi anh bắt đầu tình yêu là lúc anh tự thay","Trịnh Đình Quang"));
        mSongAdapter= new SongAdapter(this, mSongs);
        rvSongs.setAdapter(mSongAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvSongs.setLayoutManager(linearLayoutManager);
    }

}