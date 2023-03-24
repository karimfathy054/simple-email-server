package com.example.Email.Classes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.List;

public class User {
    private String email;
    private String password;
    private Contacts contacts = new Contacts();
    private Collection collection = new Collection();


    public User(String faris, String s) {
        this.email=faris;
        this.password=s;
    }

    public String getPassword() {
        return password;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean addContact(String contactName, List<String> email) {
        contacts.addContact(contactName, email);
        return true;
    }

    public void deleteContact(String contactName) {
        contacts.deleteContact(contactName);
    }

    public void editContactName(String oldName, String newName) {
        contacts.editContactName(oldName, newName);
    }

    /*public void editContactInfo(String contactName, String editedEmail, String newEmail) {
        contacts.editContactInfo(contactName, editedEmail, newEmail);
    }*/
    public void editContactInfo(String json_contact) throws JsonProcessingException {
        contacts.editContact(json_contact);
    }

    public void deleteContactInfo(String contactName, String editedEmail) {
        contacts.deleteContactInfo(contactName, editedEmail);
    }

    public void addNewEmail(String contactName, String newEmail) {
        contacts.addNewEmail(contactName, newEmail);
    }

    public void addSen(Message message) {
        collection.ad_mes("sent", message);
        collection.ad_mes("allmail", message);
    }

    public void addRes(Message message) {
        collection.ad_mes("inbox", message);
        collection.ad_mes("allmail", message);
    }

    public void deleteMes(String folder, Message message) {
        collection.Del_mes(folder, message);
    }

    public void moveMes(String to, String from, Message message) {
        collection.Move_mes(to, from, message);
    }

    public void addDraft(Message message) {
        collection.ad_mes("draft", message);
    }

    public boolean addFolder(String folder) {

        return collection.addFolder(folder);
    }

    public void deleteFolder(String folder) {
        collection.DelFolder(folder);
    }

    public boolean renameFolder(String oldName, String newName) {
        return collection.RenameF(oldName, newName);
    }

    public User() {
    }

    public String toJson() throws JsonProcessingException {
         ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    public void fromJson(String json) throws JsonProcessingException {
         ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(json, User.class);
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.contacts = user.getContacts();
        this.collection = user.getCollection();
    }


}
