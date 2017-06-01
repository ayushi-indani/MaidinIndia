package com.example.ayushi.madeinindia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
public class User_Login extends AppCompatActivity {
    public Button signin;
    public Button register;
    public EditText id;
    public EditText password;
    public DatabaseReference databaseReference;
    static public String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__login);

        id = (EditText) findViewById(R.id.id);
        id.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                id.setFocusable(true);
                id.setFocusableInTouchMode(true);
                return false;
            }
        });

        password = (EditText) findViewById(R.id.password);
        password.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                password.setFocusable(true);
                password.setFocusableInTouchMode(true);
                return false;
            }
        });

        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(User_Login.this, Register_User.class);
                startActivity(i);
            }
        });

        signin = (Button) findViewById(R.id.signin);
        signin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {

        final String id1 = id.getText().toString().trim();
        final String pwd1 = password.getText().toString().trim();
        if (TextUtils.isEmpty(id1)) {
            Toast.makeText(this, "Enter ID", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pwd1)) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }
        final ProgressDialog progressDialog = new ProgressDialog(User_Login.this);
        progressDialog.setMessage("Logging in..");
        progressDialog.show();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("user");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                if(dataSnapshot.hasChild(id1)){
                    if(pwd1.equals(dataSnapshot.child(id1).child("password").getValue().toString())){
                        progressDialog.dismiss();
                        finish();
                        userid=id1;
                        startActivity(new Intent(User_Login.this,Services.class));
                    }else{
                        password.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                        progressDialog.dismiss();
                        Toast t= Toast.makeText(User_Login.this, "Incorrect Password", Toast.LENGTH_SHORT);
                        t.cancel();
                    }
                }else {
                        id.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                        progressDialog.dismiss();
                        Toast t =Toast.makeText(User_Login.this, "Incorrect ID", Toast.LENGTH_SHORT);
                        t.cancel();
                     }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
     }
}