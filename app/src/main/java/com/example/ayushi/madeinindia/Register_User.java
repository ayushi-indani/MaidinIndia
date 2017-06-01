package com.example.ayushi.madeinindia;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
/**
 * Created by ayushi on 01-05-2017.
 */
public class Register_User extends AppCompatActivity {
    public EditText name3;
    public EditText id3;
    public EditText pwd3;
    public EditText mobile3;
    public AutoCompleteTextView city3;
    public EditText area3;
    public RadioButton male3;
    public RadioButton female3;
    public Button register_user;
    private DatabaseReference databaseReference;
    private  DatabaseReference databaseReference1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__user);

        mobile3=(EditText) findViewById(R.id.mobile3);
        male3= (RadioButton) findViewById(R.id.male3);
        female3= (RadioButton) findViewById(R.id.female3);
        name3= (EditText) findViewById(R.id.name3);
        id3 = (EditText) findViewById(R.id.id3);
        pwd3 = (EditText) findViewById(R.id.pwd3);
        city3=(AutoCompleteTextView) findViewById(R.id.city3);
        area3=(EditText) findViewById(R.id.area3);
        register_user = (Button) findViewById(R.id.register_user);


        name3.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                name3.setFocusable(true);
                name3.setFocusableInTouchMode(true);
                return false;
            }
        });

        id3.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                id3.setFocusable(true);
                id3.setFocusableInTouchMode(true);
                return false;
            }
        });

        pwd3.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                pwd3.setFocusable(true);
                pwd3.setFocusableInTouchMode(true);
                return false;
            }
        });

        mobile3.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                mobile3.setFocusable(true);
                mobile3.setFocusableInTouchMode(true);
                return false;
            }
        });

        city3.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event) {
                city3.setFocusable(true);
                city3.setFocusableInTouchMode(true);
                return false;
            }
        });
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,String_arrays.cities);
        city3.setThreshold(1);
        city3.setAdapter(adapter);

        area3.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event) {
                area3.setFocusable(true);
                area3.setFocusableInTouchMode(true);
                return false;
            }
        });

        register_user.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                register_user();
            }
        });
    }
   private void register_user(){

       final String name = name3.getText().toString().trim();
       final String id = id3.getText().toString().trim();
       final String pwd = pwd3.getText().toString().trim();
       final String mobile= mobile3.getText().toString().trim();
       final String city= city3.getText().toString().trim();
       final String area= area3.getText().toString().trim();
       final String gender;
       if(male3.isChecked()){
           gender = male3.getText().toString().trim();
       }
       else if(female3.isChecked()){
           gender = female3.getText().toString().trim();
       }else gender="null";

       if((!TextUtils.isEmpty(id))&&(!TextUtils.isEmpty(name))&&(!TextUtils.isEmpty(pwd))&&(!TextUtils.isEmpty(mobile))&&(!TextUtils.isEmpty(city))&&(!TextUtils.isEmpty(mobile))&&(!TextUtils.isEmpty(area))) {

           final ProgressDialog progressDialog = new ProgressDialog(Register_User.this);
           progressDialog.setMessage("Creating Account");
           progressDialog.show();
           databaseReference = FirebaseDatabase.getInstance().getReference().child("user");
           databaseReference1 = databaseReference.child(id);
           databaseReference1.child("password").setValue(pwd);
           databaseReference1.child("name").setValue(name);
           databaseReference1.child("gender").setValue(gender);
           databaseReference1.child("mobile").setValue(mobile);
           databaseReference1.child("city").setValue(city);
           databaseReference1.child("area").setValue(area);
           progressDialog.dismiss();
           Toast.makeText(getApplicationContext(), "User Registered", Toast.LENGTH_SHORT).show();
           id3.setText("");
           name3.setText("");
           pwd3.setText("");
           mobile3.setText("");
           city3.setText("");
           area3.setText("");
           male3.setChecked(false);
           female3.setChecked(false);
       }else{
           Toast.makeText(this,"Enter valid details!", Toast.LENGTH_SHORT).show();
       }
   }
}
