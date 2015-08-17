package com.example.paymentqr.scanQR;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.paymentqr.R;

import eu.livotov.zxscan.ScannerFragment;
import eu.livotov.zxscan.ScannerView;


public class ScanActivity extends ActionBarActivity implements ScannerView.ScannerViewEventListener
{
    public final static String RESULT_EXTRA_STR = "data";
    private ScannerFragment scanner;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_activity_main);

        if (savedInstanceState == null)
        {
            if (scanner == null)
            {
                scanner = new ScannerFragment();
                scanner.setScannerViewEventListener(this);
            }

            getSupportFragmentManager().beginTransaction().add(R.id.container, scanner).commit();
        }
    }


    @Override
    public void onScannerReady()
    {

    }

    public void onScannerStopped()
    {

    }

    @Override
    public void onScannerFailure(int cameraError)
    {

    }

    public boolean onCodeScanned(final String data)
    {
        Intent res = new Intent();
        res.putExtra(RESULT_EXTRA_STR, data);
        setResult(RESULT_OK, res);
        finish();
        return true;
    }
}
