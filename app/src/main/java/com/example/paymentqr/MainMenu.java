package com.example.paymentqr;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.paymentqr.scanQR.ScannerActivity;

public class MainMenu extends Activity {

	private DrawerLayout drawerLayout;
	private ListView drawer;
	private ActionBarDrawerToggle toggle;
	private static final String[] opciones = {"Mis Mensajes","Pagar", "Cobrar", "Otras Ope."};

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

	}


}
