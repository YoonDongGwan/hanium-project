package com.example.hanium.adapters;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hanium.activities.PostDetailActivity;
import com.example.hanium.R;
import com.example.hanium.classes.posts;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    ArrayList<posts> itemList;
    ArrayList<Bitmap> bitmaps;
    public class ViewHolder extends RecyclerView.ViewHolder{
        int id;
        TextView title,destination,deadline,time,price;
        ImageView thumbnail;
        ViewHolder(View itemview){
            super(itemview);
            title = itemview.findViewById(R.id.title);
            destination = itemview.findViewById(R.id.destination);
            deadline = itemview.findViewById(R.id.deadline);
            time = itemview.findViewById(R.id.time);
            thumbnail = itemview.findViewById(R.id.thumbnail);
            price = itemview.findViewById(R.id.price);
        }
    }
    public RecyclerAdapter(ArrayList<posts> itemList, ArrayList<Bitmap> bitmaps){
        this.itemList = itemList;
        this.bitmaps = bitmaps;
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
        holder.id = itemList.get(position).getId();
        holder.title.setText(itemList.get(position).getTitle());
        holder.destination.setText(itemList.get(position).getSimpleAddress());
        holder.deadline.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(itemList.get(position).getDeadline()));
        holder.time.setText(itemList.get(position).getRequiredTime());
        holder.thumbnail.setImageBitmap(bitmaps.get(position));
        holder.price.setText(itemList.get(position).getPrice()+" P");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), PostDetailActivity.class);
                intent.putExtra("id",String.valueOf(holder.id));
                ContextCompat.startActivity(holder.itemView.getContext(),intent,null);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

}
