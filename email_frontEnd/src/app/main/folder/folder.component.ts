import { Component, OnInit } from '@angular/core';
import {Message} from "../../message";
import {OperationsService} from "../../operations.service";
import {MainComponent} from "../main.component";
import {DisplayComponentComponent} from "../display-component/display-component.component";
import {Folders} from "../../folders";
import {CompositeComponent} from "../composite/composite.component";
import {Router} from "@angular/router";

@Component({
  selector: 'app-folder',
  templateUrl: './folder.component.html',
  styleUrls: ['./folder.component.css']
})
export class FolderComponent implements OnInit {
  static _getcomposeVisible: boolean = false;
  static name2: string;
  searchString: string = '';
  searchAtt: string = 'to';
  foldernamemove: string = "";

  static _counter: number = 1;

  get counter(): number {
    return FolderComponent._counter;
  }

  set counter(value: number) {
    FolderComponent._counter = value;
  }

  get getcomposeVisible(): boolean {
    return FolderComponent._getcomposeVisible;
  }

  set getcomposeVisible(value: boolean) {
    FolderComponent._getcomposeVisible = value;
  }

  static _messages: Message[];
  static _displayarr: Message[] = [];
  get displayarr(): Message[] {
    return FolderComponent._displayarr;
  }

  set displayarr(value: Message[]) {
    FolderComponent._displayarr = value;
  }

  mes: Message[] = [];
  sorter: boolean = false;

  get messages(): Message[] {
    return FolderComponent._messages;
  }

  selected: any[] = [];
  isSelectall: boolean = false;

  constructor(private service: OperationsService, private router: Router) {
  }

  ngOnInit(): void {

  }

  search() {
    // console.log( this.searchDate);
    // console.log(new Date(this.searchDate));
    if (this.searchAtt == 'imp') {
      if (isNaN(Number(this.searchString))) {
        alert("Invalid Input");
        return;
      }
      this.service.searchByImp(FolderComponent.name2, Number(this.searchString));
    } else if (this.searchAtt == 'to') {
      this.service.searchByString(FolderComponent.name2, "receive", this.searchString);
    } else if (this.searchAtt == "from") {
      this.service.searchByString(FolderComponent.name2, "send", this.searchString);
    } else if (this.searchAtt == "subject") {
      this.service.searchByString(FolderComponent.name2, "jj", this.searchString);
    } else {
      this.service.searchByDate(FolderComponent.name2, this.searchString);
    }
  }

  sort() {
    console.log("sorted")
  }

  sortByImp() {
    this.sorter = !this.sorter;
    this.service.sortByImp(FolderComponent.name2, this.sorter);
  }

  sortByto() {
    this.sorter = !this.sorter;
    this.service.sortByto(FolderComponent.name2, this.sorter);
  }

  sortByFrom() {
    this.sorter = !this.sorter;
    this.service.sortByFrom(FolderComponent.name2, this.sorter);
  }


  sortByDate() {
    this.sorter = !this.sorter;
    this.service.sortByDate(FolderComponent.name2, this.sorter);
  }

  deleteselected() {
    let list = [];
    for (var i = 0; i < this.selected.length; i++) {
      if (this.selected[i] == true) {
        list.push(FolderComponent._messages[i+(FolderComponent._counter-1)*6].id);
      }
    }
    this.service.deleteMess(list)
  }

  moveselected() {
    if (this.foldernamemove == FolderComponent.name2) {
      alert("You can`t do that");
      return;
    }
    console.log(this.foldernamemove, MainComponent._folders);
    let tr = false;
    for (let i = 0; i < MainComponent._folders.length; i++) {
      if (this.foldernamemove == MainComponent._folders[i].name) {
        tr = true;
        break;
      }
    }
    if (tr == false) {
      alert("You can`t do that");
      return;
    }
    let list = [];
    for (var i = 0; i < this.selected.length; i++) {
      if (this.selected[i] == true) {
        list.push(FolderComponent._messages[i+(FolderComponent._counter-1)*6].id);
      }
    }
    this.service.moveMes(list, this.foldernamemove);
  }

  selectAll() {
    this.isSelectall = !this.isSelectall
    for (var i = 0; i < FolderComponent._messages.length; i++) {
      if (this.isSelectall == true) {
        this.selected[i] = true
      } else {
        this.selected[i] = false
      }
    }
  }

  convertToDate(date: number) {
    return new Date(date);
  }

  convert(i: number) {
    DisplayComponentComponent._wfrom = this.messages[i].from;
    DisplayComponentComponent._wto = this.messages[i].to;
    DisplayComponentComponent._wsub = this.messages[i].subject;
    DisplayComponentComponent._wcontent = this.messages[i].content;
    DisplayComponentComponent._wdate = new Date(this.messages[i].date);
    DisplayComponentComponent._wimpo = this.messages[i].importance;
    DisplayComponentComponent._att=this.messages[i].attachment;
    console.log(DisplayComponentComponent._att);
    FolderComponent._getcomposeVisible = !FolderComponent._getcomposeVisible;
  }

  choose(i: number) {
    if (FolderComponent.name2 != "draft") {
      this.convert(i);
    } else {
      CompositeComponent.sto = this.messages[i].to;
      CompositeComponent.sSubject = this.messages[i].subject;
      CompositeComponent.sAtt = this.messages[i].attachment;
      CompositeComponent.sImp = this.messages[i].importance;
      CompositeComponent.sContect = this.messages[i].content;
      this.router.navigate(["/main/composite"]);
    }
  }

  checkSearchType() {
    if (this.searchAtt == 'date') return true;
    return false;
  }

  plus() {
    if (FolderComponent._counter * 6 < FolderComponent._messages.length) {
      FolderComponent._counter++;
      FolderComponent._displayarr = [];
      for (let i = (FolderComponent._counter - 1) * 6; i < (FolderComponent._counter - 1) * 6 + 6; i++) {
        if (i >= FolderComponent._messages.length) break;
        FolderComponent._displayarr.push(FolderComponent._messages[i]);
      }
    } else {
      alert("You will out of Constraint ")
    }
  }

  minus() {
    if (FolderComponent._counter == 1) {
      alert("You will out of Constraint ")
    } else {
      FolderComponent._counter--;
      FolderComponent._displayarr = [];
      for (let i = (FolderComponent._counter - 1) * 6; i < (FolderComponent._counter - 1) * 6 + 6; i++) {
        if (i >= FolderComponent._messages.length) break;
        FolderComponent._displayarr.push(FolderComponent._messages[i]);
      }

    }
  }
}
