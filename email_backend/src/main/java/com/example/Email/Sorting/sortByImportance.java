package com.example.Email.Sorting;

import com.example.Email.Classes.Message;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class sortByImportance implements command {
    public sortByImportance()
    {

    }
    @Override
    public LinkedList<Message> excute(LinkedList<Message> l) {
        LinkedList<Message> temp = new LinkedList<>();
        Comparator<Message> inpSorter = Comparator.comparing(Message::getImportance);
        PriorityQueue<Message> queue = new PriorityQueue<Message>(inpSorter);
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
