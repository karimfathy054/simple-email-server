import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {MainComponent} from "./main/main.component";
import {CompositeComponent} from "./main/composite/composite.component";
import {ContactComponent} from "./main/contact/contact.component";
import {FolderComponent} from "./main/folder/folder.component";


const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'main', component: MainComponent,
    children: [
      {path: 'composite', component: CompositeComponent},
      {path: 'contact', component: ContactComponent},
      {path: 'folder', component: FolderComponent},
    ]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

export const routingComponents =
  [CompositeComponent, LoginComponent, MainComponent];
