package com.example.paymentqr;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.example.paymentqr.createQR.CreatePayment;
import com.example.paymentqr.scanQR.ScanMainActivity;


public class MainTabActivity extends TabActivity {
	
	public static TabHost mTabHost;
	public static int tabDisplayed = 0;

	final public String tabId1 = "Inicio", tabId2 ="Pagar", tabId3="Cobrar", tabId4="Info";

	private void setupTabHost() {
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();
	}
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);

        setupTabHost();

		setupTab(new TextView(this), tabId1);
		setupTab(new TextView(this), tabId2);
		setupTab(new TextView(this), tabId3);
		setupTab(new TextView(this), tabId4);
        mTabHost.setCurrentTab(tabDisplayed);

    }
    
	private void setupTab(final View view, final String tag) {
		 
		if (tag == tabId1){
			View tabview = createTabView(mTabHost.getContext(), tag);
			Intent intent = new Intent(getApplicationContext(), MainMenu.class);
	        TabSpec setContent = mTabHost.newTabSpec(tag).setIndicator(tabview).setContent(new TabHost.TabContentFactory()
	        {
	            public View createTabContent(String tag)
	            {
	                return view;
	            }
	        });
	        setContent.setContent(intent);
	        mTabHost.addTab(setContent);
		}
		else if (tag == tabId2){
			View tabview = createTabView(mTabHost.getContext(), tag);
			Intent intent = new Intent(getApplicationContext(), CreatePayment.class);
	        TabSpec setContent = mTabHost.newTabSpec(tag).setIndicator(tabview).setContent(new TabHost.TabContentFactory()
	        {
	            public View createTabContent(String tag)
	            {
	                return view;
	            }
	        });
	        setContent.setContent(intent);
	        mTabHost.addTab(setContent);
		}
		else if (tag == tabId3){
			View tabview = createTabView(mTabHost.getContext(), tag);
			Intent intent = new Intent(getApplicationContext(), ScanMainActivity.class);
	        TabSpec setContent = mTabHost.newTabSpec(tag).setIndicator(tabview).setContent(new TabHost.TabContentFactory()
	        {
	            public View createTabContent(String tag)
	            {
	                return view;
	            }
	        });
	        setContent.setContent(intent);
	        mTabHost.addTab(setContent);
		}
		else if (tag == tabId4){
			View tabview = createTabView(mTabHost.getContext(), tag);
			Intent intent = new Intent(getApplicationContext(), MainMenu.class);
			TabSpec setContent = mTabHost.newTabSpec(tag).setIndicator(tabview).setContent(new TabHost.TabContentFactory()
			{
				public View createTabContent(String tag)
				{
					return view;
				}
			});
			setContent.setContent(intent);
			mTabHost.addTab(setContent);
		}
	}


	private static View createTabView(final Context context, final String text) {
		View view = LayoutInflater.from(context).inflate(R.layout.tabs_bg, null);
		ImageView tab_image = (ImageView) view.findViewById(R.id.tab_image);
		if (text == "Inicio")
			tab_image.setImageResource(R.drawable.ic_action_home);
		else if (text == "Pagar")
			tab_image.setImageResource(R.drawable.ic_action_swap_vert_circle);
		else if (text == "Cobrar")
			tab_image.setImageResource(R.drawable.cierre);
        else if (text == "Info")
            tab_image.setImageResource(R.drawable.info);
        TextView tv = (TextView) view.findViewById(R.id.tabsText);
		tv.setText(text);
		return view;
	}
}




