package com.example.healthsystem.models;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Chat implements Serializable {

    @Exclude
    private String uid;

    private String sender;
    private String receiver;
    private String msg;

    public Chat() {
    }

    public Chat(String sender, String receiver, String msg) {
        this.sender = sender;
        this.receiver = receiver;
        this.msg = msg;
    }

    public String getUid() {
        return uid;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getMsg() {
        return msg;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
