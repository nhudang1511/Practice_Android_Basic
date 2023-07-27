package com.example.a20110235_foodapps;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
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

import com.bumptech.glide.Glide;
import com.example.a20110235_foodapps.api.APIService;
import com.example.a20110235_foodapps.model.User;
import com.example.a20110235_foodapps.model.UserLogin;
import com.example.a20110235_foodapps.sharedpreference.SharePrefManager;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadImage extends AppCompatActivity {

    ImageView imgProfile;
    Button btnChoose,btnUpload;

    UserLogin userLogin;
    private static final int MY_REQUEST_CODE = 100;
    private static final String TAG = UploadImage.class.getName();
    Uri uri;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);
        AnhXa();
        if(SharePrefManager.getInstance(this).isLoggedIn()){
            User user = SharePrefManager.getInstance(this).getUser();
            Glide.with(this).load(user.getImages()).into(imgProfile);
            id = String.valueOf(user.getId());
            btnChoose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickRequestPermission();
                }
            });
            btnUpload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    uploadImageonProfile();
                }
            });
        }
    }
    public void AnhXa(){
        imgProfile= findViewById(R.id.img_update);
        btnChoose=findViewById(R.id.btn_choose);
        btnUpload=findViewById(R.id.btn_upload);
    }
    public void onClickRequestPermission(){
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
    public void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLaucher.launch(Intent.createChooser(intent,"Select Picture"));
    }
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
                            imgProfile.setImageBitmap(bitmap);
                        }
                        catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }

    );
    public void uploadImageonProfile(){
        String pathFileImg = RealPathUtil.getRealPath(this,uri);
        File file = new File(pathFileImg);
        RequestBody requestBodyImg = RequestBody.create(MediaType.parse("multipart/form-data"),file);

        RequestBody requestBodyId = RequestBody.create(MediaType.parse("multipart/form-data"),id);

        MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("images",file.getName(),requestBodyImg);


        APIService.apiservice.upload1(requestBodyId,multipartBody).enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                userLogin = response.body();
                Toast.makeText(getApplicationContext(), userLogin.getMessage(), Toast.LENGTH_SHORT).show();
                User user = userLogin.getResult();
                SharePrefManager.getInstance(getApplicationContext()).userLogin(user);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(UploadImage.this,"Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {
                Toast.makeText(UploadImage.this,"Error", Toast.LENGTH_SHORT).show();
            }
        });




    }
}