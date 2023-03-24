package com.example.Email.Model;

import com.example.Email.Classes.Message;
import com.example.Email.Classes.Proxy;
import com.example.Email.Classes.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class Model {
    HashMap<String , User> userBase=new HashMap<String,User>();
    HashMap<String , Message> mesBase=new HashMap<String,Message>();
    private static Model model=null;
    protected static ObjectMapper mapper =new ObjectMapper();

    public static Model getInstance(){
            if(model==null){
               model= new Model();
            }
            return model;
    }
    public HashMap<String, User> getUserBase() {
        return userBase;
    }

    public void setUserBase(HashMap<String, User> userBase) {
        this.userBase = userBase;
    }

    public HashMap<String, Message> getMesBase() {
        return mesBase;
    }

    public void setMesBase(HashMap<String, Message> mesBase) {
        this.mesBase = mesBase;
    }
    public void addMessage(String id,Message message){
        mesBase.put(id,message);
    }
    public Message getMessage(String id){
        return mesBase.get(id);
    }
    public void setMessage(Message m) {
         mesBase.put(m.getId(), m);
    }

    public boolean addUser(String mailAddress, User user){
        if(isInUserBase(mailAddress))return false;
        this.userBase.put(mailAddress,user);
        System.out.println("USER IS IN");
        return true;
    }
    public User getUser(String mailAddress){
            User user=this.userBase.get(mailAddress);
            return this.userBase.get(mailAddress);
    }
    public boolean isInUserBase(String mailAddress){
        return this.userBase.containsKey(mailAddress);
    }
    public String load() throws IOException, ParseException {
        JSONParser jsonParser=new JSONParser();

        this.userBase =mapper.readValue((String) jsonParser.parse(new FileReader("userData.json")).toString(),new TypeReference<HashMap<String,User>>(){});

        this.mesBase =mapper.readValue((String) jsonParser.parse(new FileReader("mesData.json")).toString(),new TypeReference<HashMap<String,Message>>(){});
        return this.toString();
    }
    public void save() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        FileWriter f = new FileWriter("userData.json");
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(f, this.userBase);
        f.close();
        FileWriter f2 = new FileWriter("mesData.json");
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(f2, this.mesBase);
        f2.close();
    }

    @Override
    public String toString() {
        return "Model{" +
                "userBase=" + userBase +
                ", mesBase=" + mesBase +
                '}';
    }
}
