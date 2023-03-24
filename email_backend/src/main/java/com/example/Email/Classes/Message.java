package com.example.Email.Classes;

import com.example.Email.Model.Model;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.*;

public class Message {
    private String id ;
    private String to ;
    private String from ;
    private long importance ;
    private long date =new Date().getTime() ;

    private long delete=new Date().getTime();
    private String subject ;
    private String content ;

    private String attachment =null;
    public Message() {
    }

    public Message(String id, String to, String from, int importance,String subject, String content) {
        this.id =id;
        this.to=to;
        this.from=from;
        this.importance=importance;
        this.date=new Date().getTime();
        this.subject=subject;
        this.content=content;
        this.attachment="";

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public long getImportance() {
        return importance;
    }

    public void setImportance(long importance) {
        this.importance = importance;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }
    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
    public long getDelete() {
        return delete;
    }

    public void setDelete(long delete) {
        this.delete = delete;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper =new ObjectMapper();

        String json = mapper.writeValueAsString(this);
        return json;
    }
    public void fromJson(String json) throws JsonProcessingException {
        ObjectMapper mapper =new ObjectMapper();

        Message m = mapper.readValue(json,Message.class);
        this.id =m.getId();
        this.to=m.getTo();
        this.from=m.getFrom();
        this.importance=m.getImportance();
        this.date=m.getDate();
        this.subject=m.getSubject();
        this.content=m.getContent();
        this.attachment=m.getAttachment();
    }
    public Message clone() throws CloneNotSupportedException {
        Message m =new Message();
        m.setId(this.id);
        m.setTo(this.to);
        m.setFrom(this.from);
        m.setImportance(this.importance);
        m.setDate(this.date);
        m.setSubject(this.subject);
        m.setSubject(this.subject);
        m.setContent(this.content);
        m.setAttachment(this.attachment);
        return m;
    }
}