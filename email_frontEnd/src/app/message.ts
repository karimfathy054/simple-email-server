export class Message {
  public id: string = "";
  public to: string = "";
  public from: string = "";
  public importance: number = 0;
  public subject: string = "";
  public content: string = "";
  public date: number = new Date().getTime();
  public attachment:string="";

  constructor(id: string, to: string, from: string, importance: number, subject: string, content: string, date: number,attachment:string) {
    this.id = id;
    this.to = to;
    this.from = from;
    this.importance = importance;
    this.subject = subject;
    this.content = content;
    this.date = date;
    this.attachment=attachment;
  }
}
