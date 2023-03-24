package com.example.Email.Filter;

import com.example.Email.Classes.Contact;
import com.example.Email.Classes.Contacts;
import com.example.Email.Classes.Message;

import java.util.Date;
import java.util.LinkedList;

public class FilterOperations {
    public LinkedList<Message> searchOnString(LinkedList<Message> messages, String attributeName, String value){
        Filter filter=new StringCriteria(attributeName,value);
        return filter.meetCriteria(messages);
    }
    public LinkedList<Message> searchOnNum(LinkedList<Message> messages,String attributeName,long value){
        Filter filter=new NumCriteria(attributeName,value);
        return filter.meetCriteria(messages);
    }
    public LinkedList<Message> searchOnDate(LinkedList<Message> messages, String attributeName, long value){
        Filter filter=new DateCriteria(attributeName,value);
        return filter.meetCriteria(messages);
    }
    public Contact searchOnContacts(Contacts contacts, String name){
        return contacts.getContacts().get(name);
    }
}
