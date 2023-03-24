import { Component, OnInit } from '@angular/core';

import {Contac} from "../../contac";
import {OperationsService} from "../../operations.service";
import {Router} from "@angular/router";
import {CompositeComponent} from "../composite/composite.component";
@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})

export class ContactComponent implements OnInit {
  static _contacts:Contac[];
  searchString:string='';
  newContact:string='';
  newEmail:string[]=[];
  newName:string[]=[];
  get contacts(): Contac[] {
    return ContactComponent._contacts;
  }

  set contacts(value: Contac[]) {
    ContactComponent._contacts = value;
  }

  selected:any[]=[];
  constructor(private service:OperationsService,private router: Router) { }

  ngOnInit(): void {
  }
  addContact(){
    if(this.newContact.length==0){
      alert("Invalid Name");
      return;
    }
    this.service.addContacts(JSON.stringify(new Contac(this.newContact,[])));
    this.newContact='';
  }
  addEmail(index:number){
    let tr=false;
    for(let i=0;i<this.contacts[index].emails.length;i++){
      if(this.newEmail[index]==this.contacts[index].emails[i]){
        tr=true;
        break;
      }
    }
    if(this.newEmail[index].length==0||tr){
      this.newEmail[index]="";

      alert("Invalid Email"); return;
    }
    this.contacts[index].emails[this.contacts[index].emails.length]=this.newEmail[index];
    this.service.addEmail(JSON.stringify(this.contacts[index]));
    this.newEmail[index]="";
  }
  deleteContact(index:number){
    console.log(index);
      this.service.deleteContact(ContactComponent._contacts[index].contactName);
      ContactComponent._contacts.splice(index,1);
  }
  renameContact(index:number){
    console.log(this.newName);
    let tr=false;
    for(let i=0;i<ContactComponent._contacts.length;i++){
      if(i!=index&&this.newName[index]==ContactComponent._contacts[i].contactName){
        tr=true;
        break;
      }
    }
    if(tr||this.newName[index].length==0){
      this.newName[index]="";
      alert("Invalid Name");
      return;
    }
    this.service.renameContact(ContactComponent._contacts[index].contactName,this.newName[index]);
    ContactComponent._contacts[index].contactName=this.newName[index];
    this.newName[index]="";
  }
  deleteEmail(index1:number,index2:number){
    ContactComponent._contacts[index1].emails.splice(index2,1);
    this.service.addEmail(JSON.stringify(this.contacts[index1]));
  }

  search(){
    if(this.searchString.length==0){
      alert("Invalid Search Input");
      return;
    }
    this.service.searchContacts(this.searchString);
    this.searchString="";
  }
  goToCompose(str:string){
    CompositeComponent.sto=str;
    this.router.navigate(["/main/composite"]);
  }
}
