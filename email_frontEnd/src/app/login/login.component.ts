import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {OperationsService} from "../operations.service";
import {User} from "../user";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  user:string='';
  pass:string='';

  constructor(private router: Router,private service:OperationsService) {
  }

  login(){
    if(this.checkUser()&&this.pass.length>=8){
      this.service.login(this.user,this.pass,true);
    }else {
      console.log(this.user, this.pass);
      alert(
        "INVALID EMAIL OR PASSWORD TRY AGAIN"
      )
    }
  }
  goToNext(){
    this.router.navigate(["/main/folder"]);
  }
  signUp() {
    if(this.checkUser()&&this.pass.length>=8){
      this.service.signUp(this.user,this.pass);
    }else {
      console.log(this.user, this.pass);
      alert(
        "INVALID EMAIL OR PASSWORD TRY AGAIN"
      )
    }
  }
  checkUser(){
    let checker='';
    let cc=false;
    for (let i=0;i<this.user.length;i++){
      if(cc==true&&this.user[i]=='@')return false;

      if(this.user[i]=='@'){
        cc=true;
      }
      if(cc){
        checker+=this.user[i];
      }
    }
    if(checker=="@smart.com")return true;
    else return false;
  }

}
