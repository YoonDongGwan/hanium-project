package com.example.hanium;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    ArrayList<posts> List;

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,destination,deadline,time;
        ViewHolder(View itemview){
            super(itemview);
            title = itemview.findViewById(R.id.title);
            destination = itemview.findViewById(R.id.destination);
            deadline = itemview.findViewById(R.id.deadline);
            time = itemview.findViewById(R.id.time);
        }
    }
    public RecyclerAdapter(ArrayList<posts> list){
        List = list;
    }
    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        RecyclerAdapter.ViewHolder viewHolder = new RecyclerAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        holder.title.setText(List.get(position).getTitle());
//        holder.destination.setText(List.get(position).getDestination());
        holder.deadline.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(List.get(position).getDeadline()));
        holder.time.setText(List.get(position).getRequiredTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(),PostDetailActivity.class);
                ContextCompat.startActivity(holder.itemView.getContext(),intent,null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

}
