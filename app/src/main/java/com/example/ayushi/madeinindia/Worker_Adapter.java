package com.example.ayushi.madeinindia;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by ayushi on 16-05-2017.
 */

public class Worker_Adapter extends RecyclerView.Adapter<Worker_Adapter.ViewHolder> {

    DatabaseReference databaseReference;
    DatabaseReference databaseReference1;
    private Context context;
    private List<Worker> list;
    Worker_Adapter(Context context, List<Worker> list){
        this.context=context;
        this.list=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Worker worker=list.get(position);
        holder.na.setText("  "+worker.getName());
        holder.mo.setText("  "+worker.getMobile());
        holder.ag.setText("  "+worker.getAge());

        holder.wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new android.app.AlertDialog.Builder(context).setTitle("Add to Wishlist").setMessage("Are you sure?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {

                                final String id = User_Login.userid;
                                databaseReference = FirebaseDatabase.getInstance().getReference().child("user").child(id);
                                databaseReference1 = databaseReference.child("wishlist").child(worker.getName());
                                databaseReference1.child("name").setValue(worker.getName());
                                databaseReference1.child("age").setValue(worker.getAge());
                                databaseReference1.child("mobile").setValue(worker.getMobile());
                                //Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();

                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder  extends RecyclerView.ViewHolder{

        public Button wish;
        public TextView na;
        public TextView mo;
        public TextView ag;

        public ViewHolder(View view){
            super(view);
            wish=(Button)view.findViewById(R.id.wish);
            na=(TextView)view.findViewById(R.id.na);
            mo=(TextView)view.findViewById(R.id.mo);
            ag=(TextView)view.findViewById(R.id.ag);
        }
    }
}
