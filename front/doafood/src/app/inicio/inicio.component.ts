import { environment } from './../../environments/environment.prod';
import { AuthService } from './../service/auth.service';
import { User } from './../model/User';
import { Comunidade } from 'src/app/model/Comunidade';

import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Postagem } from '../model/Postagem';

import { PostagemService } from '../service/postagem.service';
import { ComunidadeService } from '../service/comunidade.service';
import { UserLogin } from '../model/UserLogin';


@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {

  postagem: Postagem = new Postagem()
  listaPostagem: Postagem[]
  
 

  comunidade: Comunidade = new Comunidade()
  listaComunidades: Comunidade[]
  idComunidade: number

  user: User = new User()
  idUser = environment.id


 

  constructor(
    private router : Router,
    private route: ActivatedRoute,
    private postagemService: PostagemService,
    private comunidadeService: ComunidadeService,
    private auth: AuthService
  ) { }

  ngOnInit() {
  window.scrollTo(0,0)

    if(environment.token == ""){
      //alert('Sua seção expirou, faça o login novamente!')
      this.router.navigate(['/entrar'])
    }

    this.getAllComunidades()
    this.getAllPostagem()
    this.getByIdUser()
  }

  enviar(){
    this.comunidade.id = this.idComunidade
    this.postagem.comunidade = this.comunidade
    
    this.user.id = this.idUser
    this.postagem.usuario = this.user

    this.postagemService.postPostagem(this.postagem).subscribe((resp: Postagem) => {
    this.postagem = resp
    alert('Postagem realizado com sucesso!')
    this.postagem = new Postagem()
    this.getAllPostagem()
    })

  }

  atualizarPostagem(){

  }

  getAllComunidades(){

    this.comunidadeService.getAllComunidade().subscribe((resp: Comunidade[]) => {
      this.listaComunidades = resp
    })

  }

  getByIdComunidade(){

    this.comunidadeService.getByIdComunidade(this.idComunidade).subscribe((resp :Comunidade) =>{
      this.comunidade = resp
    })

  }

  getAllPostagem(){
    this.postagemService.getAllPostagens().subscribe((resp: Postagem[]) =>{
      this.listaPostagem = resp
    })
  }



  getByIdUser(){
    this.auth.getByIdUser(this.idUser).subscribe((resp: User) =>{
      this.user = resp

    })
  }


}
