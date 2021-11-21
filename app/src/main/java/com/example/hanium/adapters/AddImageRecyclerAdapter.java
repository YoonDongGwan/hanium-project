package com.example.hanium.adapters;

import android.graphics.Bitmap;
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
    ArrayList<Bitmap> bitmaps;
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView, remove_btn;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.addpost_item_image);
//            remove_btn = itemView.findViewById(R.id.addpost_item_remove_btn);
        }
    }
    public AddImageRecyclerAdapter(ArrayList<Uri> list, ArrayList<Bitmap> bitmaps){
        this.list = list;
        this.bitmaps = bitmaps;
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
        if(bitmaps == null) {
            holder.imageView.setImageURI(list.get(position));
//            holder.remove_btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    list.remove(position);
//                    notifyDataSetChanged();
//                }
//            });
        }else{
            holder.imageView.setImageBitmap(bitmaps.get(position));
//            holder.remove_btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    bitmaps.remove(position);
//                    notifyDataSetChanged();
//                }
//            });
        }

            if (position == 0) {
                holder.imageView.setVisibility(View.INVISIBLE);
//                holder.remove_btn.setVisibility(View.GONE);
            }
    }

    @Override
    public int getItemCount() {
        if(bitmaps == null) {
            return list.size();
        }else{
            return bitmaps.size();
        }

    }

}
