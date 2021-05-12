import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import {HashLocationStrategy, LocationStrategy} from '@angular/common';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import { RodapeComponent } from './rodape/rodape.component';
import { EntrarComponent } from './entrar/entrar.component';
import { CadastrarComponent } from './cadastrar/cadastrar.component';
import { InicioComponent } from './inicio/inicio.component';
import { ComunidadeComponent } from './comunidade/comunidade.component';
import { ComunidadeDeleteComponent} from './edit/comunidade-delete/comunidade-delete.component';
import { PostagemEditComponent } from './edit/postagem-edit/postagem-edit.component';
import { PostagemDeletComponent } from './edit/postagem-delet/postagem-delet.component';

import { ComunidadeEditComponent } from './edit/comunidade-edit/comunidade-edit.component';




@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    RodapeComponent,
    EntrarComponent,
    CadastrarComponent,
    InicioComponent,
    ComunidadeComponent,
    PostagemEditComponent,
    PostagemDeletComponent,
    ComunidadeDeleteComponent,
    ComunidadeEditComponent,
    ComunidadeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    {
      provide : LocationStrategy,
      useClass : HashLocationStrategy
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
