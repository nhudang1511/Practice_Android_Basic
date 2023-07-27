package vn.isostart.bt04.Recycler_View;

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

import vn.isostart.bt04.Grid_View.Grid_Activity;
import vn.isostart.bt04.ListView.MainActivity;
import vn.isostart.bt04.Multiple_View.MutipleActivity;
import vn.isostart.bt04.R;
import vn.isostart.bt04.Recycler_View.SongAdapter;
import vn.isostart.bt04.Recycler_View.SongModel;

public class SongRecyclerActivity extends AppCompatActivity {

    private RecyclerView rvSongs;
    private SongAdapter mSongAdapter;
    private List<SongModel> mSongs = new ArrayList<>();
    Button btnNext;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvSongs = (RecyclerView) findViewById(R.id.rv_songs);
        btnNext = (Button) findViewById(R.id.btnNext);

        // Create song data
        mSongs.add(new SongModel("68996", "NẾU EM CÒN TỒN TẠI", "Khi anh bắt đầu 1 tình yêu là lúc anh tự thay", "Trịnh Đình Quang"));
        mSongs.add(new SongModel("68781", "NGỐC", "Có rất nhiều những câu chuyện Em giấu riêng mình em biết", "Khắc Việt"));
        mSongs.add(new SongModel("68650", "HÃY TIN ANH LẦN NỮA", "Dẫu cho ta đã sai khi ở bên nhau Cô yêu thương", "Thiên Dũng"));
        mSongs.add(new SongModel("68610", "CHUỖI NGÀY VẮNG EM", "Từ khi em bước ra đi cõi lòng anh ngập tràng bao", "Duy Cường"));
        mSongs.add(new SongModel("68656", "KHI NGƯỜI YÊU MÌNH KHÓC", "Nước mắt em đang rơi trên những ngón tay Nước mắt em", "Phan Mạnh Quỳnh"));
        mSongs.add(new SongModel("68685", "MỞ", "Anh mơ gặp em anh mơ được ôm anh mơ được gần", "Trịnh Thăng Bình"));
        mSongs.add(new SongModel("68752", "TÌNH YÊU CHẮP VÁ", "Muốn đi xa nơi yêu thương mình từng có Để không nghe", "Mr. Siro"));

        mSongAdapter = new SongAdapter(this, mSongs);
        rvSongs.setAdapter(mSongAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvSongs.setLayoutManager(linearLayoutManager);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SongRecyclerActivity.this, MutipleActivity.class);
                startActivity(intent);
            }
        });
    }
}
