package com.example.Email.Classes;

import java.util.LinkedList;

public class Folder {
    private  String name ;
    private LinkedList<Message> messages ;

    public void setMessages(LinkedList<Message> messages) {
        this.messages = messages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Folder(String name) {
        this.name = name;
        messages=new LinkedList<Message>();
    }
    public void rename(String name){
        this.setName(name);
    }

    public Folder() {
    }

    public void addMessage(Message m){
        String id=m.getId();
        for(Message mes:messages){
            if(mes.getId().equals(id))return;
        }
        this.messages.add(m) ;
    }

    public void removeMessage(String id) {
        LinkedList<Message>list=new LinkedList<>();
        for (Message ma : messages) {
            list.add(ma);
        }
        for (Message ma : list) {
            if (ma.getId().equals(id)) {
                messages.remove(messages.indexOf(ma));
            }
        }
    }

    public boolean haveMessage(String id) {
        for (Message ma : messages) {
            if (ma.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public LinkedList<Message> getMessages() {
        return messages;
    }
}
