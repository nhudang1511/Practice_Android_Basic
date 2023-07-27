package com.example.retrofitloginsignup;

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

import com.bumptech.glide.Glide;
import com.example.retrofitloginsignup.API.APIService;
import com.example.retrofitloginsignup.API.RetrofitClient;
import com.example.retrofitloginsignup.File.RealPathUtil;
import com.example.retrofitloginsignup.Model.User;
import com.example.retrofitloginsignup.Model.UserLogin;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateImage extends AppCompatActivity {
    Button btnFromFile;
    Button btnUpload;
    ImageView imgUpload;

    String id;
    Uri uri;

    ProgressDialog progressDialog;
    APIService apiService;

    UserLogin userLoginParam;
    private static final int MY_REQUEST_CODE = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_image);
        User user = SharedPrefManager.getInstance(this).getUser();
        Integer idd = user.getId();
        id = idd.toString();
        //Mapping
        btnFromFile = findViewById(R.id.btnGllay);
        imgUpload = findViewById(R.id.imgUploadProfile);
        btnUpload = findViewById(R.id.btnUpload);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait ...");

        Glide.with(getApplicationContext()).load(user.getImages()).into(imgUpload);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImageToProfile();
            }
        });
        btnFromFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRequestPermission();
            }
        });
    }

    private void uploadImageToProfile() {
        progressDialog.show();

        String pathFileImg = RealPathUtil.getRealPath(this,uri);
        File file = new File(pathFileImg);
        RequestBody requestBodyImg = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        RequestBody requestBodyId = RequestBody.create(MediaType.parse("multipart/form-data"),id);
        MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("images",file.getName(),requestBodyImg);

        apiService = RetrofitClient.getAppfood().create(APIService.class);
        apiService.updateImages(requestBodyId,multipartBody).enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                progressDialog.dismiss();
                userLoginParam = response.body();

                Toast.makeText(getApplicationContext(), userLoginParam.getMessage(), Toast.LENGTH_SHORT).show();
                User user = userLoginParam.getResult();
                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();

            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {
//                Toast.makeText(LoginActivity.this,"Error", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                Log.d("Error API:",t.getMessage());
            }
        });
    }
    //    Xu ly upload images

    private ActivityResultLauncher<Intent> mActivityResultLaucher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>(){

                @Override
                public void onActivityResult(ActivityResult result) {
                     Log.e("Tag","onActivityResult");
                     if(result.getResultCode()== Activity.RESULT_OK){
                         Intent data = result.getData();
                         if(data==null)
                             return;
                         uri = data.getData();
                         try{
                             Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                             imgUpload.setImageBitmap(bitmap);
                         }
                         catch (IOException e){
                             e.printStackTrace();
                         }
                     }
                }
            }

    );

    public void onClickRequestPermission(){
//        > android 6 ms check
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            openGallery();
            return;
        }
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
        {
            openGallery();
        }
        else {
            String [] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permission,MY_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==MY_REQUEST_CODE)
        {
            //Nguoi dung cho phep su dung
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


}