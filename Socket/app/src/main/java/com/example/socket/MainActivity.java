package com.example.socket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    Button btnPaired;
    ListView listDanhSach;
    public static int REQUEST_BLUETOOTH = 1;

    //Bluetooth
    private BluetoothAdapter myBluetooth = null;
    private Set<BluetoothDevice> pairedDevices;
    public static String EXTRA_ADDRESS = "device_address";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ánh xạ
        btnPaired = findViewById(R.id.btnTimthietbi);
        listDanhSach = findViewById(R.id.listTb);
        //Kiểm tra thiết bị có bluetooth
        myBluetooth = BluetoothAdapter.getDefaultAdapter();
        if(myBluetooth == null){
            Toast.makeText(this, "Thiết bị Bluetooth chưa bật", Toast.LENGTH_SHORT).show();
            //finish apk
            finish();
        } else if (!myBluetooth.isEnabled()) {
            //Yêu cầu User bật bluetooth
            Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Thiết bị Bluetooth chưa bật", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, "Thiết bị Bluetooth đã bật", Toast.LENGTH_SHORT).show();
            //startForResult
            startActivityForResult(turnBTon, REQUEST_BLUETOOTH);
        }
        //Kết thúc kiểm tra thiết bị có bluetooth
        //Thực hiện tìm thiết bị
        btnPaired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pairDivicesList();
            }
        });
    }

    private AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            //Get the device MAC address, the last 17 chars in the View
            String info = ((TextView) view).getText().toString();
            String address = info.substring(info.length()-17);

            //Make an intent to start next activity
            Intent intent = new Intent(MainActivity.this, ControlActivity.class);
            //Change the activity
            intent.putExtra(EXTRA_ADDRESS, address);
            startActivity(intent);
        }
    };
    private void pairDivicesList() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT)!= PackageManager.PERMISSION_GRANTED){
            pairedDevices = myBluetooth.getBondedDevices();
            ArrayList list = new ArrayList();
            if(pairedDevices.size()>0){
                for(BluetoothDevice  bt : pairedDevices){
                    if(ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "Danh sách thiết bị bluetooth đã bật", Toast.LENGTH_SHORT).show();
                        list.add(bt.getName() + "\n"+bt.getAddress());
                    }
                }
            }
            else{
                Toast.makeText(this, "Không tìm thấy thiết bị kết nối", Toast.LENGTH_SHORT).show();
            }
            final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, list);
            listDanhSach.setAdapter(adapter);
            listDanhSach.setOnItemClickListener(myListClickListener);
            return;
        }


    }
}