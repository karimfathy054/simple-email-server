package com.example.Email.Sorting;

import com.example.Email.Classes.Message;

import java.util.LinkedList;

public interface command {
    public LinkedList<Message> excute(LinkedList<Message> l);
}
