package com.example.paymentqr;

import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class TransactionDetail extends Activity {

    private static final String TAG = "TransactionDetail";

    private TextView detailResultTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_detail);

        Bundle bundle = getIntent().getExtras();
        final String result = bundle.getString("resultadoScanner");

        detailResultTxt = (TextView)findViewById(R.id.scanResult);
        detailResultTxt.setText(" Resultado Transacción "+result);
    }

    public void aceptTransaction(View v) {
        Intent intent = new Intent(this, MainTabActivity.class);
        startActivity(intent);
        finish();
    }

    public String getNumeroTelefono(){
        TelephonyManager mTelephonyManager;
        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        return mTelephonyManager.getLine1Number();

    }
}
