import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastrarComponent } from './cadastrar/cadastrar.component';
import { ComunidadeComponent } from './comunidade/comunidade.component';
import { PostagemDeletComponent } from './edit/postagem-delet/postagem-delet.component';
import { PostagemEditComponent } from './edit/postagem-edit/postagem-edit.component';

import { ComunidadeEditComponent } from './edit/comunidade-edit/comunidade-edit.component';
import { EntrarComponent } from './entrar/entrar.component';
import { InicioComponent } from './inicio/inicio.component';
import { ComunidadeDeleteComponent } from './edit/comunidade-delete/comunidade-delete.component';


const routes: Routes = [

  {path:'', redirectTo: 'entrar', pathMatch:'full'},
  {path:'entrar', component:EntrarComponent},
  {path:'cadastrar', component:CadastrarComponent},
  {path:'inicio', component: InicioComponent},
  {path:'comunidade', component: ComunidadeComponent},
  {path: 'comunidade-edit/:id', component: ComunidadeEditComponent},
  {path: 'comunidade-delete/:id', component: ComunidadeDeleteComponent},
  
  {path: 'postagem-edit/:id', component: PostagemEditComponent},
  {path: 'postagem-delet/:id', component: PostagemDeletComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
