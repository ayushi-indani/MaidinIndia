package com.example.ayushi.madeinindia;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
public class Worker_Account extends Fragment {

    public Worker_Account() {
        // Required empty public constructor
    }

    public Button logout1;
    public Button change_pass;
    public Button delete_acc;
    public EditText oldpass;
    public EditText newpass;
    public DatabaseReference databaseReference;
    public DatabaseReference databaseReference1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_worker__account, container, false);
        logout1 = (Button) v.findViewById(R.id.logout2);
        change_pass = (Button) v.findViewById(R.id.change);
        delete_acc = (Button) v.findViewById(R.id.del);
        oldpass = (EditText) v.findViewById(R.id.old_pwd);
        newpass = (EditText) v.findViewById(R.id.new_pwd);

        oldpass.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                oldpass.setFocusable(true);
                oldpass.setFocusableInTouchMode(true);
                return false;
            }
        });

        newpass.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                newpass.setFocusable(true);
                newpass.setFocusableInTouchMode(true);
                return false;
            }
        });
        logout1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Logging out");
                progressDialog.show();
                logout();
            }
        });
        change_pass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                change_pass();
            }
        });
        delete_acc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                delete_acc();
            }
        });
        return v;
    }

    public void logout() {
        getActivity().finish();
        Intent i = new Intent(getActivity(), Worker_Login.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    public void change_pass() {

        final String id1 = Worker_Login.workerid;
        final String old1 = oldpass.getText().toString().trim();
        final String new1 = newpass.getText().toString().trim();

        if (TextUtils.isEmpty(old1)) {
            Toast.makeText(getActivity(), "Enter Old Password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(new1)) {
            Toast.makeText(getActivity(), "Enter New Password", Toast.LENGTH_SHORT).show();
            return;
        }
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Changing Password");
        progressDialog.show();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("worker");
        databaseReference1 = databaseReference.child(id1);
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (old1.equals(dataSnapshot.child("password").getValue().toString())) {
                    progressDialog.dismiss();
                    databaseReference1.child("password").setValue(new1);
                    Toast.makeText(getActivity(), "Password Changed", Toast.LENGTH_SHORT).show();
                } else {
                    oldpass.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                    progressDialog.dismiss();
                    Toast t =Toast.makeText(getActivity(), "Wrong Password Entered", Toast.LENGTH_SHORT);
                    t.cancel();
                    }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void delete_acc() {
        new AlertDialog.Builder(getActivity()).setTitle("Delete Account").setMessage("Are you sure?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {

                        Intent intent=new Intent(getActivity(),MainActivity.class);
                        startActivity(intent);
                        final String id1 = Worker_Login.workerid;
                        databaseReference = FirebaseDatabase.getInstance().getReference().child("worker");
                        databaseReference1 = databaseReference.child(id1);
                        databaseReference1.removeValue();
                        Toast.makeText(getActivity(), "Deleted", Toast.LENGTH_SHORT).show();
                    }})
                .setNegativeButton(android.R.string.no, null).show();

    }
}