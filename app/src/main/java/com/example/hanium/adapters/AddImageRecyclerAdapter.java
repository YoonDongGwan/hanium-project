package com.example.hanium.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hanium.R;

import java.util.ArrayList;

public class AddImageRecyclerAdapter extends RecyclerView.Adapter<AddImageRecyclerAdapter.ViewHolder> {
    ArrayList<Uri> list;
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView, remove_btn;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.addpost_item_image);
            remove_btn = itemView.findViewById(R.id.addpost_item_remove_btn);
        }
    }
    public AddImageRecyclerAdapter(ArrayList<Uri> list){
        this.list = list;
    }
    @NonNull
    @Override
    public AddImageRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.addpost_recyclerview_item,parent,false);
        AddImageRecyclerAdapter.ViewHolder viewHolder = new AddImageRecyclerAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AddImageRecyclerAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageURI(list.get(position));
        holder.remove_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });
        if (position == 0){
            holder.imageView.setVisibility(View.INVISIBLE);
            holder.remove_btn.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}