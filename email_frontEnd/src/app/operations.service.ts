import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpParams} from "@angular/common/http";
import {Router} from "@angular/router";
import {User} from "./user";
import {MainComponent} from "./main/main.component";
import {Folders} from "./folders";
import {FolderComponent} from "./main/folder/folder.component";
import {ContactComponent} from "./main/contact/contact.component";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class OperationsService {
  private url ='http://localhost:8080';
  constructor(private router: Router,private http: HttpClient) {}
  login(mail:string,pass:string,tr:boolean){
    this.http.get(this.url+"/"+"logIn?mailAddress="+mail+"&pass="+pass, {responseType:"text"}).subscribe((result:any)=>{
      console.log(result);
      if(result=="NON"){
          alert(
            "WRONG EMAIL OR WRONG PASSWORD TRY AGAIN"
          )
        }else{
          localStorage.setItem("mail",mail);
          localStorage.setItem("pass",pass);
          User.mail=mail;
          User.pass=pass;
          let resu=JSON.parse(result);
          for(let i=0;i<resu.length;i++){
            MainComponent._folders[i]=new Folders(resu[i],[]);
          }
          FolderComponent.name2="allmail";
          console.log(result,  MainComponent._folders);
          if(tr)
             this.router.navigate(["/main/folder"]);
          this.getMess("allmail");
        }
    },(error:HttpErrorResponse) =>{
      alert(error.message);
    });
  }
  signUp(mail:string,pass:string){
    let queryParams = new FormData();
    queryParams.append("mailAddress",mail);
    queryParams.append("pass",pass);
    this.http.post(this.url+"/"+"signUp",queryParams,{responseType:"text"}).subscribe((result:any)=>{
      console.log(result);
      if((result) as string =="NON"){
        alert(
          "USED MAIL EMAIL TRY AGAIN"
        )
      }else{
        localStorage.setItem("mail",mail);
        localStorage.setItem("pass",pass);
        User.mail=mail;
        User.pass=pass;
        FolderComponent.name2="allmail";
        this.router.navigate(["/main/folder"]);
      }
    },(error:HttpErrorResponse) =>{
      alert(error.message);
    });
  }
  addMessage(message:any){
    let queryParams = new FormData();
    queryParams.append("message",message);
    this.http.post(this.url+"/"+"addMessages",queryParams,{responseType:"text"}).subscribe();
  }
  addMesDraft(message:any){
    let queryParams = new FormData();
    queryParams.append("message",message);
    this.http.post(this.url+"/"+"addMesDraft",queryParams,{responseType:"text"}).subscribe();
  }
  addFolder(){
    let queryParams = new FormData();
    queryParams.append("mailAddress",User.mail);
    queryParams.append("folderName",MainComponent._newFolderName);
    this.http.post(this.url+"/"+"addFolder",queryParams,{responseType:"text"}).subscribe((result:any)=>{
          console.log(MainComponent._newFolderName);
           if(result=='YES'){
             MainComponent._folders.push(new Folders(MainComponent._newFolderName,[]));
           } else {
             alert("There Is Folder With Same Name");
           }
           MainComponent._newFolderName = 'New Folder';
    },(error:HttpErrorResponse) =>{
      alert(error.message);
    });

  }
  renameFolder(oldName:number,newName:string) {
    let queryParams = new FormData();
    queryParams.append("mailAddress",User.mail);
    queryParams.append("oldName",MainComponent._folders[oldName].name);
    queryParams.append("newName",newName);
    this.http.post(this.url + "/renameFolder",queryParams, {responseType: "text"}).subscribe((result: any) => {
      console.log(result);
      if (result == 'YES') {
            MainComponent._folders[oldName].name = newName;

      } else {
        alert("Can`t Confirm Operation");
      }
    });
  }
  deleteFolder(index:number){
    this.http.delete(this.url+"/deleteFolder",{params:{mailAddress:User.mail,name:MainComponent._folders[index].name}}).subscribe((result:any)=>{
      MainComponent._folders.splice(index,1);
    });
  }

  getMess(foldName:string){
    // http://localhost:8080/reload?mail=ahmed&foldName=adasd
    this.http.get(this.url+"/reload?mail="+User.mail+"&foldName="+foldName,{responseType:"text"}).subscribe((result:any)=>{
      console.log(result);
      FolderComponent._messages=JSON.parse(result);

      FolderComponent._displayarr=[];
      for (let i=(FolderComponent._counter-1)*6;i<(FolderComponent._counter-1)*6+6;i++) {
        if (i >= FolderComponent._messages.length) break;
        FolderComponent._displayarr.push(FolderComponent._messages[i]);
      }
    })
  }

  sortByImp(foldName:string,sorter:boolean){
    this.http.get(this.url+"/sortImp?mailAddress="+User.mail+"&folderName="+foldName,{responseType:"text"}).subscribe((result:any)=>{
      console.log(result);
      FolderComponent._messages=JSON.parse(result);
      if(sorter){
        FolderComponent._messages.reverse();
      }
      FolderComponent._displayarr=[];
      for (let i=(FolderComponent._counter-1)*6;i<(FolderComponent._counter-1)*6+6;i++) {
        if (i >= FolderComponent._messages.length) break;
        FolderComponent._displayarr.push(FolderComponent._messages[i]);
      }
    })
  }
  sortByto(foldName:string,sorter:boolean){
    this.http.get(this.url+"/sortTo?mailAddress="+User.mail+"&folderName="+foldName,{responseType:"text"}).subscribe((result:any)=>{
      console.log(result);
      FolderComponent._messages=JSON.parse(result);
      if(sorter){
        FolderComponent._messages.reverse();
      }
      FolderComponent._displayarr=[];
      for (let i=(FolderComponent._counter-1)*6;i<(FolderComponent._counter-1)*6+6;i++) {
        if (i >= FolderComponent._messages.length) break;
        FolderComponent._displayarr.push(FolderComponent._messages[i]);
      }
    })
  }
  sortByFrom(foldName:string,sorter:boolean){
    this.http.get(this.url+"/sortFrom?mailAddress="+User.mail+"&folderName="+foldName,{responseType:"text"}).subscribe((result:any)=>{
      console.log(result);
      FolderComponent._messages=JSON.parse(result);
      if(sorter){
        FolderComponent._messages.reverse();
      }
      FolderComponent._displayarr=[];
      for (let i=(FolderComponent._counter-1)*6;i<(FolderComponent._counter-1)*6+6;i++) {
        if (i >= FolderComponent._messages.length) break;
        FolderComponent._displayarr.push(FolderComponent._messages[i]);
      }
    })
  }
  sortByDate(foldName:string,sorter:boolean){
    this.http.get(this.url+"/sortDate?mailAddress="+User.mail+"&folderName="+foldName,{responseType:"text"}).subscribe((result:any)=>{
      console.log(result);
      FolderComponent._messages=JSON.parse(result);
      if(sorter){
        FolderComponent._messages.reverse();
      }
      FolderComponent._displayarr=[];
      for (let i=(FolderComponent._counter-1)*6;i<(FolderComponent._counter-1)*6+6;i++) {
        if (i >= FolderComponent._messages.length) break;
        FolderComponent._displayarr.push(FolderComponent._messages[i]);
      }
    })
  }
  deleteMess(list:String[]) {
    var z: string = JSON.stringify(list);
    this.http.delete(this.url + "/delMessages", {params: { json:z, use: User.mail, fol: FolderComponent.name2,},responseType:"text"}).subscribe((result: any) => {
      console.log(result);
      if(result=="[]"){
        FolderComponent._messages=[];
      }else
      FolderComponent._messages=JSON.parse(result);
      FolderComponent._displayarr=[];
      for (let i=(FolderComponent._counter-1)*6;i<(FolderComponent._counter-1)*6+6;i++) {
        if (i >= FolderComponent._messages.length) break;
        FolderComponent._displayarr.push(FolderComponent._messages[i]);
      }
    });
  }
  moveMes(list:String[],neww:string) {
    var z: string = JSON.stringify(list);
    let queryParams = new FormData();
    queryParams.append("list",z);
    queryParams.append("use",User.mail);
    queryParams.append("to",neww);
    queryParams.append("from",FolderComponent.name2);
    this.http.post(this.url + "/moveMessages",queryParams, {responseType: "text"}).subscribe((result: any) => {
      console.log(result);
      if(result=="[]"){
        FolderComponent._messages=[];
      }else
        FolderComponent._messages=JSON.parse(result);
      FolderComponent._displayarr=[];
      for (let i=(FolderComponent._counter-1)*6;i<(FolderComponent._counter-1)*6+6;i++) {
        if (i >= FolderComponent._messages.length) break;
        FolderComponent._displayarr.push(FolderComponent._messages[i]);
      }
    });
  }
  searchByImp(foldName:string,imp:number){
    console.log(foldName);
    this.http.get(this.url+"/searchImportance?mailAddress="+User.mail+"&folderName="+foldName+"&imp="+imp,{responseType:"text"}).subscribe((result:any)=>{
      console.log(result);
      FolderComponent._messages=JSON.parse(result);
      FolderComponent._displayarr=[];
      for (let i=(FolderComponent._counter-1)*6;i<(FolderComponent._counter-1)*6+6;i++) {
        if (i >= FolderComponent._messages.length) break;
        FolderComponent._displayarr.push(FolderComponent._messages[i]);
      }

    })
  }
  searchByString(foldName:string,str:string,attribute:string){
    console.log(foldName);
    this.http.get(this.url+"/searchString?mailAddress="+User.mail+"&folderName="+foldName+"&str="+str+"&attribute="+attribute,{responseType:"text"}).subscribe((result:any)=>{
      console.log(result);
      FolderComponent._messages=JSON.parse(result);
      FolderComponent._displayarr=[];
      for (let i=(FolderComponent._counter-1)*6;i<(FolderComponent._counter-1)*6+6;i++) {
        if (i >= FolderComponent._messages.length) break;
        FolderComponent._displayarr.push(FolderComponent._messages[i]);
      }
    })
  }
  searchByDate(foldName:string,str:string){
    console.log(new Date(str).getTime());
    this.http.get(this.url+"/searchDate?mailAddress="+User.mail+"&folderName="+foldName+"&date="+new Date(str).getTime(),{responseType:"text"}).subscribe((result:any)=>{
      console.log(result);
      FolderComponent._messages=JSON.parse(result);
      FolderComponent._displayarr=[];
      for (let i=(FolderComponent._counter-1)*6;i<(FolderComponent._counter-1)*6+6;i++) {
        if (i >= FolderComponent._messages.length) break;
        FolderComponent._displayarr.push(FolderComponent._messages[i]);
      }
    })
  }
  getContacts(){
    this.http.get(this.url+"/getContacts?mail="+User.mail,{responseType:"text"}).subscribe((result:any)=>{
      console.log(result);
      ContactComponent._contacts=JSON.parse(result);
      ContactComponent._contacts.reverse();
      FolderComponent._displayarr=[];
      for (let i=(FolderComponent._counter-1)*6;i<(FolderComponent._counter-1)*6+6;i++) {
        if (i >= FolderComponent._messages.length) break;
        FolderComponent._displayarr.push(FolderComponent._messages[i]);
      }
    })
  }
  addContacts(str:string){
    let queryParams = new FormData();
    queryParams.append("user",User.mail);
    queryParams.append("json_Contact",str);
    this.http.post(this.url + "/addContact",queryParams, {responseType: "text"}).subscribe((result: any) => {
      console.log(result);
      if (result == 'NON') {
        alert("Can`t Confirm Operation");
      } else {
        this.getContacts();
      }
    });
  }
  addEmail(str:string){
    let queryParams = new FormData();
    queryParams.append("user",User.mail);
    queryParams.append("json_editedContact",str);
    this.http.post(this.url + "/editContactinfo",queryParams, {responseType: "text"}).subscribe((result: any) => {
      console.log("hi");
        this.getContacts();
    });
  }
  deleteContact(name:string){
    console.log("hi");
    this.http.delete(this.url + "/deleteContact", {params: { user: User.mail, contactName: name},responseType:"text"}).subscribe((result:any)=>{});
  }
  renameContact(old:string,neww:string){
    let queryParams = new FormData();
    queryParams.append("user",User.mail);
    queryParams.append("newName",neww);
    queryParams.append("oldName",old);
    this.http.post(this.url + "/renameContact",queryParams, {responseType: "text"}).subscribe((result: any) => {
    });
  }
  searchContacts(name:string){
    console.log(name);
    this.http.get(this.url+"/searchContact?user="+User.mail+"&contactName="+name,{responseType:"text"}).subscribe((result:any)=>{
      console.log(result);
      ContactComponent._contacts=JSON.parse(result);
      ContactComponent._contacts.reverse();
    })
  }
  upload(file:any): Observable<any>{
    const formData = new FormData();
    formData.append("file", file, file.name);
    return this.http.post("https://file.io", formData)
  }

}
