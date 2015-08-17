package com.example.paymentqr.createQR;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.paymentqr.R;
import com.google.zxing.WriterException;

public class GenerateCodeQR extends Activity {

    private static final String TAG = "GenerateCodeQR";

    private String mEncodeString;
    private TextView mTextDesc;
    private ImageView mImageQR;
    private ProgressBar mProgress;
    private Bitmap mBitmapQR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generate_code_qr);

        Bundle bundle = getIntent().getExtras();
        final int idMonto = bundle.getInt("idMonto");
        final int idCodigoComercio = bundle.getInt("idCodigoComercio");
        final String nameTarjeta = bundle.getString("idTarjeta");
        String idTarjeta = "1323234234";

        mImageQR = (ImageView) findViewById(R.id.imageQR);
        mEncodeString = "id_cliente: " + "18740265" +
                "; telefono: " +" 04120127517" +
                "; mac_address: " +"127.0.0.1" +
                "; id_transaccion: " +"1111" +
                "; monto (bsf): " + idMonto +
                "; id_comercio: " + idCodigoComercio +
                "; id_tarjeta: " + idTarjeta;
        mTextDesc = (TextView) findViewById(R.id.textDesc);

        new AsyncGenerateQRCode().execute(GenerateQR.MARGIN_AUTOMATIC);

        Button atrasReversarButton = (Button) findViewById(R.id.atrasButton);
        atrasReversarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreatePayment.self.back();
            }
        });


    }
    /**
     * AsyncTask to generate QR Code image
     */
    private class AsyncGenerateQRCode extends AsyncTask<Integer, Void, Integer> {

        /**
         * Background thread function to generate image
         *
         * @param params margin to use in creating QR Code
         * @return non zero for success
         *
         * Note that is margin is not in pixels.  See the zxing api for details about the margin
         * for QR code generation
         */
        @Override
        protected Integer doInBackground(Integer... params) {
            if (params.length != 1) {
                throw new IllegalArgumentException("Must pass QR Code margin value as argument");
            }

            try {
                final int colorQR = Color.BLACK;
                final int colorBackQR = Color.WHITE;
                final int marginSize = params[0];
                final int width = 450;
                final int height = 450;

                mBitmapQR = GenerateQR.generateBitmap(mEncodeString, width, height,
                        marginSize, colorQR, colorBackQR);
            }
            catch (IllegalArgumentException iae) {
                Log.e(TAG, "Invalid arguments for encoding QR");
                iae.printStackTrace();
                return 0;
            }
            catch (WriterException we) {
                Log.e(TAG, "QR Writer unable to generate code");
                we.printStackTrace();
                return 0;
            }
            return 1;
        }

        @Override
        protected void onPostExecute(Integer result) {
            if (result != 0) {
                mImageQR.setImageBitmap(mBitmapQR);
                mImageQR.setVisibility(View.VISIBLE);
            }else {
                mTextDesc.setText(getString(R.string.encode_error));
                mTextDesc.setVisibility(View.VISIBLE);
            }
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }


}
