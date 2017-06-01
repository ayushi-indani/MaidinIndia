package com.example.ayushi.madeinindia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
/**
 * Created by ayushi on 01-05-2017.
 */
public class Worker_Login extends AppCompatActivity {
    public Button signin;
    public Button register;
    public EditText id;
    public EditText pwd;
    static public String workerid;
    public DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker__login);

        id = (EditText) findViewById(R.id.idw);
        id.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                id.setFocusable(true);
                id.setFocusableInTouchMode(true);
                return false;
            }
        });

        pwd = (EditText) findViewById(R.id.pwdw);
        pwd.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                pwd.setFocusable(true);
                pwd.setFocusableInTouchMode(true);
                return false;
            }
        });

        signin = (Button) findViewById(R.id.signinw);
        signin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                login();
            }
        });

        register = (Button) findViewById(R.id.registerw);
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Worker_Login.this, Register_Worker.class);
                startActivity(i);
            }
        });
    }

    private void login(){
        final String id1 = id.getText().toString().trim();
        final String pwd1 = pwd.getText().toString().trim();

        if (TextUtils.isEmpty(id1)) {
            Toast.makeText(this, "Enter ID", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pwd1)) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }
        final ProgressDialog progressDialog = new ProgressDialog(Worker_Login.this);
        progressDialog.setMessage("Logging in..");
        progressDialog.show();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("worker");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(id1)){
                    if(pwd1.equals(dataSnapshot.child(id1).child("password").getValue().toString())){
                        progressDialog.dismiss();
                        workerid=id1;
                        Intent i=new Intent(Worker_Login.this,Worker_home_tabs.class);
                        startActivity(i);
                    }else{
                        pwd.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                        progressDialog.dismiss();
                        Toast t= Toast.makeText(Worker_Login.this, "Incorrect Password", Toast.LENGTH_SHORT);
                        t.cancel();
                    }
                }else {
                    id.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                    progressDialog.dismiss();
                    Toast t =Toast.makeText(Worker_Login.this, "Incorrect ID", Toast.LENGTH_SHORT);
                    t.cancel();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}