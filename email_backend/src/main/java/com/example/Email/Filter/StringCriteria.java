package com.example.Email.Filter;

import com.example.Email.Classes.Message;

import java.util.LinkedList;

public class StringCriteria implements Filter {
    String attributeName;
    String value;
    public StringCriteria(String attributeName,String value){
        this.attributeName=attributeName;
        this.value=value;
    }
    @Override
    public LinkedList<Message> meetCriteria(LinkedList<Message> list) {
        LinkedList<Message>newList=new LinkedList<>();
        for(int i=0;i<list.size();i++){
            if(attributeName.equals("send")){
                if(list.get(i).getFrom().equals(value))newList.add(list.get(i));
            }else if(attributeName.equals("receive")){
                if(list.get(i).getTo().equals(value))newList.add(list.get(i));
            }else {
                if(list.get(i).getSubject().equals(value))newList.add(list.get(i));
            }
        }
        return  newList;
    }
}
