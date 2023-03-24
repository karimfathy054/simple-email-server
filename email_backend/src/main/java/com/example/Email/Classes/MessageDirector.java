package com.example.Email.Classes;
import com.example.Email.Model.Model;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

public class MessageDirector {
    private static MessageDirector director=null;
    private Proxy proxy=new Proxy();
    public static MessageDirector getInstance(){
        if(director==null){
            director= new MessageDirector();
        }
        return director;
    }
    Model model = Model.getInstance();

    /*{
    "id":"id",
    "to":"address1,address2,address3", receivers email addresses should be comma separated with no spaces
    "from":"sender address",
    "importance":6,
    "date":date,
    "delete":delete date,
    "subject":"subject",
    "content":"content",
    "attachment":"attachment path"
    }*/
    public void handleMessage(String json_message) throws JsonProcessingException, CloneNotSupportedException {
        Message message = new Message();
        message.fromJson(json_message);
        model.setMessage(message);
        User sender =model.getUser(message.getFrom());
        String z=sender.getEmail();
        sender.addSen(message);
        JSONObject obj = new JSONObject(json_message);
        String to = obj.getString("to");
        List<String> tos = new ArrayList<String>(Arrays.asList(to.split(",")));
        for (String x : tos) {
            if(!proxy.checkExistence(x))continue;
            Message tempMessage= (Message) message.clone();
            tempMessage.setTo(x);
            User tempUser =model.getUser(tempMessage.getTo());
            tempUser.addRes(tempMessage);
        }
    }

    public void handleDraft(String json_message) throws JsonProcessingException {
        Message message = new Message();
        message.fromJson(json_message);
        model.setMessage(message);
        User user =model.getUser(message.getFrom());
        user.addDraft(message);
    }

}
