package com.example.ayushi.madeinindia;

import android.content.Intent;
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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by ayushi on 01-05-2017.
 */
public class Filter extends AppCompatActivity {
    public Button apply;
    public Button skip;
    public AutoCompleteTextView city;
    public EditText area;
    public RadioGroup radioGroup;
    public RadioButton male;
    public RadioButton female;
    public Spinner hours;
    public EditText salary;
    public CheckBox muslim;
    public CheckBox hindu;
    public CheckBox christian;
    public CheckBox others;

    public static String area1;
    public static String city1;
    public static String hours1;
    public static String gender1;
    public static String salary1;
    public static String religion1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        radioGroup= (RadioGroup) findViewById(R.id.radioGroup);
        apply=(Button)findViewById(R.id.apply);
        skip=(Button)findViewById(R.id.skip);
        city=(AutoCompleteTextView) findViewById(R.id.city);
        area=(EditText) findViewById(R.id.area);
        male= (RadioButton) findViewById(R.id.male);
        female= (RadioButton) findViewById(R.id.female);
        hours= (Spinner) findViewById(R.id.hours);
        salary= (EditText) findViewById(R.id.salary);
        muslim=(CheckBox) findViewById(R.id.muslim);
        hindu=(CheckBox)findViewById(R.id.hindu);
        christian=(CheckBox)findViewById(R.id.christian);
        others=(CheckBox)findViewById(R.id.others);

        salary.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event) {
                salary.setFocusable(true);
                salary.setFocusableInTouchMode(true);
                return false;
            }
        });

        city.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event) {
                city.setFocusable(true);
                city.setFocusableInTouchMode(true);
                return false;
            }
        });
        ArrayAdapter adapter1 = new ArrayAdapter(this,android.R.layout.simple_list_item_1,String_arrays.cities);
        city.setThreshold(1);
        city.setAdapter(adapter1);

        ArrayAdapter adapter2=new ArrayAdapter(this, android.R.layout.simple_list_item_1,String_arrays.hours_spinner);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hours.setAdapter(adapter2);

        area.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event) {
                area.setFocusable(true);
                area.setFocusableInTouchMode(true);
                return false;
            }
        });

        hindu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    muslim.setChecked(false);
                    christian.setChecked(false);
                    others.setChecked(false);
                    religion1="Hindu";
                }
            }
        });

        muslim.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    hindu.setChecked(false);
                    christian.setChecked(false);
                    others.setChecked(false);
                    religion1="Muslim";
                }
            }
        });

        christian.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    muslim.setChecked(false);
                    hindu.setChecked(false);
                    others.setChecked(false);
                    religion1="Christian";
                }
            }
        });

        others.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    muslim.setChecked(false);
                    christian.setChecked(false);
                    hindu.setChecked(false);
                    religion1="Others";
                }
            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                View radioButton=radioGroup.findViewById(checkedId);
                int index = radioGroup.indexOfChild(radioButton);
                switch (index){
                    case 0: gender1="Male";
                        break;
                    case 1: gender1="Female";
                        break;
                }
            }
        });

        apply.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                applybtn();
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                religion1="";
                gender1="";
                hours1="";
                city1="";
                salary1="";
                area1="";
                Intent i=new Intent(Filter.this,HomeActivity.class);
                startActivity(i);
            }
        });

    }

    public void applybtn(){

        final String religion2;
        final String gender2;
        final String hours2=hours.getSelectedItem().toString();
        final String city2=city.getText().toString();
        final String salary2 =salary.getText().toString();
        final String area2=area.getText().toString();

        if ((!TextUtils.isEmpty(hours2))&&(!TextUtils.isEmpty(city2))&&(!TextUtils.isEmpty(salary2))&&(!TextUtils.isEmpty(area2))&&(!TextUtils.isEmpty(area2))) {

            if(gender1.equals("Male")){
                gender2="Male";
            }else {gender2="Female";}

            if(religion1.equals("Hindu")){
                religion2="Hindu";
            }else if(religion1.equals("Christian")){
                religion2="Christian";
            }else if(religion1.equals("Muslim")){
                religion2="Muslim";
            }else{
                religion2="Others";
            }
            religion1=religion2;
            gender1=gender2;
            hours1=hours2;
            city1=city2;
            salary1=salary2;
            area1=area2;
            Intent i=new Intent(Filter.this,HomeActivity.class);
            startActivity(i);

        }else{
            Toast.makeText(this, "Please fill all details!", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}