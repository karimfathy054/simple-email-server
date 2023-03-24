import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainComponent } from './main/main.component';
import { CompositeComponent } from './main/composite/composite.component';
import { ContactComponent } from './main/contact/contact.component';
import { LoginComponent } from './login/login.component';
import { FolderComponent } from './main/folder/folder.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {OperationsService} from "./operations.service";
import { DisplayComponentComponent } from './main/display-component/display-component.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    MainComponent,
    CompositeComponent,
    ContactComponent,
    FolderComponent,
    DisplayComponentComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,HttpClientModule ,
    FormsModule
  ],
  providers: [OperationsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
