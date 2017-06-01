package com.example.ayushi.madeinindia;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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

import static com.example.ayushi.madeinindia.R.id.pwd1;

/**
 * Created by ayushi on 01-05-2017.
 */

public class User_Profile extends Fragment {
    public EditText name1;
    public EditText id1;
    public EditText password1;
    public Button user_profile;
    public Button logout;
    public EditText mobile1;
    public AutoCompleteTextView city1;
    public EditText area1;
    private DatabaseReference databaseReference;
    private  DatabaseReference databaseReference1;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_user__profile, container, false);

        name1 = (EditText) v.findViewById(R.id.name1);
        id1 = (EditText) v.findViewById(R.id.id1);
        password1 = (EditText) v.findViewById(pwd1);
        user_profile = (Button) v.findViewById(R.id.user_profile);
        logout= (Button) v.findViewById(R.id.logout1);
        mobile1 = (EditText) v.findViewById(R.id.mobile1);
        city1=(AutoCompleteTextView) v.findViewById(R.id.city1);
        area1=(EditText) v.findViewById(R.id.area1);

        name1.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                name1.setFocusable(true);
                name1.setFocusableInTouchMode(true);
                return false;
            }
        });

        password1.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                password1.setFocusable(true);
                password1.setFocusableInTouchMode(true);
                return false;
            }
        });

        mobile1.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                mobile1.setFocusable(true);
                mobile1.setFocusableInTouchMode(true);
                return false;
            }
        });

        city1.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                city1.setFocusable(true);
                city1.setFocusableInTouchMode(true);
                return false;
            }
        });

        area1.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                area1.setFocusable(true);
                area1.setFocusableInTouchMode(true);
                return false;
            }
        });

        ArrayAdapter adapter1 = new ArrayAdapter(getActivity().getBaseContext(),android.R.layout.simple_list_item_1,String_arrays.cities);
        city1.setThreshold(1);
        city1.setAdapter(adapter1);

        showFields();

        user_profile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                save_changes();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Logging out");
                progressDialog.show();
                logout();
            }
        });
        return v;
    }

    public void showFields(){
        databaseReference = FirebaseDatabase.getInstance().getReference().child("user");
        databaseReference.child(User_Login.userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                id1.setText(User_Login.userid);
                name1.setText(dataSnapshot.child("name").getValue().toString());
                password1.setText(dataSnapshot.child("password").getValue().toString());
                mobile1.setText(dataSnapshot.child("mobile").getValue().toString());
                city1.setText(dataSnapshot.child("city").getValue().toString());
                area1.setText(dataSnapshot.child("area").getValue().toString());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void save_changes(){
        final String name = name1.getText().toString().trim();
        final String id = id1.getText().toString().trim();
        final String pwd = password1.getText().toString().trim();
        final String mobile= mobile1.getText().toString().trim();
        final String city= city1.getText().toString().trim();
        final String area= area1.getText().toString().trim();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("user");
        databaseReference1 = databaseReference.child(id);
        databaseReference1.child("password").setValue(pwd);
        databaseReference1.child("name").setValue(name);
        databaseReference1.child("mobile").setValue(mobile);
        databaseReference1.child("city").setValue(city);
        databaseReference1.child("area").setValue(area);
        Toast.makeText(getActivity(), "Changes saved", Toast.LENGTH_SHORT).show();
    }

    public void logout(){
        getActivity().finish();
        Intent i = new Intent(getActivity(),User_Login.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
