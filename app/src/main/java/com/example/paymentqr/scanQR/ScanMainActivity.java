package com.example.paymentqr.scanQR;

import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paymentqr.MainTabActivity;
import com.example.paymentqr.R;
import com.example.paymentqr.TransactionDetail;
import com.example.paymentqr.createQR.CreatePayment;
import com.example.paymentqr.createQR.GenerateCodeQR;

import java.util.ArrayList;

import eu.livotov.zxscan.ScannerView;


public class ScanMainActivity extends ActivityGroup
{
    public static ScanMainActivity self;
    private ArrayList<View> history;

    TextView datosCodigo;
    Button btnContinuar;
    String resultadoScanner=null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_activity_main);

        this.history = new ArrayList<View>();
        self = this;

        btnContinuar = (Button) findViewById(R.id.btnContinuar);
        datosCodigo = (TextView) findViewById(R.id.datosCodigo);
        final ScannerView scanner = (ScannerView) findViewById(R.id.scanner);
        datosCodigo.sette

        scanner.setScannerViewEventListener(new ScannerView.ScannerViewEventListener() {
            @Override
            public void onScannerReady() {
                displayScannedResult("Buscando código QR");
            }

            @Override
            public void onScannerFailure(int i) {
                displayScannedResult("onScannerFailure");

            }

            public boolean onCodeScanned(final String data) {
                scanner.stopScanner();

                if (data != null) {
                    TelephonyManager  tm=(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                    String IMEINumber=tm.getDeviceId();

                    resultadoScanner=data;
                    datosCodigo.setText(IMEINumber+" "+data);
                    btnContinuar.setVisibility(View.VISIBLE);
                    displayScannedResult("Código leido exitasamente");
                }
                return true;
            }
        });

        scanner.startScanner();

    }

    private void displayScannedResult(final String data)
    {
        Toast.makeText(ScanMainActivity.this, data, Toast.LENGTH_SHORT).show();

    }

    public void btnContinuar(View v) {
        Toast.makeText(ScanMainActivity.this, "Código exitoso", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(ScanMainActivity.this, TransactionDetail.class);
        Bundle b = new Bundle();
        b.putString("resultadoScanner", "resultadoScanner");

        intent.putExtras(b);
        replaceContentView("ScanMainActivity", intent);
    }

    public void replaceContentView(String id, Intent newIntent) {
        this.history.add(getWindow().findViewById(R.id.container));
        View view = getLocalActivityManager().startActivity(id, newIntent).getDecorView();
        this.history.add(view);
        this.setContentView(view);
    }
    /*public String getNumeroTelefono(){
        TelephonyManager mTelephonyManager;
        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        return mTelephonyManager.getLine1Number();

    }*/

}
