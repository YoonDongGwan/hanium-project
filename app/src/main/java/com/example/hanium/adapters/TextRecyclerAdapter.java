package com.example.hanium.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hanium.R;
import com.example.hanium.activities.ChangeLocationActivity;
import com.example.hanium.activities.SetLocationActivity;
import com.example.hanium.classes.SearchLocation;

import java.util.ArrayList;


public class TextRecyclerAdapter extends RecyclerView.Adapter<TextRecyclerAdapter.ViewHolder> {
    ArrayList<SearchLocation> itemList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView location ;
        String id;
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
        holder.id = itemList.get(position).getDistrictId();
        holder.location.setText(itemList.get(position).getADMNM()) ;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.itemView.getContext(), SetLocationActivity.class);
                intent.putExtra("ADMNM",holder.location.getText().toString());
                intent.putExtra("districtId",holder.id);
                ((ChangeLocationActivity)holder.itemView.getContext()).setResult(Activity.RESULT_OK, intent);
                ((ChangeLocationActivity)holder.itemView.getContext()).finish();
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size() ;
    }
}
