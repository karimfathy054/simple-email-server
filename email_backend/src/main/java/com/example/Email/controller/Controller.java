package com.example.Email.controller;

import com.example.Email.Classes.Contact;

import com.example.Email.Classes.User;
import com.example.Email.Model.Model;
import com.example.Email.Service.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import org.springframework.core.io.Resource;


import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


import java.io.FileNotFoundException;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
public class Controller {
    Service service;

    @Autowired
    public Controller(Service service) {
        this.service = service;
    }

    @GetMapping("load")
    public void load() throws IOException, ParseException {
        service.loadDataBase();
    }

    @GetMapping("save")
    public void save() throws IOException {
        service.saveDataBase();
    }

    @GetMapping("print")
    public ResponseEntity<String> print() throws IOException, ParseException {
        return new ResponseEntity<String>(service.printDataBase(), HttpStatus.OK);
    }

    @GetMapping("reload")
    public ResponseEntity<String> reload(@RequestParam String mail, @RequestParam String foldName) throws JsonProcessingException {
        ObjectMapper mapper =new ObjectMapper();
        User user=service.getPerson(mail);
        String json = mapper.writeValueAsString(service.getPerson(mail).getCollection().get_al_folder(foldName));

        return new ResponseEntity<String>(json,HttpStatus.OK);
    }
    @GetMapping("logIn")
    public ResponseEntity<String>logIn(@RequestParam String mailAddress,@RequestParam String pass) throws JsonProcessingException {
        if(service.validateLogIn(mailAddress,pass)){
            service.logIn(mailAddress,pass);
            User user=Model.getInstance().getUser(mailAddress);
            user.getCollection().check();
            LinkedList<String>list=new LinkedList<String>();
            for(String st: user.getCollection().getFolds().keySet()){
                if(user.getCollection().checkFolder(st))continue;
                    list.add(st);
            }
            ObjectMapper mapper =new ObjectMapper();
            String json = mapper.writeValueAsString(list);
            return new ResponseEntity<String>(json,HttpStatus.OK);
        }else return new ResponseEntity<String>("NON",HttpStatus.OK);
    }

    @PostMapping("signUp")
    public String signUp(@RequestParam String mailAddress,@RequestParam String pass) throws JsonProcessingException {
        if(service.validateSignUP(mailAddress)){
            service.signUp(mailAddress,pass);
            return "YES";
        }else return "NON";
    }
    @PostMapping("addFolder")
    public String addFolder(@RequestParam String mailAddress,@RequestParam String folderName){
        if(service.addFolder(mailAddress,folderName))
            return "YES";
        return "NO";
    }
    @PostMapping("renameFolder")
    public String renameFolder(@RequestParam String mailAddress,@RequestParam String oldName,@RequestParam String newName){
        if(service.renameFolder(mailAddress,oldName,newName))return "YES";
        else return "NO";
    }
    @DeleteMapping("deleteFolder")
    public void deleteFolder(@RequestParam String mailAddress,@RequestParam String name){
        service.deleteFolder(mailAddress,name);
    }
    @PostMapping("addMessages")
    public void addMessages(@RequestParam String message) throws JsonProcessingException {
        service.addMessages(message);
    }

    @PostMapping("addMesDraft")
    public void addMesDraft(@RequestParam String message) throws JsonProcessingException {
        service.addMesDraft(message);
    }
    @GetMapping("sortDate")
    public ResponseEntity<String> sortByDate(@RequestParam String mailAddress,@RequestParam String folderName) throws JsonProcessingException {
        ObjectMapper mapper =new ObjectMapper();
        String json = mapper.writeValueAsString(service.sortByDate(mailAddress,folderName));
        return new ResponseEntity<String>(json,HttpStatus.OK);
    }
    @GetMapping("sortImp")
    public  ResponseEntity<String>sortByImportance(@RequestParam String mailAddress,@RequestParam String folderName) throws JsonProcessingException {
        ObjectMapper mapper =new ObjectMapper();
        String json = mapper.writeValueAsString(service.sortByImportance(mailAddress,folderName));
        return new ResponseEntity<String>(json,HttpStatus.OK);
    }
    @GetMapping("sortTo")
    public  ResponseEntity<String> sortByTo(@RequestParam String mailAddress,@RequestParam String folderName) throws JsonProcessingException {
        ObjectMapper mapper =new ObjectMapper();
        String json = mapper.writeValueAsString(service.sortByTo(mailAddress,folderName));
        return new ResponseEntity<String>(json,HttpStatus.OK);
    }
    @GetMapping("sortFrom")
    public  ResponseEntity<String> sortByFrom(@RequestParam String mailAddress,@RequestParam String folderName) throws JsonProcessingException {
        ObjectMapper mapper =new ObjectMapper();
        String json = mapper.writeValueAsString(service.sortByFrom(mailAddress,folderName));
        return new ResponseEntity<String>(json,HttpStatus.OK);
    }
    @DeleteMapping("delMessages")
    public ResponseEntity<String>  delMessages(@RequestParam String json,@RequestParam String use,@RequestParam String fol) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        LinkedList<String> temp = mapper.readValue(json, new TypeReference<LinkedList<String>>(){});
        service.delMessages(temp, use, fol);
        User user=service.getPerson(use);
        String json2 = mapper.writeValueAsString(service.getPerson(use).getCollection().get_al_folder(fol));

