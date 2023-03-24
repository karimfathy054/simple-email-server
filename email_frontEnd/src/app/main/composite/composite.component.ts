import {Component, OnInit} from '@angular/core';
import {Message} from "../../message";
import {User} from "../../user";
import {Router} from "@angular/router";
import {OperationsService} from "../../operations.service";

@Component({
  selector: 'app-composite',
  templateUrl: './composite.component.html',
  styleUrls: ['./composite.component.css']
})
export class CompositeComponent implements OnInit {

  static sto:string;
  static sSubject:string;
  static sContect:string;
  static sImp:number;
  static sAtt:any;
  to: string = "";
  subject: string = "";
  content: string = "";
  importance: number = 0;
  attachment:string ="";
  file: any = null;
  clearr(){
    CompositeComponent.sto="";
    CompositeComponent.sSubject="";
    CompositeComponent.sContect="";
    CompositeComponent.sImp=0;
    CompositeComponent.sAtt="";
  }
  ngOnInit(): void {
    this.to=CompositeComponent.sto;
    this.subject=CompositeComponent.sSubject;
    this.content=CompositeComponent.sContect;
    this.importance=CompositeComponent.sImp;
    this.attachment=CompositeComponent.sAtt;
  }
  con(){
    console.log("hi");
  }

  constructor(private router: Router,private service:OperationsService) {
  }

  create() {
    var ma: Message = new Message(CompositeComponent.makeString(), this.to, User.mail, this.importance, this.subject
      , this.content, new Date().getTime(),this.attachment);///add path of attachment
    console.log(ma);
    if (this.to == ""  || this.content == "" || this.subject == "") {
      alert("please input all detail of message")
    } else {
      console.log(JSON.stringify(ma));
      this.service.addMessage(JSON.stringify(ma));

      this.router.navigate(["/main/folder"]);
    }
    this.clearr();
  }
  sendDraft() {
    var ma: Message = new Message(CompositeComponent.makeString(), this.to, User.mail, this.importance, this.subject
      , this.content, new Date().getTime(),this.attachment);///add path of attachment
    console.log(ma);
    this.service.addMesDraft(JSON.stringify(ma));
    this.router.navigate(["/main/folder"]);
    this.clearr();
  }

  attach1(event: any) {
    this.file =event.target.files[0]
    // this.attachment = ;
    // console.log(this.attachment);
  }

  upload() {
    console.log(this.file);
    this.service.upload(this.file).subscribe((event: any) => {
      if (typeof (event) === 'object') {
        this.attachment = event.link;
        console.log(this.attachment)
      }
    })
    var x = new String();
    console.log(x);
  }

  static makeString(): string {
    let outString: string = '';
    let inOptions: string = 'abcdefghijklmnopqrstuvwxyz0123456789';

    for (let i = 0; i < 11; i++) {
      outString += inOptions.charAt(Math.floor(Math.random() * inOptions.length));
    }
    return outString;
  }

}
