import {Component, OnInit} from '@angular/core';
import {Folders} from "../folders";
import {OperationsService} from "../operations.service";
import {Router} from "@angular/router";
import {FolderComponent} from "./folder/folder.component";
import {User} from "../user";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  constructor(private router: Router,private service:OperationsService){
  }
  ngOnInit(): void {
    User.mail=JSON.parse(JSON.stringify(localStorage.getItem("mail")));
    User.pass=JSON.parse(JSON.stringify(localStorage.getItem("pass")));
    console.log(User.mail);
    this.service.login(User.mail,User.pass,false);
  }

   static _show: boolean = false;
   static _newFolderName: string = 'NewFolder';
   static _folders: Folders[] = [];


  get show(): boolean {
    return MainComponent._show;
  }

  set show(value: boolean) {
    MainComponent._show = value;
  }

  get newFolderName(): string {
    return MainComponent._newFolderName;
  }

  set newFolderName(value: string) {
    MainComponent._newFolderName = value;
  }

  get folders(): Folders[] {
    return MainComponent._folders;
  }

  set folders(value: Folders[]) {
    MainComponent._folders = value;
  }

  rename:boolean[]=[];
  renameName:string='';
  renameFunc(index:number){
    this.rename[index]=!this.rename[index];
    this.renameName='';
  }

  renameFolder(index:number){
    if(this.renameName.length>0)
      this.service.renameFolder(index,this.renameName);
      else alert("INVALID NAME");
    this.rename[index]=!this.rename[index];
    this.renameName='';
  }
  showFunc() {
    MainComponent._show = !MainComponent._show;
    MainComponent._newFolderName = 'NewFolder';
  }
  addNewFolder() {
    console.log(MainComponent._newFolderName);
    console.log(MainComponent._newFolderName);
    if(MainComponent._newFolderName.length==0){
      alert("NOT VALID NAME");
    } else{
      this.service.addFolder();
    }
    MainComponent._show = !MainComponent._show;
  }

  deleteFolder(index:number){
    this.service.deleteFolder(index);
  }

   change(st:string){
    this.router.navigate(["/main/folder"]);
    FolderComponent.name2=st;
    this.service.getMess(st);
    console.log(st);
  }

  goToContacts(){
    this.router.navigate(["/main/contact"]);
    this.service.getContacts();
  }
  goToCompose(){
    this.router.navigate(["/main/composite"]);
  }
}
