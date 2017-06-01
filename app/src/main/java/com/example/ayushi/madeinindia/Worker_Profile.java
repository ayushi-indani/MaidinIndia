package com.example.ayushi.madeinindia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
public class Worker_Profile extends Fragment {

    public Worker_Profile() {
        // Required empty public constructor
    }

    public EditText name2;
    public EditText id2;
    public EditText age2;
    public EditText mobile2;
    public AutoCompleteTextView city2;
    public EditText area2;
    public EditText salary2;
    public Button worker_profile;
    private DatabaseReference databaseReference;
    private  DatabaseReference databaseReference1;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_worker__profile, container, false);

        id2 = (EditText) v.findViewById(R.id.id2);
        name2 = (EditText) v.findViewById(R.id.name2);
        age2 = (EditText) v.findViewById(R.id.age2);
        mobile2 = (EditText) v.findViewById(R.id.mobile2);
        city2=(AutoCompleteTextView) v.findViewById(R.id.city2);
        area2=(EditText) v.findViewById(R.id.area2);
        salary2= (EditText)v.findViewById(R.id.salary2);
        worker_profile = (Button) v.findViewById(R.id.worker_profile);

        name2.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                name2.setFocusable(true);
                name2.setFocusableInTouchMode(true);
                return false;
            }
        });

        age2.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                age2.setFocusable(true);
                age2.setFocusableInTouchMode(true);
                return false;
            }
        });

        mobile2.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                mobile2.setFocusable(true);
                mobile2.setFocusableInTouchMode(true);
                return false;
            }
        });

        city2.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                city2.setFocusable(true);
                city2.setFocusableInTouchMode(true);
                return false;
            }
        });

        area2.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                area2.setFocusable(true);
                area2.setFocusableInTouchMode(true);
                return false;
            }
        });

        salary2.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                salary2.setFocusable(true);
                salary2.setFocusableInTouchMode(true);
                return false;
            }
        });

        ArrayAdapter adapter1 = new ArrayAdapter(getActivity().getBaseContext(),android.R.layout.simple_list_item_1,String_arrays.cities);
        city2.setThreshold(1);
        city2.setAdapter(adapter1);

         showField();
         worker_profile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                save_change();
            }
        });

        return v;
    }

    public void showField(){
        databaseReference = FirebaseDatabase.getInstance().getReference().child("worker");
        databaseReference.child(Worker_Login.workerid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                id2.setText(Worker_Login.workerid);
                name2.setText(dataSnapshot.child("name").getValue().toString());
                mobile2.setText(dataSnapshot.child("mobile").getValue().toString());
                city2.setText(dataSnapshot.child("city").getValue().toString());
                area2.setText(dataSnapshot.child("area").getValue().toString());
                age2.setText(dataSnapshot.child("age").getValue().toString());
                salary2.setText(dataSnapshot.child("salary").getValue().toString());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void save_change(){
        final String name = name2.getText().toString().trim();
        final String id = id2.getText().toString().trim();
        final String mobile= mobile2.getText().toString().trim();
        final String city= city2.getText().toString().trim();
        final String area= area2.getText().toString().trim();
        final String age= age2.getText().toString().trim();
        final String salary= salary2.getText().toString().trim();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("worker");
        databaseReference1 = databaseReference.child(id);
        databaseReference1.child("name").setValue(name);
        databaseReference1.child("mobile").setValue(mobile);
        databaseReference1.child("city").setValue(city);
        databaseReference1.child("area").setValue(area);
        databaseReference1.child("age").setValue(age);
        databaseReference1.child("salary").setValue(salary);
        Toast.makeText(getActivity(), "Changes saved", Toast.LENGTH_SHORT).show();
    }
}
