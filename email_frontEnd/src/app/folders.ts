import {Message} from "./message" ;
export class Folders{
  name:string="";
  messages:Message[] = [];

  constructor(name: string, messages: Message[]) {
    this.name = name;
    this.messages = messages;
  }
}
