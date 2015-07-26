package com.example.paymentqr;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.paymentqr.webService.MainActivity2;


public class MainActivity extends ActionBarActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "Inicio MainActivity");
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    public void SingIn(View v) {
               /*if (etUsarName.getText().toString().equals("") == true ||
                                etPassword.getText().toString().equals("")) {
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                            alertDialogBuilder.setTitle("Error");
                            alertDialogBuilder.setMessage("Debe llenar todos los campos");
                            alertDialogBuilder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            alertDialogBuilder.show();
                        } else {
                            if (etUsarName.getText().toString().equals("admin") &&
                                    etPassword.getText().toString().equals("admin")) {
                                Intent intent = new Intent(PaginaPrincipal.this, MenuPrincipal.class);
                                Bundle b = new Bundle();
                                intent.putExtras(b);
                                startActivity(intent);
                            } else {
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                                alertDialogBuilder.setTitle("Error");
                                alertDialogBuilder.setMessage("Datos incorrectos");
                                alertDialogBuilder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                                alertDialogBuilder.show();
                            }
                        }*/
        Intent intent = new Intent(MainActivity.this, MainTabActivity.class);
        Bundle b = new Bundle();
        intent.putExtras(b);
        startActivity(intent);
        finish();

    }

    public void register(View v) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        Bundle b = new Bundle();
        intent.putExtras(b);
        startActivity(intent);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
