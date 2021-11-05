package com.example.hanium.adapters;


import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hanium.classes.ErrandPost;
import com.example.hanium.activities.PostDetailActivity;
import com.example.hanium.R;

import java.util.ArrayList;

public class ErrandAdapter extends RecyclerView.Adapter<ErrandAdapter.ViewHolder> {
    ArrayList<ErrandPost> List;
    ArrayList<Bitmap> bitmaps;
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,destination,deadline,time,state;
        ViewHolder(View itemview){
            super(itemview);
            title = itemview.findViewById(R.id.title);
            destination = itemview.findViewById(R.id.destination);
            deadline = itemview.findViewById(R.id.deadline);
            time = itemview.findViewById(R.id.time);
            state=itemview.findViewById(R.id.state);
        }
    }
    public ErrandAdapter(ArrayList<ErrandPost> list){
        List = list;
    }
    @NonNull
    @Override
    public ErrandAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.errand_item,parent,false);
        ErrandAdapter.ViewHolder viewHolder = new ErrandAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ErrandAdapter.ViewHolder holder, int position) {
        holder.title.setText(List.get(position).getTitle());
        holder.destination.setText(List.get(position).getDestination());
        holder.deadline.setText(List.get(position).getDeadline());
        holder.time.setText(List.get(position).getTime());
        holder.state.setText(List.get(position).getState());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), PostDetailActivity.class);
                ContextCompat.startActivity(holder.itemView.getContext(),intent,null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

}


