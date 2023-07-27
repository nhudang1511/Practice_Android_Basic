package com.example.a20110235_nhu_tuan8;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
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
import android.os.MessageQueue;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.a20110235_nhu_tuan8.SharePrefManager;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Upload extends AppCompatActivity {

    Button btnChoose, btnUpload;
    ImageView imageViewUpload, imageViewChoose;

    EditText editTextUserName;

    TextView textViewUserName;
    private Uri mUri;
    //rivate User user;
    private UserLogin userLogin;
    private ImageUpLoad imageUp;
    APIService apiService;
    private ProgressDialog mProgressDialog;
    public static final int MY_REQUEST_CODE = 100;
    public static final String TAG = Upload.class.getName();
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        User user = SharePrefManager.getInstance(this).getUser();
        Integer idd = user.getId();
        id = idd.toString();
        AnhXa();
        mProgressDialog = new ProgressDialog(Upload.this);
        mProgressDialog.setMessage("Please wait...");
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckPermission();
            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUri != null) {
                    UploadImage1();
                }
            }
        });

    }

    private void AnhXa() {
        btnChoose = findViewById(R.id.btnChoose);
        btnUpload = findViewById(R.id.btnUpload);
        imageViewUpload = findViewById(R.id.imgMultipart);
        imageViewChoose = findViewById(R.id.imgChoose);
        editTextUserName = findViewById(R.id.editUserName);
        textViewUserName = findViewById(R.id.textViewUserName);
    }
     public static String[] storge_permissions = {
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            READ_EXTERNAL_STORAGE
    };
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public static String[] storge_permission_33 = {
            android.Manifest.permission.READ_MEDIA_IMAGES,
            android.Manifest.permission.READ_MEDIA_AUDIO,
            android.Manifest.permission.READ_MEDIA_VIDEO
    };

    private static String[] permission() {
        String[] p;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            p = storge_permission_33;
        } else {
            p = storge_permissions;
        }
        return p;
    }

    private void CheckPermission() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            openGallery();
            return;
        }
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openGallery();
            return;
        } else {
            requestPermissions(permission(), MY_REQUEST_CODE);
        }

    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent, "Select Picture"));
    }

    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
                //mActivityResultLauncher.launch(Intent.createChooser(intent,"Select Picture"));
            }
        }
    }

    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
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
                            imageViewUpload.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                }
            }
    );

    public void UploadImage1() {
        // RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),username);
        mProgressDialog.show();
        //String userId = "2";
        //String userId = String.valueOf(SharePrefManager.getInstance(this).getUser().getId());
        RequestBody requestBodyID = RequestBody.create(MediaType.parse("multipart/form-data"), id);
        String strRealPath = RealPathUtil.getRealPath(this, mUri);
        File file = new File(strRealPath);
        RequestBody requestBodyAvt = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part multipartBodyAvt = MultipartBody.Part.createFormData(Constant.MY_IMAGES, file.getName(), requestBodyAvt);
        apiService = RetrofitClient.getRetrofit2().create(APIService.class);
        apiService.updateImages(requestBodyID,multipartBodyAvt).enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                userLogin = response.body();
                Toast.makeText(getApplicationContext(), userLogin.getMessage(), Toast.LENGTH_SHORT).show();
                User user = userLogin.getResult();
                SharePrefManager.getInstance(getApplicationContext()).userLogin(user);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {
                Toast.makeText(Upload.this,"Error", Toast.LENGTH_SHORT).show();
                Log.d("Error API:",t.getMessage());
            }
        });

    }
}