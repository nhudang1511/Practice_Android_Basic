package com.example.a20110235_socket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

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

    Button btnParied;
    ListView listDanhSach;
    public static int REQUEST_BLUETOOTH=1;
    private BluetoothAdapter myBluetooth=null;
    private Set<BluetoothDevice> pairedDevices;
    public static String EXTRA_ADDRESS="device_address";
    private AdapterView.OnItemClickListener myListClickListner = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            //Get the device MAC address, the last 17 chars in the View
            String info = ((TextView) view).getText().toString();
            String address = info.substring(info.length()-17);

            //Make an intent to start next activity
            Intent intent = new Intent(MainActivity.this, control.class);
            //Change the activity
            intent.putExtra(EXTRA_ADDRESS, address);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnParied=(Button) findViewById(R.id.btnTimthietbi);
        listDanhSach=(ListView) findViewById(R.id.listTb);
        myBluetooth=BluetoothAdapter.getDefaultAdapter();
        if(myBluetooth==null){
            Toast.makeText(getApplicationContext(), "Thiet bi Bluetooth chua bat", Toast.LENGTH_SHORT).show();
            finish();
        } else if (!myBluetooth.isEnabled()) {
            Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            if(ActivityCompat.checkSelfPermission(this,
                   android.Manifest.permission.BLUETOOTH_CONNECT)!= PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getApplicationContext(), "Thiet bi Bluetooth chua bat", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(getApplicationContext(), "Thiet bi Bluetooth da bat", Toast.LENGTH_SHORT).show();
            startActivityForResult(turnBTon,REQUEST_BLUETOOTH);
        }
        btnParied.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pairedDevicesList();
            }
        });
        }

        public void  pairedDevicesList(){
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT)!=PackageManager.PERMISSION_GRANTED){
            pairedDevices=myBluetooth.getBondedDevices();
            ArrayList list = new ArrayList();
            if(pairedDevices.size()>0){
                for(BluetoothDevice bt :pairedDevices){
                    if(ActivityCompat.checkSelfPermission(this,
                            android.Manifest.permission.BLUETOOTH_CONNECT)!=PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(getApplicationContext(), "Danh sach thiet bi Bluetooth da bat", Toast.LENGTH_SHORT).show();
                        list.add(bt.getName()+"\n"+bt.getAddress());
                    }
                }
            }
            else {
                Toast.makeText(getApplicationContext(), "Khong tim thay thiet bi ket noi", Toast.LENGTH_SHORT).show();
            }

            final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_activated_1,list);
            listDanhSach.setAdapter(adapter);
            listDanhSach.setOnItemClickListener(myListClickListner);
            return;
        }


    }


}