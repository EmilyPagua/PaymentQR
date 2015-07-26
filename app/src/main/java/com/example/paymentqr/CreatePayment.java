package com.example.paymentqr;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.paymentqr.createQR.GenerateCodeQR;

public class CreatePayment extends Activity {

    private Spinner spinnerCount;
    private EditText etxMonto, etxCodigo, etxAvailabilityAccount;
    private static String idTarjeta;
    private Button btnAcept;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_payment);

        spinnerCount = (Spinner)findViewById(R.id.spAccount);
        btnAcept = (Button) findViewById(R.id.btaceptar);
        etxMonto = (EditText)findViewById(R.id.etxMonto);
        etxCodigo = (EditText)findViewById(R.id.etxCodigo);
        etxAvailabilityAccount = (EditText)findViewById(R.id.etxAvailabilityAccount);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this, R.array.array_tarjetas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //PARA EL COMBOBOX DE LAS TARJETAS DE UN CLIENTE
        spinnerCount.setAdapter(adapter);
        spinnerCount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                idTarjeta = parent.getItemAtPosition(position).toString();

                if (idTarjeta.equals("Visa")) {
                    idTarjeta = "V-1234566";
                    etxAvailabilityAccount.setText("5.000");
                } else if (idTarjeta.equals("MasterCard")) {
                    idTarjeta = "MC-9999999";
                    etxAvailabilityAccount.setText("2.000");

                } else if (idTarjeta.equals("VirtualCard")) {
                    idTarjeta = "VC-11111111";
                    etxAvailabilityAccount.setText("3.000");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void generateQR(View v) {
        if (etxMonto.getText().toString().equals("") == true ||
                etxCodigo.getText().toString().equals("")) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle("Error");
            alertDialogBuilder.setMessage("Debe llenar todos los campos");
            alertDialogBuilder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alertDialogBuilder.show();
        } else {
            Intent intent = new Intent(CreatePayment.this, GenerateCodeQR.class);
            Bundle b = new Bundle();
            b.putString("idTarjeta", idTarjeta);
            b.putInt("idMonto", Integer.valueOf(etxMonto.getText().toString()));
            b.putInt("idCodigoComercio", Integer.valueOf(etxCodigo.getText().toString()));
            intent.putExtras(b);
            startActivity(intent);
        }
    }
}
