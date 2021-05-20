import { PostagemService } from 'src/app/service/postagem.service';

import { Component, OnInit } from '@angular/core';
import { Postagem } from 'src/app/model/Postagem';
import { User } from 'src/app/model/User';
import { environment } from 'src/environments/environment.prod';
import { ActivatedRoute, Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { Comunidade } from 'src/app/model/Comunidade';
import { AlertasService } from 'src/app/service/alertas.service';
import { sortBy } from 'sort-by-typescript';
import { ComunidadeService } from 'src/app/service/comunidade.service';

@Component({
  selector: 'app-comunidade-view',
  templateUrl: './comunidade-view.component.html',
  styleUrls: ['./comunidade-view.component.css']
})
export class ComunidadeViewComponent implements OnInit {



  idComunidade: number
  listComunidadePostagem : Postagem[]
  listaComunidades: Comunidade[]
  novalista: Comunidade[]


  key = 'data'
  reverse = true

  comunidade: Comunidade = new Comunidade()


  postagem: Postagem = new Postagem()
  user: User = new User()
  idUser = environment.id
  idUserLogado = environment.id

  nomeUserLogado = environment.nome



  constructor(

    private ComunidadeService: ComunidadeService,
    private router: Router,
    private route: ActivatedRoute,
    private sanitizer: DomSanitizer,
    private alertas: AlertasService
    
  ) { }

  ngOnInit() {
    window.scroll(0,0)

    if(environment.token == ''){
      
      this.router.navigate(['/inicio'])
    }

    this.idComunidade = this.route.snapshot.params['id']
    this.findByIdComunidade(this.idComunidade)
    this.findAllTemas()
  }

  findByIdComunidade(id: number){
    this.ComunidadeService.getByIdComunidade(id).subscribe((resp: Comunidade)=>{
      this.comunidade = resp
      
      console.log(id)
      this.listComunidadePostagem
      
    })
  }


  findAllTemas() {
    this.ComunidadeService.getAllComunidade().subscribe((resp: Comunidade[]) => {
      this.listaComunidades = resp
    })
  }


}
