package com.example.Email.Filter;

import com.example.Email.Classes.Message;

import java.util.LinkedList;

public class NumCriteria implements Filter{
    String attributeName;
    long value;
    public NumCriteria(String attributeName,long value){
        this.attributeName=attributeName;
        this.value=value;
    }
    @Override
    public LinkedList<Message> meetCriteria(LinkedList<Message> list) {
        LinkedList<Message>newList=new LinkedList<>();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getImportance()==value)newList.add(list.get(i));
        }
        return  newList;
    }
}
