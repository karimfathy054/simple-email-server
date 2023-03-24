package com.example.Email.Classes;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class Collection {
    HashMap<String, Folder> folds = new HashMap<>();

    public Collection() {
        folds.put("allmail", new Folder("allmail"));
        folds.put("inbox", new Folder("inbox"));
        folds.put("draft", new Folder("draft"));
        folds.put("sent", new Folder("sent"));
        folds.put("trash", new Folder("trash"));
    }

    public HashMap<String, Folder> getFolds() {
        return folds;
    }

    public void setFolds(HashMap<String, Folder> folds) {
        this.folds = folds;
    }

    public boolean checkFolder(String FolderName){
        if(FolderName.equals("allmail"))return true;
        else if(FolderName.equals("inbox"))return true;
        else if(FolderName.equals("draft"))return true;
        else if(FolderName.equals("trash"))return true;
        else if(FolderName.equals("sent"))return true;
        return false;
    }
    public boolean RenameF(String old, String now) {
        if(checkFolder(old))return false;
        if(folds.containsKey(now))return false;
        folds.get(old).setName(now);
        folds.put(now, folds.get(old));
        folds.remove(old);
        return true;
    }

    public void DelFolder(String name) {
        if (name.equals("allmail") || name.equals("inbox")) {
            System.out.println(" This is main folder cannot del");
        } else if (name.equals("draft") || name.equals("sent")) {
            System.out.println(" This is main folder cannot del");
        } else if (name.equals("trash")) {
            System.out.println(" This is main folder cannot del");
        } else if (folds.containsKey(name)) {
            folds.remove(name);
        } else {
            System.out.println(" folder is not  exisit");
        }
    }

    public boolean addFolder(String name) {
        if (folds.containsKey(name)) {
            System.out.println("Alerady has Folder with same name");
            return false;
        } else {
            Folder temp = new Folder(name);
            folds.put(name, temp);
            return true;
        }
    }

    public void Move_mes(String to, String from, Message a) {
        if(!checkFolder(to)&&!from.equals("draft")){
            if(!checkFolder(from))
            folds.get(from).removeMessage(a.getId());
            
            folds.get(to).addMessage(a);
        }
    }

    public void copy_mes(String to, String from, Message a) {
        folds.get(to).addMessage(a);
    }

    public void ad_mes(String name, Message m) {
        if (folds.containsKey(name)) {
            folds.get(name).addMessage(m);
        } else {
            System.out.println("NO Folder To add");
        }
    }

    public LinkedList<Message> get_al_folder(String name) {
        return folds.get(name).getMessages();
    }

    public void set_al_folder(String name,LinkedList<Message> f) {
        folds.get(name).setMessages(f);
    }
    public void Del_mes(String fol, Message m) {
        if (fol.equals("trash")) {
            System.out.println("The system will del automatic after certain time");
        } else {
            folds.get(fol).removeMessage(m.getId());
            folds.get("trash").addMessage(m);
        }
    }

    public void check() {
        LinkedList<Message> temp = folds.get("trash").getMessages();
        for (Message ma : temp) {
            Date now = new Date();
            if ((now.getTime() - ma.getDelete()) >= 2592000000l) {
                folds.get("trash").removeMessage(ma.getId());
            }
        }
    }
}
