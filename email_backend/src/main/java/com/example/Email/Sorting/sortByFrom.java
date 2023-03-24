package com.example.Email.Sorting;

import com.example.Email.Classes.Message;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class sortByFrom implements command {
    public sortByFrom()
    {

    }
    @Override
    public LinkedList<Message> excute(LinkedList<Message> l) {
        LinkedList<Message> temp = new LinkedList<>();
        Comparator<Message> fromSorter = Comparator.comparing(Message::getFrom);
        PriorityQueue<Message> queue = new PriorityQueue<Message>(fromSorter);
        for (Message ma : l) {
            queue.add(ma);
        }
        int z = queue.size();
        for (int i = 0; i < z; i++) {
            temp.add(queue.peek());
            queue.poll();
        }
        return temp;
    }
}
