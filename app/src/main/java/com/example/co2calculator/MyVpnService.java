package com.example.co2calculator;

import android.content.Intent;
import android.net.VpnService;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import java.io.IOException;

public class MyVpnService extends VpnService implements Runnable {

    private static final String TAG = "MyVpnService";
    private Thread thread;
    private ParcelFileDescriptor vpnInterface;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (thread != null) {
            return START_STICKY;
        }
        thread = new Thread(this, "MyVpnServiceThread");
        thread.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (vpnInterface != null) {
            try {
                vpnInterface.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (thread != null) {
            thread.interrupt();
        }
    }

    @Override
    public void run() {
        try {
            vpnInterface = establishVpn();
            monitorTraffic(vpnInterface);
        } catch (Exception e) {
            Log.e(TAG, "VPN error: " + e.getMessage());
        }
    }

    private ParcelFileDescriptor establishVpn() {
        Builder builder = new Builder();
        builder.addAddress("10.0.0.2", 24);
        builder.addRoute("0.0.0.0", 0);
        return builder.establish();
    }

    private void monitorTraffic(ParcelFileDescriptor vpnInterface) {
        // Logica per monitorare il traffico e filtrare le richieste HTTPS
        // Esegui l'analisi dei pacchetti per determinare le richieste HTTPS in uscita
        // Invia i dati di consumo al ViewModel o ad un observer
    }
}