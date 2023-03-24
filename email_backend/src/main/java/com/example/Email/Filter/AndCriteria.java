package com.example.Email.Filter;

import com.example.Email.Classes.Message;

import java.util.LinkedList;

public class AndCriteria implements Filter{
    private Filter first;
    private Filter second;

    public AndCriteria(Filter first, Filter second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public LinkedList<Message> meetCriteria(LinkedList<Message> list) {
        LinkedList<Message>l=first.meetCriteria(list);
        return second.meetCriteria(l);
    }
}
