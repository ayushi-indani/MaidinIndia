package com.example.ayushi.madeinindia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
 * Created by ayushi on 01-05-2017.
 */
public class Admin extends AppCompatActivity {
    public EditText id;
    public EditText pwd;
    public Button admin_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        id=(EditText)findViewById(R.id.admin_id);
        pwd=(EditText)findViewById(R.id.admin_pwd);
        admin_login=(Button) findViewById(R.id.admin_login);

        id.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event) {
                id.setFocusable(true);
                id.setFocusableInTouchMode(true);
                return false;
            }
        });

        pwd.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event) {
                pwd.setFocusable(true);
                pwd.setFocusableInTouchMode(true);
                return false;
            }
        });

        admin_login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String pwdx = pwd.getText().toString();
                String idx = id.getText().toString();
                if(idx.equals("admin")&& pwdx.equals("admin"))
                {
                    Intent i=new Intent(Admin.this,Admin_Home.class);
                    startActivity(i);
                }else{
                    Toast.makeText(Admin.this, "Incorrect Login Attempt", Toast.LENGTH_SHORT).show();
                }
            }
    });
}
}