package com.example.hanium;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    List<post> List = null;
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,destination,deadline,time,point;
        ViewHolder(View itemview){
            super(itemview);
            title = itemview.findViewById(R.id.title);
            destination = itemview.findViewById(R.id.destination);
            deadline = itemview.findViewById(R.id.deadline);
            time = itemview.findViewById(R.id.time);
        }
    }
    public RecyclerAdapter(List<post> list){
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
        holder.destination.setText(List.get(position).getDestination());
        holder.deadline.setText(List.get(position).getDeadline());
        holder.time.setText(List.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return List.size();
    }
}
