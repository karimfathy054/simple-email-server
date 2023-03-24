package com.example.Email.Filter;

import com.example.Email.Classes.Message;

import java.util.LinkedList;


public interface Filter {
    LinkedList<Message> meetCriteria(LinkedList<Message> list);

}
