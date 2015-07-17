package com.example.paymentqr;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.paymentqr.scanQR.ScannerActivity;


public class TransactionDetail extends ActionBarActivity {

    private TextView detailResultTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_detail);

        Bundle bundle = getIntent().getExtras();
        final String result = bundle.getString("RESULT");

        detailResultTxt = (TextView)findViewById(R.id.scanResult);
        detailResultTxt.setText(result);
    }

    public void aceptTransaction(View v) {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
        finish();
    }
    public void rejectsTransaction(View v) {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
        finish();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.transaction_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
