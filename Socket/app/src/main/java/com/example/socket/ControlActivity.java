package com.example.socket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class ControlActivity extends AppCompatActivity {
    ImageButton btnTb1, btnTb2, btnDisc;
    TextView txt1, txtMac;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    Set<BluetoothDevice> pairDevices1;
    String address = null;
    private ProgressDialog progress;
    int flaglamp1;
    int flaglamp2;
    //SPP UUID
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private final String TAG = ControlActivity.class.getName();

    private class ConnectBT extends AsyncTask<Void, Void, Void>{
        private boolean ConnectSuccess = true;
        @Override
        protected void onPreExecute(){
            progress = ProgressDialog.show(ControlActivity.this, "Đang kết nối...", "Xin vui lòng đợi");
        }
        @Override
        protected Void doInBackground(Void... devices){
            try {
                if(btSocket == null || !isBtConnected){
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);
                    if(ActivityCompat.checkSelfPermission(ControlActivity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED){
                        //create a RFCOMM (SPP) connection
                        btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);
                        BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                        btSocket.connect();
                    }
                }
            }catch (IOException e){
                ConnectSuccess = false;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result){ //after the doInBackground, it check if everything is fine
            super.onPostExecute(result);
            if(!ConnectSuccess){
                msg("Kết nối thất bại! Kiểm tra thiết bị.");
                finish();
            }
            else{
                msg("Kết nối thành công");
                isBtConnected = true;
                pairedDevicesList1();
            }
            progress.dismiss();
        }
    }

    private void pairedDevicesList1() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED ){
            pairDevices1 = myBluetooth.getBondedDevices();
            if(pairDevices1.size() > 0){
                for(BluetoothDevice bt: pairDevices1){
                    txtMac.setText(bt.getName() + " - " + bt.getAddress());
                }
            }
            else {
                Toast.makeText(getApplicationContext(), "Không tìm thấy thiết bị kết nối", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void msg(String s){
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        Intent newint = getIntent();
        address = newint.getStringExtra(MainActivity.EXTRA_ADDRESS); //recieve the address of the bluetooth device

        //ánh xạ
        initView();

        new ConnectBT().execute();//call the class to connect

        btnTb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thietbi1();
            }
        });

        btnTb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thietbi7();
            }
        });

        btnDisc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Disconnect();
            }
        });


    }

    private void Disconnect() {
        if(btSocket != null){
            try{
                btSocket.close();
            }
            catch (IOException e){
                msg("Lỗi");
            }
        }
        finish();
    }

    private void thietbi7() {
        if(btSocket != null){
            try{
                if(this.flaglamp2 == 0){
                    this.flaglamp2 = 1;
                    this.btnTb2.setBackgroundResource(R.drawable.btnOn7);
                    btSocket.getOutputStream().write("7".toString().getBytes());
                    txt1.setText("Thiết bị số 7 đang bật");
                    return;
                }
                else{
                    if(this.flaglamp2 != 1) return;
                    {
                        this.flaglamp2 = 0;
                        this.btnTb2.setBackgroundResource(R.drawable.btnTbOff);
                        btSocket.getOutputStream().write("G".toString().getBytes());
                        txt1.setText("Thiết bị số 7 đang tắt");
                        return;
                    }
                }
            }catch (IOException e){
                msg("Lỗi");
            }
        }
    }

    private void thietbi1() {
        if(btSocket != null){
            try{
                if(this.flaglamp1 == 0){
                    this.flaglamp1 = 1;
                    this.btnTb1.setBackgroundResource(R.drawable.btnOn1);
                    btSocket.getOutputStream().write("1".toString().getBytes());
                    txt1.setText("Thiết bị số 1 đang bật");
                    return;
                }
                else{
                    if(this.flaglamp1 != 1) return;
                    {
                        this.flaglamp1 = 0;
                        this.btnTb1.setBackgroundResource(R.drawable.btnTbOff);
                        btSocket.getOutputStream().write("A".toString().getBytes());
                        txt1.setText("Thiết bị số 1 đang tắt");
                        return;
                    }
                }
            }catch (IOException e){
                msg("Lỗi");
            }
        }
    }

    private void initView() {
        btnTb1 = findViewById(R.id.btnTb1);
        btnTb2 = findViewById(R.id.btnTb2);
        btnDisc = findViewById(R.id.btnDisc);
        txt1 = findViewById(R.id.textV1);
        txtMac = findViewById(R.id.textViewMAC);

    }
}