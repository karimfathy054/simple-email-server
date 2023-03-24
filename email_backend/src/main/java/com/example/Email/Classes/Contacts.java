package com.example.Email.Classes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class Contacts {
    public HashMap<String,Contact> contacts =new HashMap<>();

    public HashMap<String, Contact> getContacts() {
        return contacts;
    }

    public void setContacts(HashMap<String, Contact> contacts) {
        this.contacts = contacts;
    }

    public Contacts() {
    }

    public void addContact(String contactName, List<String> email){
        Contact temp =new Contact(contactName);
        for (String x :email){
            temp.addEmail(x);
        }
        contacts.put(temp.getContactName(), temp);
    }

    public void deleteContact (String contactName){
        contacts.remove(contactName);
    }
    public void editContactName(String oldName,String newName){
        Contact temp =contacts.get(oldName);
        temp.setContactName(newName);
        contacts.remove(oldName);
        contacts.put(newName,temp);
    }
    public void editContactInfo(String contactName,String editedEmail,String newEmail){
        Contact temp =contacts.get(contactName);
        temp.deleteEmail(editedEmail);
        temp.addEmail(newEmail);
    }
    public void editContact(String json_Contact) throws JsonProcessingException {
        JSONObject obj =new JSONObject(json_Contact);
        Contact temp =contacts.get(obj.getString("contactName"));
        temp.fromJson(json_Contact);

    }
    public void deleteContactInfo(String contactName,String editedEmail){
        Contact temp =contacts.get(contactName);
        temp.deleteEmail(editedEmail);
    }
    public void addNewEmail(String contactName,String newEmail){
        Contact temp =contacts.get(contactName);
        temp.addEmail(newEmail);
    }
}
