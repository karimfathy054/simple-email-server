package com.example.Email.Filter;

import com.example.Email.Classes.Message;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class DateCriteria implements Filter {
    String attributeName;
    Date value;
    public DateCriteria(String attributeName,long value){
        this.attributeName=attributeName;
        this.value=new Date(value);
    }
    @Override
    public LinkedList<Message> meetCriteria(LinkedList<Message> list) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(value);
        int day=calendar.get(Calendar.DAY_OF_MONTH),month=calendar.get(Calendar.MONTH),year=calendar.get(Calendar.YEAR);

        LinkedList<Message>newList=new LinkedList<>();
        for(int i=0;i<list.size();i++){
            Date date=new Date(list.get(i).getDate());
            calendar.setTime(date);
            int day2=calendar.get(Calendar.DAY_OF_MONTH),month2=calendar.get(Calendar.MONTH),year2=calendar.get(Calendar.YEAR);
            if(day==day2&&month==month2&&year2==year)
                newList.add(list.get(i));
        }
        return  newList;
    }
}
