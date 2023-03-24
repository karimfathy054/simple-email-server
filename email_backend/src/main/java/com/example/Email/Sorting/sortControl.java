package com.example.Email.Sorting;

import com.example.Email.Classes.Message;

import java.util.LinkedList;

public class sortControl {
    private command command;

    public void setCommand(command command) {
        this.command = command;
    }
    public LinkedList<Message> excute (LinkedList<Message> l)
    {
        return command.excute(l);
    }

}
