import { Component, OnInit } from '@angular/core';
import {FolderComponent} from "../folder/folder.component";

@Component({
  selector: 'app-display-component',
  templateUrl: './display-component.component.html',
  styleUrls: ['./display-component.component.css']
})
export class DisplayComponentComponent implements OnInit {

  constructor() {
  }



   static _att:string;

   get att(): string {
    return DisplayComponentComponent._att;
  }

   set att(value: string) {
     DisplayComponentComponent._att = value;
  }

  ngOnInit(): void {
  }

  static _wto: string;
  static _wfrom: string;
  static _wsub: string;
  static _wdate:Date;
  set wdate(value: Date) {
    DisplayComponentComponent._wdate = value;
  }
  get wdate() {
    return DisplayComponentComponent._wdate;
  }
  static _wimpo:number;
  get wimpo(): number {
    return DisplayComponentComponent._wimpo;
  }

  set wimpo(value: number) {
    DisplayComponentComponent._wimpo = value;
  }
  get wto(): string {
    return DisplayComponentComponent._wto;
  }

  get wfrom(): string {
    return DisplayComponentComponent._wfrom;
  }

  get wsub(): string {
    return DisplayComponentComponent._wsub;
  }

  get wcontent(): string {
    return DisplayComponentComponent._wcontent;
  }

  set wto(value: string) {
    DisplayComponentComponent._wto = value;
  }

  set wfrom(value: string) {
    DisplayComponentComponent._wfrom = value;
  }

  set wsub(value: string) {
    DisplayComponentComponent._wsub = value;
  }

  set wcontent(value: string) {
    DisplayComponentComponent._wcontent = value;
  }
  static _wcontent: string;
  convert2(){
    console.log("HELLO");
    FolderComponent._getcomposeVisible=!FolderComponent._getcomposeVisible;
  }
}
