import { Postagem } from './../model/Postagem';

import { environment } from './../../environments/environment.prod';
import { AuthService } from './../service/auth.service';
import { User } from './../model/User';
import { Comunidade } from 'src/app/model/Comunidade';

import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';


import { PostagemService } from '../service/postagem.service';
import { ComunidadeService } from '../service/comunidade.service';
import { UserLogin } from '../model/UserLogin';
import { AlertasService } from '../service/alertas.service';


@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {

  postagem: Postagem = new Postagem()
  listaPostagem: Postagem[]
  listaComunidade : Comunidade[]



  comunidade: Comunidade = new Comunidade()
  listaComunidades: Comunidade[]
  idComunidade: number
  categoriaPost: string

  user: User = new User()
  idUser = environment.id
  tipo = environment.tipo

  ok = true





  constructor(
    private router : Router,
    private route: ActivatedRoute,
    private postagemService: PostagemService,
    private comunidadeService: ComunidadeService,
    private auth: AuthService,
    private alertas: AlertasService
  ) { }

  ngOnInit() {
  window.scrollTo(0,0)

    if(environment.token == ""){
      //alert('Sua seção expirou, faça o login novamente!')
      this.router.navigate(['/entrar'])
    }

    console.log(environment.tipo)

    this.getAllComunidades()
    this.getAllPostagem()
    this.getByIdUser()

    if (this.tipo == 'rec'){
      this.ok = false
    }
  }

  
  enviar(){
    this.comunidade.id = this.idComunidade
    this.postagem.comunidade = this.comunidade

    this.user.id = this.idUser
    this.postagem.usuario = this.user

    this.postagemService.postPostagem(this.postagem).subscribe((resp: Postagem) => {
    this.postagem = resp
    this.alertas.showAlertSuccess('Postagem realizado com sucesso!')
    this.postagem = new Postagem()
    this.getAllPostagem()
    })

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

      console.log(environment.tipo)
    })
  }

  findPostagemByCategoria(){
    if(this.categoriaPost == ''){
      this.getAllPostagem()
    }
    else{
      this.postagemService.getByCategoriaPostagem(this.categoriaPost).subscribe((resp: Postagem[])=>{
        this.listaPostagem = resp
      })
    }
  }



  getByIdUser(){
    this.auth.getByIdUser(this.idUser).subscribe((resp: User) =>{
      this.user = resp

    })
  }


  InscreverPostagem() {
    this.comunidade.id = this.idComunidade
    this.postagem.comunidade = this.comunidade

    this.postagemService.putPostagem(this.postagem).subscribe((resp: Postagem) => {
      this.postagem = resp
      this.router.navigate(['/inicio'])
      this.alertas.showAlertInfo("Atualização completa!")


    })
  }
}
