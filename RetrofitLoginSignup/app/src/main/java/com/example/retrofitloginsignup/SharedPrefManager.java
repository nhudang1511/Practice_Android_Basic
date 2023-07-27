package com.example.retrofitloginsignup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.retrofitloginsignup.Model.User;

public class SharedPrefManager {
    private  static final  String SHARED_PREF_NAME =  "volleyregisterlogin";
    private  static final  String KEY_USERNAME =  "keyusername";
    private  static final  String KEY_EMAIL =  "keyemail";
    private  static final  String KEY_GENDER =  "keygender";
    private  static final  String KEY_ID =  "keyid";

    private  static final  String KEY_IMAGES =  "keyimages";
    private  static SharedPrefManager mInstance;
    private static Context ctx;

    public SharedPrefManager(Context context) {
        ctx=context;
    }

    public  static synchronized  SharedPrefManager getInstance(Context context){
        if(mInstance==null){
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public void userLogin(User user){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putInt(KEY_ID,user.getId());
        editor.putString(KEY_USERNAME,user.getName());
        editor.putString(KEY_EMAIL,user.getEmail());
        editor.putString(KEY_GENDER,user.getGender());
        if(user.getImages().isEmpty() || user.getImages()==null)
            user.setImages("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSODfgXVmuk7-PD1wdcMoU4IvvUZhlu7fiU1w&usqp=CAU");
        editor.putString(KEY_IMAGES,user.getImages());
        editor.apply();
    }

    public void userSignUp(User user){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putInt(KEY_ID,user.getId());
        editor.putString(KEY_USERNAME,user.getName());
        editor.putString(KEY_EMAIL,user.getEmail());
        editor.putString(KEY_GENDER,user.getGender());
        user.setImages("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSODfgXVmuk7-PD1wdcMoU4IvvUZhlu7fiU1w&usqp=CAU");
        editor.putString(KEY_IMAGES,user.getImages());
        editor.apply();
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME,null)!=null;
    }

    public  User getUser(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return  new User(
                sharedPreferences.getInt(KEY_ID,-1),
        sharedPreferences.getString(KEY_USERNAME,null),
        sharedPreferences.getString(KEY_EMAIL,null),
        sharedPreferences.getString(KEY_GENDER,null),
        sharedPreferences.getString(KEY_IMAGES,null)
        );
    }

    public void  logout(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.clear();
        editor.apply();
        ctx.startActivity(new Intent(ctx, LoginActivity.class));
    }


}
