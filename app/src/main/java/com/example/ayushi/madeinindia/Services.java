package com.example.ayushi.madeinindia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
/**
 * Created by ayushi on 01-05-2017.
 */
public class Services extends AppCompatActivity {
    public ImageView image1;
    public ImageView image2;
    public ImageView image3;
    public ImageView image4;
    public ImageView image5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        image1 = (ImageView) findViewById(R.id.maid);
        image1.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
        Intent i = new Intent(Services.this,Filter.class);
        startActivity(i);
            }
         });
        image2 = (ImageView) findViewById(R.id.babysitter);
        image2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Services.this,Filter.class);
                startActivity(i);
            }
        });
        image3 = (ImageView) findViewById(R.id.cook);
        image3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Services.this,Filter.class);
                startActivity(i);
            }
        });
        image4 = (ImageView) findViewById(R.id.nurse);
        image4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Services.this,Filter.class);
                startActivity(i);
            }
        });
        image5 = (ImageView) findViewById(R.id.driver);
        image5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Services.this,Filter.class);
                startActivity(i);
            }
        });
}
}