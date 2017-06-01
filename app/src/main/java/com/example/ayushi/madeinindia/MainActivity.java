package com.example.ayushi.madeinindia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by ayushi on 01-05-2017.
 */
public class MainActivity extends AppCompatActivity {
    public Button worker;
    public ImageView logo;
    public Button user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        worker = (Button) findViewById(R.id.worker);
        worker.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Worker_Login.class);
                startActivity(i);
            }
        });

        user = (Button) findViewById(R.id.user);
        user.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, User_Login.class);
                startActivity(i);
            }
        });

        logo = (ImageView) findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            int i=3;
            @Override
            public void onClick(View v) {
                if(i==0){
                Intent intent=new Intent(MainActivity.this,Admin.class);
                startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "You are "+i+" steps away from entering developer mode", Toast.LENGTH_SHORT).show();
                    i--;
                }
            }
        });
    }
}