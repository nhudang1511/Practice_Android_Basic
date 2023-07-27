package com.example.foodapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foodapp.File.RealPathUtil;
import com.example.foodapp.api.ApiService;
import com.example.foodapp.api.RetrofitClient;
import com.example.foodapp.model.User;
import com.example.foodapp.model.UserLogin;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Upload_Image_Profile extends AppCompatActivity {

    private static final String TAG = Upload_Image_Profile.class.getName();

    private static final int MY_REQUEST_CODE = 10;

    ApiService apiService;

    UserLogin userLogin;

    ImageView imgAvatar;
    Button btnChoose, btnUpload, btnBack;

    String id;

    Uri uri;

    private ActivityResultLauncher<Intent> mActivityResultLaucher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>(){

                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.e(TAG,"onActivityResult");
                    if(result.getResultCode()== Activity.RESULT_OK){
                        Intent data = result.getData();
                        if(data==null)
                            return;
                        uri = data.getData();
                        try{
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                            imgAvatar.setImageBitmap(bitmap);
                        }
                        catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }

    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image_profile);
        User user = SharedPrefManager.getInstance(Upload_Image_Profile.this).getUser();
        Integer idd = user.getId();
        id = idd.toString();

        Mapping();

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRequestPermission();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImageToProfile();
            }
        });
    }

    private void uploadImageToProfile() {
        String pathFileImg = RealPathUtil.getRealPath(this,uri);
        File file = new File(pathFileImg);
        RequestBody requestBodyImg = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        RequestBody requestBodyId = RequestBody.create(MediaType.parse("multipart/form-data"),id);
        MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("images",file.getName(),requestBodyImg);

        apiService = RetrofitClient.getRetrofit2().create(ApiService.class);
        apiService.updateImages(requestBodyId,multipartBody).enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                userLogin = response.body();
                Toast.makeText(getApplicationContext(), userLogin.getMessage(), Toast.LENGTH_SHORT).show();
                User user = userLogin.getResult();
                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {
//                Toast.makeText(LoginActivity.this,"Error", Toast.LENGTH_SHORT).show();
                Log.d("Error API:",t.getMessage());
            }
        });
    }

    private void onClickRequestPermission() {
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            openGallery();
            return;
        }
        if(checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
        {
            openGallery();
        }
        else {
            String [] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permission,MY_REQUEST_CODE);
        }
    }

    //Hien thi thong bao xin quyen truy cap cua nguoi dung
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==MY_REQUEST_CODE)
        {
            if(grantResults.length>0&& grantResults[0]==PackageManager.PERMISSION_GRANTED){
                openGallery();
            }
        }
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLaucher.launch(Intent.createChooser(intent,"Select Picture"));
    }

    private void Mapping() {
        imgAvatar = findViewById(R.id.img_update);
        btnChoose = findViewById(R.id.btn_choose);
        btnUpload = findViewById(R.id.btn_upload);
        btnBack = findViewById(R.id.btn_back);
    }
}