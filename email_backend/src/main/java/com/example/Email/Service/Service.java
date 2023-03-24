package com.example.Email.Service;

import com.example.Email.Classes.*;
import com.example.Email.Filter.*;
import com.example.Email.Model.Model;
import com.example.Email.Sorting.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;

@org.springframework.stereotype.Service
public class Service {


    public void loadDataBase() throws IOException, ParseException {
        Model dataBase = Model.getInstance();
        dataBase.load();
    }

    public void saveDataBase() throws IOException {
        Model dataBase = Model.getInstance();
        dataBase.save();
    }

    public boolean validateLogIn(String mailAddress, String pass) {
        Proxy proxy = new Proxy();

        return proxy.checkPass(mailAddress, pass);
    }

    public boolean validateSignUP(String mailAddress) {
        Proxy proxy = new Proxy();

        return !proxy.checkExistence(mailAddress);
    }

    public String logIn(String mailAddress, String pass) throws JsonProcessingException {
        Model dataBase = Model.getInstance();
        return dataBase.getUser(mailAddress).toJson();
    }
    public String signUp(String mailAddress, String pass) throws JsonProcessingException {
        Model dataBase = Model.getInstance();
        User user=new User(mailAddress,pass);
        dataBase.addUser(mailAddress,user);
        return dataBase.getUser(mailAddress).toJson();
    }

    public String printDataBase() {

        Model dataBase = Model.getInstance();
        return dataBase.toString();
    }

    public boolean addFolder(String email, String name) {
        Model dataBase = Model.getInstance();
        User user = dataBase.getUser(email);
        return user.addFolder(name);
    }

    public boolean renameFolder(String email, String oldF, String newF) {
        Model dataBase = Model.getInstance();
        User user = dataBase.getUser(email);
       return user.renameFolder(oldF, newF);
    }

    public void deleteFolder(String email, String name) {
        Model dataBase = Model.getInstance();
        User user = dataBase.getUser(email);
        user.deleteFolder(name);
    }


    public LinkedList<Message> sortByDate(String email, String name) {
        Model dataBase = Model.getInstance();
        User user = dataBase.getUser(email);
        Collection collection = user.getCollection();
        LinkedList<Message> messages = collection.get_al_folder(name);
        command command = new sortByDate();
        sortControl sort = new sortControl();
        sort.setCommand(command);
        return sort.excute(messages);
    }
    public LinkedList<Message> sortByImportance(String email, String name) {
        Model dataBase = Model.getInstance();
        User user = dataBase.getUser(email);
        Collection collection = user.getCollection();
        LinkedList<Message> messages = collection.get_al_folder(name);
        command command = new sortByImportance();
        sortControl sort = new sortControl();
        sort.setCommand(command);
        return sort.excute(messages);
    }
    public LinkedList<Message> sortByTo(String email, String name) {
        Model dataBase = Model.getInstance();
        User user = dataBase.getUser(email);
        Collection collection = user.getCollection();
        LinkedList<Message> messages = collection.get_al_folder(name);
        command command = new sortByTo();
        sortControl sort = new sortControl();
        sort.setCommand(command);
        return sort.excute(messages);
    }
    public LinkedList<Message> sortByFrom(String email, String name) {
        Model dataBase = Model.getInstance();
        User user = dataBase.getUser(email);
        Collection collection = user.getCollection();
        LinkedList<Message> messages = collection.get_al_folder(name);
        command command = new sortByFrom();
        sortControl sort = new sortControl();
        sort.setCommand(command);
        return sort.excute(messages);
    }

    public LinkedList<Message> searchDate(String email, String name , long value) {
        Model dataBase = Model.getInstance();
        User user = dataBase.getUser(email);
        Collection collection = user.getCollection();
        LinkedList<Message> messages = collection.get_al_folder(name);
        Filter filter =new DateCriteria("",value);
        return filter.meetCriteria(messages);
    }
    public LinkedList<Message> searchImportance(String email, String name ,long value) {
        Model dataBase = Model.getInstance();
        User user = dataBase.getUser(email);
        Collection collection = user.getCollection();
        LinkedList<Message> messages = collection.get_al_folder(name);
        Filter filter =new NumCriteria("",value);
        return filter.meetCriteria(messages);
    }
    public LinkedList<Message> searchString(String email, String name ,String value,String attributeName) {
        Model dataBase = Model.getInstance();
        User user = dataBase.getUser(email);
        Collection collection = user.getCollection();
        LinkedList<Message> messages = collection.get_al_folder(name);
        Filter filter =new StringCriteria(attributeName,value);
        return filter.meetCriteria(messages);
    }

    //  we will get an id of message
    public void delMessages(LinkedList<String> l, String use, String fol) {
        Model dataBase = Model.getInstance();
        User person = dataBase.getUser(use);
        for (String i : l) {
            Message ma = dataBase.getMessage(i);
            person.deleteMes(fol, ma);
        }
    }

    public void moveMessages(LinkedList<String> l, String use, String to, String from) throws CloneNotSupportedException {
        Model dataBase = Model.getInstance();
        User person = dataBase.getUser(use);
        for (String i : l) {
            Message ma =dataBase.getMessage(i).clone();
            person.moveMes(to, from, ma);
        }
    }

    // we should make add message take a json  to handle (multply reciver );
    public void addMessages(String json) throws JsonProcessingException {
        MessageDirector dir = MessageDirector.getInstance();
        try {
            dir.handleMessage(json);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
    public User getPerson(String name) throws JsonProcessingException {
        Model dataBase = Model.getInstance();
        return dataBase.getUser(name);
    }

    public void addMesDraft(String json) throws JsonProcessingException {
        MessageDirector dir = MessageDirector.getInstance();
        dir.handleDraft(json);
    }
    //addCon delete rename search

    public boolean addContact(String user, String json_Contact) throws JsonProcessingException {
        Model dataBase = Model.getInstance();
        JSONObject contact =new JSONObject(json_Contact);
        String contactName = contact.getString("contactName");
        JSONArray emails = contact.getJSONArray("emails");

        LinkedList<String> emailAddresses = new LinkedList<>();
        for (int i = 0; i < emails.length(); i++) {
            emailAddresses.add(emails.getString(i));
        }
        User temp = dataBase.getUser(user);
        if(temp.getContacts().getContacts().containsKey(contactName)){
            return false;
        }
        temp.addContact(contactName, emailAddresses);
        return true;
    }

    public void deleteContact(String user, String contactName) {
        Model dataBase = Model.getInstance();
        User temp = dataBase.getUser(user);
        temp.deleteContact(contactName);
    }

    public void editContactinfo(String user, String json_editedContact) throws JsonProcessingException {
        Model dataBase = Model.getInstance();
        User temp = dataBase.getUser(user);
        temp.editContactInfo(json_editedContact);
    }

    public void renameContact(String user, String newName, String oldName) {
        Model dataBase = Model.getInstance();
        User temp = dataBase.getUser(user);
        temp.editContactName(oldName, newName);
    }

    public Contact searchContact(String user, String contactName) {
        Model dataBase = Model.getInstance();
        User temp = dataBase.getUser(user);
        FilterOperations op = new FilterOperations();
        return op.searchOnContacts(temp.getContacts(), contactName);
    }

}
