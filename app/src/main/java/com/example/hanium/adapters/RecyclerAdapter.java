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
import com.example.hanium.activities.ReviewPopup;
import com.example.hanium.classes.posts;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<posts> itemList;
    ArrayList<Bitmap> bitmaps;
    public class ViewHolder0 extends RecyclerView.ViewHolder{
        int id;
        TextView title,destination,deadline,time,price,review;
        ImageView thumbnail;
        ViewHolder0(View itemview){
            super(itemview);
            title = itemview.findViewById(R.id.title);
            destination = itemview.findViewById(R.id.destination);
            deadline = itemview.findViewById(R.id.deadline);
            time = itemview.findViewById(R.id.time);
            thumbnail = itemview.findViewById(R.id.thumbnail);
            price = itemview.findViewById(R.id.price);
            review = itemview.findViewById(R.id.review);
        }
    }
    public RecyclerAdapter(ArrayList<posts> itemList, ArrayList<Bitmap> bitmaps){
        this.itemList = itemList;
        this.bitmaps = bitmaps;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case 0:
            case 1:
            case 2:
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
            RecyclerAdapter.ViewHolder0 viewHolder = new RecyclerAdapter.ViewHolder0(view);
            return viewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewholder, int position) {
        String status = itemList.get(position).getStatus();
        switch(status) {
            case "basic":
            case "proceed":
            ViewHolder0 holder = (ViewHolder0) viewholder;
            holder.id = itemList.get(position).getId();
            holder.title.setText(itemList.get(position).getTitle());
            holder.destination.setText(itemList.get(position).getSimpleAddress());
            holder.deadline.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(itemList.get(position).getDeadline()));
            holder.time.setText(itemList.get(position).getRequiredTime());
            holder.thumbnail.setImageBitmap(bitmaps.get(position));
            holder.price.setText(itemList.get(position).getPrice() + " P");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(holder.itemView.getContext(), PostDetailActivity.class);
                    intent.putExtra("id", String.valueOf(holder.id));
                    ContextCompat.startActivity(holder.itemView.getContext(), intent, null);
                }
            });
            break;
            case "end":
                ViewHolder0 holder0 = (ViewHolder0) viewholder;
                holder0.id = itemList.get(position).getId();
                holder0.title.setText(itemList.get(position).getTitle());
                holder0.destination.setText(itemList.get(position).getSimpleAddress());
                holder0.deadline.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(itemList.get(position).getDeadline()));
                holder0.time.setText(itemList.get(position).getRequiredTime());
                holder0.thumbnail.setImageBitmap(bitmaps.get(position));
                holder0.price.setText(itemList.get(position).getPrice() + " P");
                holder0.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(holder0.itemView.getContext(), PostDetailActivity.class);
                        intent.putExtra("id", String.valueOf(holder0.id));
                        ContextCompat.startActivity(holder0.itemView.getContext(), intent, null);
                    }
                });
                holder0.review.setVisibility(View.VISIBLE);
                Log.d("reviewpoint",itemList.get(position).getReview()+"점");
                holder0.review.setText(itemList.get(position).getReview()+"점");
                holder0.review.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(holder0.itemView.getContext(), ReviewPopup.class);
                        intent.putExtra("id",holder0.id+"");
                        ContextCompat.startActivity(holder0.itemView.getContext(), intent, null);
                    }
                });
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        String status = itemList.get(position).getStatus();
        switch (status){
            case "basic":
                return 0;
            case "proceed":
                return 1;
            case "end":
                return 2;
            default:
                return 0;
        }
    }
}
