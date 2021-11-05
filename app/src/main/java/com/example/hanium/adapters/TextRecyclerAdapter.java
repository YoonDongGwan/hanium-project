package com.example.hanium.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hanium.R;
import com.example.hanium.classes.SearchLocation;
import com.example.hanium.classes.locationinfo;
import com.example.hanium.classes.posts;

import java.util.ArrayList;


public class TextRecyclerAdapter extends RecyclerView.Adapter<TextRecyclerAdapter.ViewHolder> {
    ArrayList<SearchLocation> itemList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView location ;

        ViewHolder(View itemView) {
            super(itemView) ;

            location = itemView.findViewById(R.id.location) ;
        }
    }


    public TextRecyclerAdapter(ArrayList<SearchLocation> itemList) {
        this.itemList=itemList ;
    }


    @Override
    public TextRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.changelocation_recyclerview_item,parent,false);
        TextRecyclerAdapter.ViewHolder viewHolder = new TextRecyclerAdapter.ViewHolder(view);


        return viewHolder ;
    }


    @Override
    public void onBindViewHolder(TextRecyclerAdapter.ViewHolder holder, int position) {
        holder.location.setText(itemList.get(position).getADMNM()) ;

    }


    @Override
    public int getItemCount() {
        return itemList.size() ;
    }
}