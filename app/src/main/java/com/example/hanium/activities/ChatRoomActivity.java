package com.example.hanium.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hanium.R;

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
        try {
            socket = IO.socket("http://15.164.145.19:3001");
            socket.connect();
            socket.on(Socket.EVENT_CONNECT, onConnect);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            socket.emit("content", "HI");
            socket.emit("chatRoomId", 4);
        }
    };
}
