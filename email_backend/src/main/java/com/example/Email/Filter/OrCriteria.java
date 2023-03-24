package com.example.Email.Filter;

import com.example.Email.Classes.Message;

import java.util.LinkedList;

public class OrCriteria implements Filter{
    private Filter first;
    private Filter second;

    public OrCriteria(Filter first, Filter second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public LinkedList<Message> meetCriteria(LinkedList<Message> list) {
        LinkedList<Message>f=first.meetCriteria(list),s=second.meetCriteria(list);
        for(Message mes:f){
            if(!s.contains(mes)){
                s.add(mes);
            }
        }
        return s;
    }
}
