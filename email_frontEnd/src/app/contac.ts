export class Contac {
  contactName: string = "";
  emails: string[] = [];

  constructor(contactName: string, emails: string[]) {
    this.contactName = contactName;
    this.emails = emails;
  }
}
