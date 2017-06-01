package com.example.ayushi.madeinindia;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ayushi on 16-05-2017.
 */

public class ViewWorker extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    DatabaseReference databaseReference;
    List<Worker> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.worker, container, false);

        recyclerView=(RecyclerView)v.findViewById(R.id.recyclerView1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list=new ArrayList<>();

        databaseReference= FirebaseDatabase.getInstance().getReference().child("worker");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                    Worker worker=postSnapshot.getValue(Worker.class);
                    if(worker.getGender().equals(Filter.gender1)&&worker.getArea().equals(Filter.area1)&&worker.getCity().equals(Filter.city1)
                            &&worker.getSalary().equals(Filter.salary1)&&worker.getHours().equals(Filter.hours1)&&worker.getReligion().equals(Filter.religion1)){
                        list.add(worker);
                    }
                }
                adapter=new Worker_Adapter(getActivity(),list);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return v;
    }
}
