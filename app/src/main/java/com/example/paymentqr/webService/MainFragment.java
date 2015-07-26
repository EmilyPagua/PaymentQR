package com.example.paymentqr.webService;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.paymentqr.R;

import android.util.Log;
public class MainFragment extends BaseVolleyFragment {
    private TextView label;
    private Button connector;
    private static final String TAG = MainFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        label = (TextView) v.findViewById(R.id.label);
        connector = (Button) v.findViewById(R.id.connection_button);
        Log.d(TAG, "onCreateView despues de asignar el label y el connector");
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        connector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeRequest();
            }
        });
    }

    private void makeRequest(){

        String url = "http://127.0.0.1:8000/snippets/?format=json";
       /* JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>(){

            @Override
            public void onResponse(JSONObject jsonObject) {
                label.setText(jsonObject.toString());
                onConnectionFinished();
            }
        },new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    onConnectionFailed(volleyError.toString());
                }
        });
        addToQueue(request);*/
    }
}