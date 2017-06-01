package com.example.ayushi.madeinindia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by ayushi on 01-05-2017.
 */
public class Admin_Home extends AppCompatActivity {
    private Button delete_user;
    private Button delete_worker;
    private EditText worker_id1;
    private EditText user_id1;
    private DatabaseReference databaseReference;
    private  DatabaseReference databaseReference1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__home);

        delete_user = (Button) findViewById(R.id.delete_user);
        delete_worker = (Button) findViewById(R.id.delete_worker);
        worker_id1= (EditText) findViewById(R.id.worker_id);
        user_id1= (EditText) findViewById(R.id.user_id);

        delete_user.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                delete_user();
            }
        });

        delete_worker.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                delete_worker();
            }
        });
    }

    private void delete_user() {
        final String id = user_id1.getText().toString().trim();

        if (!(TextUtils.isEmpty(id))){
        databaseReference = FirebaseDatabase.getInstance().getReference().child("user");
        databaseReference1 = databaseReference.child(id);
        databaseReference1.removeValue();
        Toast.makeText(this, "User Account Deleted", Toast.LENGTH_SHORT).show();
    }else{
            Toast.makeText(this,"Please enter an ID!", Toast.LENGTH_SHORT).show();
        }
    }

    private void delete_worker(){
        final String id = worker_id1.getText().toString().trim();

        if (!(TextUtils.isEmpty(id))) {
            databaseReference = FirebaseDatabase.getInstance().getReference().child("worker");
            databaseReference1 = databaseReference.child(id);
            databaseReference1.removeValue();
            Toast.makeText(this, "Worker Account Deleted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Please enter an ID!", Toast.LENGTH_SHORT).show();
        }
    }
}
