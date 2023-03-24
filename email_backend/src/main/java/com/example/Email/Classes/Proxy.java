package com.example.Email.Classes;

import com.example.Email.Model.Model;

public class Proxy {
    Model dataBase= Model.getInstance();
    public boolean checkPass(String mailAddress,String pass){
        if(checkExistence(mailAddress)){
            if(dataBase.getUser(mailAddress).getPassword().equals(pass)){
                return true;
            }
        }
        return false;
    }
    public boolean checkExistence(String mailAddress){
        return dataBase.isInUserBase(mailAddress);
    }
}
