package com.example.exercise1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
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
import android.app.ProgressDialog;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.exercise1.api.FoodApi;
import com.example.exercise1.model.Const;
import com.example.exercise1.model.UploadMessage;
import com.example.exercise1.model.User;
import com.example.exercise1.util.RealPathUtil;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateImageActivity extends AppCompatActivity {
    private ImageView imageUpload;
    private static final int MY_REQUEST_CODE = 100;
    private Button btnSelect;
    private Button btnUpload;
    private Button btnReturn;
    private Uri mUri;
    private User user;

    public static final String TAG = MainActivity.class.getName();

    private ProgressDialog mProgressDialog;

    ActivityResultLauncher<Intent> mActivityResultLancher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.e(TAG, "onActivityResult");
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data == null) {
                            return;
                        }
                        Uri uri = data.getData();
                        mUri = uri;
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            imageUpload.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }

            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_image);

        initView();

        getUser();

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait to upload...");



        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckPermissions();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mUri != null){
                    UploadImage();
                }
            }
        });


    }

    private void getUser() {
        Bundle bundleRecieve = getIntent().getExtras();
        if(bundleRecieve != null){
            user = (User) bundleRecieve.get("userObject");
            if(user != null){
                Glide.with(getApplicationContext()).load(user.getImages()).into(imageUpload);
            }
        }
    }

    private void initView() {
        imageUpload = findViewById(R.id.img_update);
        btnSelect = findViewById(R.id.btnChoose);
        btnReturn = findViewById(R.id.btnComeback);
        btnUpload = findViewById(R.id.btnUpload);
    }

    private void UploadImage() {
        mProgressDialog.show();
        String userID = Integer.toString(user.getId());
        RequestBody requestBodyID = RequestBody.create(MediaType.parse("multipart/form-data"), userID);
        String strRealPath = RealPathUtil.getRealPath(this, mUri);
        File file = new File(strRealPath);
        RequestBody requestBodyAvt = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part multipartBodyAvt = MultipartBody.Part.createFormData(Const.MY_IMAGES, file.getName(), requestBodyAvt);
        FoodApi.apiService.uploadImage(requestBodyID, multipartBodyAvt).enqueue(new Callback<UploadMessage>() {
            @Override
            public void onResponse(Call<UploadMessage> call, Response<UploadMessage> response) {
                mProgressDialog.dismiss();
                UploadMessage uploadMessage = response.body();
                Toast.makeText(getApplicationContext(),uploadMessage.getMessage(), Toast.LENGTH_SHORT).show();
                if(uploadMessage != null){
                    Intent intent = new Intent(UpdateImageActivity.this, MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("userObject", user);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<UploadMessage> call, Throwable t) {
                mProgressDialog.dismiss();
                //Toast.makeText(UpdateImageActivity.this, "Error", Toast.LENGTH_SHORT).show();
                Log.e(TAG, t.getMessage());
            }
        });

    }


    public void CheckPermissions(){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            openGallery();
            return;
        }
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            openGallery();
        }
        else{
            String [] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permission, MY_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == MY_REQUEST_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openGallery();
            }
        }

    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLancher.launch(Intent.createChooser(intent, "Select Picture"));
    }

}