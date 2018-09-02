package com.example.rado.cardviewtolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

public class Image extends AppCompatActivity {
    ImageView view;

    private static final String TAG = "Image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        Bundle bundle = getIntent().getExtras();
        String im = bundle.getString("img").replaceAll("[^0-9]", "");
        Log.d(TAG, "SEE THIS: " + im);
        int resid = Integer.parseInt(im);

        ImageView iv = (ImageView) findViewById(R.id.image);
        Log.d(TAG, "Getting image resource: " + bundle);
        //iv.setImageURI(null);
        Log.d(TAG, "Setting image resource: " + resid);
        iv.setImageResource(resid);
    }
}
