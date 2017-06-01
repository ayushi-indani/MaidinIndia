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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
/**
 * Created by ayushi on 01-05-2017.
 */
public class Register_Worker extends AppCompatActivity {

    public RadioButton male4;
    public RadioButton female4;
    public Button register_worker;
    public EditText id4;
    public EditText age4;
    public EditText pwd4;
    public EditText mobile4;
    public AutoCompleteTextView city4;
    public EditText area4;
    public EditText name4;
    public EditText salary4;
    public Spinner hours4;
    public Spinner work_type4;
    CheckBox hindu4;
    CheckBox muslim4;
    CheckBox christian4;
    CheckBox others4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__worker);

        register_worker=(Button)findViewById(R.id.register_worker);
        area4=(EditText) findViewById(R.id.area4);
        mobile4=(EditText) findViewById(R.id.mobile4);
        male4= (RadioButton) findViewById(R.id.male4);
        female4= (RadioButton) findViewById(R.id.female4);
        name4= (EditText) findViewById(R.id.name4);
        id4 = (EditText) findViewById(R.id.id4);
        age4 = (EditText) findViewById(R.id.age4);
        pwd4 = (EditText) findViewById(R.id.pwd4);
        salary4 = (EditText) findViewById(R.id.salary4);
        city4=(AutoCompleteTextView) findViewById(R.id.city4);
        hours4= (Spinner) findViewById(R.id.hours4);
        work_type4=(Spinner)findViewById(R.id.work_type4);
        hindu4=(CheckBox)findViewById(R.id.hindu4);
        muslim4=(CheckBox)findViewById(R.id.muslim4);
        christian4=(CheckBox)findViewById(R.id.christian4);
        others4=(CheckBox)findViewById(R.id.others4);

        name4.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                name4.setFocusable(true);
                name4.setFocusableInTouchMode(true);
                return false;
            }
        });

        id4.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                id4.setFocusable(true);
                id4.setFocusableInTouchMode(true);
                return false;
            }
        });

        pwd4.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                pwd4.setFocusable(true);
                pwd4.setFocusableInTouchMode(true);
                return false;
            }
        });

        mobile4.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                mobile4.setFocusable(true);
                mobile4.setFocusableInTouchMode(true);
                return false;
            }
        });

        salary4.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                salary4.setFocusable(true);
                salary4.setFocusableInTouchMode(true);
                return false;
            }
        });

        age4.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                age4.setFocusable(true);
                age4.setFocusableInTouchMode(true);
                return false;
            }
        });

        city4.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event) {
                city4.setFocusable(true);
                city4.setFocusableInTouchMode(true);
                return false;
            }
        });

        area4.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event) {
                area4.setFocusable(true);
                area4.setFocusableInTouchMode(true);
                return false;
            }
        });

        ArrayAdapter adapter1 = new ArrayAdapter(this,android.R.layout.simple_list_item_1,String_arrays.cities);
        city4.setThreshold(1);
        city4.setAdapter(adapter1);

        ArrayAdapter adapter2=new ArrayAdapter(this, android.R.layout.simple_list_item_1,String_arrays.hours_spinner);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hours4.setAdapter(adapter2);

        ArrayAdapter adapter3=new ArrayAdapter(this, android.R.layout.simple_list_item_1,String_arrays.work_type_spinner);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        work_type4.setAdapter(adapter3);

        hindu4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    muslim4.setChecked(false);
                    christian4.setChecked(false);
                    others4.setChecked(false);
                }
            }
        });

        muslim4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    hindu4.setChecked(false);
                    christian4.setChecked(false);
                    others4.setChecked(false);
                }
            }
        });

        christian4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    muslim4.setChecked(false);
                    hindu4.setChecked(false);
                    others4.setChecked(false);
                }
            }
        });

        others4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    muslim4.setChecked(false);
                    christian4.setChecked(false);
                    hindu4.setChecked(false);
                }
            }
        });


        register_worker.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                register_worker();
            }
        });
    }
    private void register_worker(){
        final String name = name4.getText().toString().trim();
        String id = id4.getText().toString().trim();
        String pwd = pwd4.getText().toString().trim();
        final String age = age4.getText().toString().trim();
        final String mobile= mobile4.getText().toString().trim();
        final String work_type= work_type4.getSelectedItem().toString().trim();
        final String city= city4.getText().toString().trim();
        final String area= area4.getText().toString().trim();
        final String gender;
        final String religion;
        if(male4.isChecked()){
            gender = male4.getText().toString().trim();
        }
        else if(female4.isChecked()){
            gender = female4.getText().toString().trim();
        }else gender="null";

        if(hindu4.isChecked()){
            religion = hindu4.getText().toString().trim();
        }
        else if(muslim4.isChecked()){
            religion = muslim4.getText().toString().trim();
        }
        else if(christian4.isChecked()) {
            religion = christian4.getText().toString().trim();
        }
        else if (others4.isChecked()){
            religion = others4.getText().toString().trim();
        }else religion="null";
        final String hours= hours4.getSelectedItem().toString().trim();
        final String salary= salary4.getText().toString().trim();

        if(!TextUtils.isEmpty(id)&&(!TextUtils.isEmpty(hours))&&(!TextUtils.isEmpty(city))&&(!TextUtils.isEmpty(salary))&&(!TextUtils.isEmpty(area))){

            final ProgressDialog progressDialog = new ProgressDialog(Register_Worker.this);
            progressDialog.setMessage("Creating Account");
            progressDialog.show();
            DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("worker");
            DatabaseReference databaseReference1=databaseReference.child(id);
            databaseReference1.child("name").setValue(name);
            databaseReference1.child("password").setValue(pwd);
            databaseReference1.child("age").setValue(age);
            databaseReference1.child("mobile").setValue(mobile);
            databaseReference1.child("work_type").setValue(work_type);
            databaseReference1.child("city").setValue(city);
            databaseReference1.child("area").setValue(area);
            databaseReference1.child("gender").setValue(gender);
            databaseReference1.child("religion").setValue(religion);
            databaseReference1.child("hours").setValue(hours);
            databaseReference1.child("salary").setValue(salary);
            progressDialog.dismiss();
            Toast.makeText(this,"Worker Registered", Toast.LENGTH_SHORT).show();
            id4.setText("");
            name4.setText("");
            pwd4.setText("");
            age4.setText("");
            mobile4.setText("");
            city4.setText("");
            area4.setText("");
            salary4.setText("");
            male4.setChecked(false);
            female4.setChecked(false);
            hindu4.setChecked(false);
            muslim4.setChecked(false);
            christian4.setChecked(false);
            others4.setChecked(false);
        }else{
            Toast.makeText(this,"Enter valid details!", Toast.LENGTH_SHORT).show();
        }
    }
}
