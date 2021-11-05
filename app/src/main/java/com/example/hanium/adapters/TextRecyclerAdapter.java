package com.example.hanium.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.app.ComponentActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hanium.R;
import com.example.hanium.activities.ChangeLocationActivity;
import com.example.hanium.activities.SetLocationActivity;
import com.example.hanium.classes.SearchLocation;
import com.example.hanium.classes.locationinfo;
import com.example.hanium.classes.posts;
import com.google.firebase.database.core.Context;

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
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.itemView.getContext(), SetLocationActivity.class);
                intent.putExtra("location",holder.location.getText().toString());
                ContextCompat.startActivity(holder.itemView.getContext(),intent,null);
                Log.d("test",holder.location+"");
                ((ChangeLocationActivity)holder.itemView.getContext()).finish();


            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size() ;
    }
}
