package com.example.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ImageView imageView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Bitmap bitmap = getPicture("https://cdn-amz.woka.io/images/I/71ZmuGHjn3L.jpg");
                        imageView.post(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(bitmap);
                                textView.setText("Dowload thanh cong");
                            }
                        });
                    }
                });
                thread.start();
            }
        });

    }
    private Bitmap getPicture(String link){
        URL url;
        Bitmap bitmap =null;
        try {
            url = new URL(link); //lay ve duong dan nay
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }
}