        return new ResponseEntity<String>(json2, HttpStatus.OK);
    }

    @PostMapping("moveMessages")
    public  ResponseEntity<String>   moveMessages(@RequestParam String list,@RequestParam String use,@RequestParam String to,@RequestParam String from) throws JsonProcessingException, CloneNotSupportedException {
        ObjectMapper mapper = new ObjectMapper();
        LinkedList<String> temp = mapper.readValue(list, new TypeReference<LinkedList<String>>(){});
        service.moveMessages(temp,use,to,from);
        User user=service.getPerson(use);
        String json2 = mapper.writeValueAsString(service.getPerson(use).getCollection().get_al_folder(from));
        return new ResponseEntity<String>(json2, HttpStatus.OK);
    }

    @GetMapping("searchDate")
    public ResponseEntity<String> searchDate(@RequestParam String mailAddress, @RequestParam String folderName, @RequestParam long date) throws JsonProcessingException {
        ObjectMapper mapper =new ObjectMapper();
        String json = mapper.writeValueAsString(service.searchDate(mailAddress,folderName,date));
        return new ResponseEntity<String>(json,HttpStatus.OK);

    }
    @GetMapping("searchImportance")
    public ResponseEntity<String> searchImportance(@RequestParam String mailAddress, @RequestParam String folderName, @RequestParam long imp) throws JsonProcessingException {
        ObjectMapper mapper =new ObjectMapper();
        String json = mapper.writeValueAsString(service.searchImportance(mailAddress,folderName,imp));
        return new ResponseEntity<String>(json,HttpStatus.OK);
    }
    @GetMapping("searchString")
    public ResponseEntity<String> searchString(@RequestParam String mailAddress, @RequestParam String folderName, @RequestParam String str,@RequestParam String attribute) throws JsonProcessingException {
        ObjectMapper mapper =new ObjectMapper();
        String json = mapper.writeValueAsString(service.searchString(mailAddress,folderName,attribute,str));
        return new ResponseEntity<String>(json,HttpStatus.OK);
    }
    @GetMapping("getContacts")
    public ResponseEntity<String>getContacts(@RequestParam String mail) throws JsonProcessingException {
        User user=Model.getInstance().getUser(mail);
        ObjectMapper mapper =new ObjectMapper();
        String json = mapper.writeValueAsString(user.getContacts().getContacts().values());
        return new ResponseEntity<String>(json,HttpStatus.OK);
    }
    @PostMapping("addContact")
    public ResponseEntity<String> addContact(@RequestParam String user,@RequestParam String json_Contact) throws JsonProcessingException {
        if(service.addContact(user,json_Contact)){
            return new ResponseEntity<String>("YES",HttpStatus.OK);
        }
        return new ResponseEntity<String>("NON",HttpStatus.OK);

    }

    @PostMapping("editContactinfo")
    public ResponseEntity<String> editContactinfo(@RequestParam String user,@RequestParam String json_editedContact) throws JsonProcessingException {
        service.editContactinfo(user,json_editedContact);
        return new ResponseEntity<String>("NON",HttpStatus.OK);
    }
    @DeleteMapping("deleteContact")
    public void deleteContact(@RequestParam String user,@RequestParam String contactName)
    {
        service.deleteContact(user,contactName);
    }
    @PostMapping("renameContact")
    public void renameContact(@RequestParam String user,@RequestParam String newName,@RequestParam String oldName){
        service.renameContact(user,newName,oldName);
    }
    @GetMapping("searchContact")
    public ResponseEntity<String> searchContact(@RequestParam String user,@RequestParam  String contactName) throws JsonProcessingException {
        ObjectMapper mapper =new ObjectMapper();
        LinkedList<Contact>list=new LinkedList<>();
        list.add(service.searchContact(user,contactName));
        String json = mapper.writeValueAsString(list);
        return new ResponseEntity<String>(json,HttpStatus.OK);
    }
//    String dir="F:\\";
//    @PostMapping("upload")
//    public ResponseEntity<List<String>> uploadFiles(@RequestParam("mail") String mail,@RequestParam("files")List<MultipartFile> multipartFiles) throws IOException {
//        List<String> filenames = new ArrayList<>();
//        for(MultipartFile file : multipartFiles) {
//            String filename = StringUtils.cleanPath(file.getOriginalFilename());
//            Path fileStorage = get(dir, filename).toAbsolutePath().normalize();
//            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
//            filenames.add(filename);
//        }
//        return ResponseEntity.ok().body(filenames);
//    }
//    @GetMapping("download/{filename}")
//    public ResponseEntity<Resource> downloadFiles(@PathVariable("filename") String filename) throws IOException {
//        Path filePath = get(dir).toAbsolutePath().normalize().resolve(filename);
//        if(!Files.exists(filePath)) {
//            throw new FileNotFoundException(filename + " was not found on the server");
//        }
//        Resource resource = (Resource) new UrlResource(filePath.toUri());
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("File-Name", filename);
//        httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
//        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
//                .headers(httpHeaders).body(resource);
//    }

}
