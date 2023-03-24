package com.example.Email.Classes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class Contact {
    String contactName;
    List<String> emails=new ArrayList<>();

    public Contact() {
    }

    public List<String> getEmails() {
        return emails;
    }

    public Contact(String contactName) {
        this.contactName = contactName;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void addEmail(String emailAddress){
        this.emails.add(emailAddress);
    }
    public void deleteEmail(String emailAddress){
        this.emails.remove(emailAddress);
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
    }
    public void fromJson(String json_contact) throws JsonProcessingException {
        ObjectMapper mapper =new ObjectMapper();
        Contact temp = mapper.readValue(json_contact, Contact.class);
        this.contactName = temp.getContactName();
        this.emails =temp.getEmails();
    }

}
