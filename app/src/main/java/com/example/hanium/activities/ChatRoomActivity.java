package com.example.hanium.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hanium.R;
import com.example.hanium.classes.messageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class ChatRoomActivity extends AppCompatActivity {
    EditText editText;
    Button sendBtn;
    Socket socket;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);
        editText = findViewById(R.id.chatRoom_EditText);
        sendBtn = findViewById(R.id.chatRoom_sendBtn);
        Gson gson = new GsonBuilder().create();
        try {
            socket = IO.socket("http://15.164.145.19:3001/chat/rooms/");
            socket.connect();
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    socket.emit("enter", gson.toJson("4"));
                }
            });
            Log.d("test","test");

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                socket.emit("message", gson.toJson(new messageInfo(4, "Hi")));
                Log.d("test","test1");
            }
        });
    }
}
