package com.example.ayushi.madeinindia;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

/**
 * Created by ayushi on 16-05-2017.
 */

public class User_Adapter extends RecyclerView.Adapter<User_Adapter.ViewHolder> {

    DatabaseReference databaseReference;
    private Context context;
    private List<User> list;
    User_Adapter(Context context, List<User> list){
        this.context=context;
        this.list=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.model_user,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final User user=list.get(position);
        holder.na.setText("  "+user.getName());
        holder.mo.setText("  "+user.getMobile());
        holder.ge.setText("  "+user.getGender());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder  extends RecyclerView.ViewHolder{

        public TextView na;
        public TextView mo;
        public TextView ge;

        public ViewHolder(View view){
            super(view);
            na=(TextView)view.findViewById(R.id.na1);
            mo=(TextView)view.findViewById(R.id.mo1);
            ge=(TextView)view.findViewById(R.id.ge1);
        }
    }
}
