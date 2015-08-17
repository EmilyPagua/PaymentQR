package com.example.paymentqr.createQR;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.paymentqr.R;

import java.util.ArrayList;

public class CreatePayment extends ActivityGroup {

    public static CreatePayment self;
    private ArrayList<View> history;

    private Spinner spinnerCount;
    private EditText etxMonto, etxCodigo, etxAvailabilityAccount;
    private static String idTarjeta;
    ArrayAdapter adapter;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.history = new ArrayList<View>();
        self = this;

        setContentView(R.layout.create_payment);

        spinnerCount = (Spinner)findViewById(R.id.spAccount);
        etxMonto = (EditText)findViewById(R.id.etxMonto);
        etxCodigo = (EditText)findViewById(R.id.etxCodigo);
        etxAvailabilityAccount = (EditText)findViewById(R.id.etxAvailabilityAccount);

        adapter = ArrayAdapter.createFromResource(
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
            replaceContentView("GenerarPago", intent);
        }
    }
    public void limpiarCampos(View v) {
        etxMonto.setText("");
        etxCodigo.setText("");
        etxAvailabilityAccount.setText("");
        spinnerCount.setAdapter(adapter);

    }

    public void replaceContentView(String id, Intent newIntent) {
        this.history.add(getWindow().findViewById(R.id.createPayment));
        View view = getLocalActivityManager().startActivity(id, newIntent).getDecorView();
        this.history.add(view);
        this.setContentView(view);
    }

    public void back()
    {
        if(history.size() > 1){
            history.remove(history.size()-1);
            View view = history.get(history.size()-1);
            Activity activity = (Activity) view.getContext();
            setContentView(view);
        }
        else{
            finish();
        }
    }

}
