package com.example.hanium.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hanium.R;
import com.example.hanium.activities.ChatRoomActivity;
import com.example.hanium.activities.ReviewPopup;
import com.example.hanium.classes.ChatRoomInformation;

import java.util.ArrayList;
import java.util.List;


public class ChatRecyclerAdapter extends RecyclerView.Adapter<ChatRecyclerAdapter.ViewHolder>{
    ArrayList<ChatRoomInformation> list;
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nickname, message;
        ViewHolder(View itemview){
            super(itemview);
            nickname = itemview.findViewById(R.id.chat_nickname);
            message = itemview.findViewById(R.id.chat_message);
        }
    }
    public ChatRecyclerAdapter(ArrayList<ChatRoomInformation> list){
        this.list = list;
    }


    @Override
    public ChatRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_recyclerview_item,parent,false);
        ChatRecyclerAdapter.ViewHolder viewHolder = new ChatRecyclerAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatRecyclerAdapter.ViewHolder holder, int position) {
        holder.message.setText(list.get(position).getLastChat());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ChatRoomActivity.class);
                ContextCompat.startActivity(holder.itemView.getContext(), intent, null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
