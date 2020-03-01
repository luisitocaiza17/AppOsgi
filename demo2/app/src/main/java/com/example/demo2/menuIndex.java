package com.example.demo2;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.*;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class menuIndex extends AppCompatActivity {
    private static final int REQUEST_ENABLE_BT = 1;
    Button cerrarBtn, encenderBtn;
    BluetoothAdapter myBluetooth;
    private Set pairedDevices;
    BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_index);
        cerrarBtn=(Button)findViewById(R.id.cerrarApp);
        encenderBtn=(Button)findViewById(R.id.encender);
        cerrarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        encenderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getBlu();
                connect();
            }
        });
    }

    public void getBlu(){
          mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
       try{
        if (!mBluetoothAdapter.isEnabled()) {
            // El Bluetooth está apagado, solicitamos permiso al usuario para iniciarlo
            Toast.makeText(menuIndex.this,"Blueto apagado",Toast.LENGTH_SHORT).show();
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            // REQUEST_ENABLE_BT es un valor entero que vale 1
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            // Se registra el broadcast receiver

        }else{
            IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            registerReceiver(mReceiver, filter);
            if (mBluetoothAdapter.isDiscovering()) {
                // El Bluetooth ya está en modo discover, lo cancelamos para iniciarlo de nuevo
                mBluetoothAdapter.cancelDiscovery();
            }
            mBluetoothAdapter.startDiscovery();
        }
       }catch(Exception e){
           Toast.makeText(menuIndex.this,"encendido",Toast.LENGTH_SHORT).show();

       }
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Se ha encontrado un dispositivo Bluetooth
                // Se obtiene la información del dispositivo del intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Toast.makeText(menuIndex.this,"Dispositivo encontrado:"+device.getName() + "; MAC " + device.getAddress(),Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void start(BundleContext context)throws Exception{
        myBluetooth = BluetoothAdapter.getDefaultAdapter();
        Toast.makeText(menuIndex.this,"Hola",Toast.LENGTH_SHORT).show();

    }

    private BluetoothDevice mBluetoothDevice;
    private BluetoothSocket mBluetoothSocket;
    private UUID uuid = UUID.fromString("E0CVF06C-CD8B-4647-BB8A-263B43F0F974");
    public void connect(){

        try {
            mBluetoothSocket = mBluetoothDevice.createRfcommSocketToServiceRecord(uuid);
            mBluetoothSocket.connect();
            Toast.makeText(menuIndex.this,"Conectado",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(menuIndex.this,"No conectado",Toast.LENGTH_SHORT).show();
        }
    }

}
