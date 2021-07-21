package com.example.hanium;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class ChatRecyclerAdapter extends RecyclerView.Adapter<ChatRecyclerAdapter.ViewHolder>{
    List<chatinf> list;
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nickname, message;
        ViewHolder(View itemview){
            super(itemview);
            nickname = itemview.findViewById(R.id.chat_nickname);
            message = itemview.findViewById(R.id.chat_message);
        }
    }
    public ChatRecyclerAdapter(List<chatinf> list){
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
        holder.nickname.setText(list.get(position).getNickname());
        holder.message.setText(list.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
