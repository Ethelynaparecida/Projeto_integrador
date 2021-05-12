import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Postagem } from 'src/app/model/Postagem';
import { Comunidade } from 'src/app/model/Comunidade';
import { User } from 'src/app/model/User';
import { AuthService } from 'src/app/service/auth.service';
import { PostagemService } from 'src/app/service/postagem.service';
import { ComunidadeService } from 'src/app/service/comunidade.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-postagem-edit',
  templateUrl: './postagem-edit.component.html',
  styleUrls: ['./postagem-edit.component.css']
})
export class PostagemEditComponent implements OnInit {

  postagem: Postagem = new Postagem()
  listaPostagem: Postagem[]



  comunidade: Comunidade = new Comunidade()
  listaComunidades: Comunidade[]
  idComunidade: number

  user: User = new User()
  idUser = environment.id




  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private postagemService: PostagemService,
    private comunidadeService: ComunidadeService,
    private auth: AuthService
  ) { }

  ngOnInit() {

    window.scrollTo(0, 0)

    if (environment.token == "") {
      //alert('Sua seção expirou, faça o login novamente!')
      this.router.navigate(['/entrar'])
    }

    let id = this.route.snapshot.params['id']

    this.getAllComunidades()

    this.getByIdUser()
    this.getByIdPostagem(id)

  }


  atualizarPostagem() {
    this.comunidade.id = this.idComunidade
    this.postagem.comunidade = this.comunidade

    this.postagemService.putPostagem(this.postagem).subscribe((resp: Postagem) => {
      this.postagem = resp
      this.router.navigate(['/inicio'])
      alert("Atualização completa!")


    })
  }

  getAllComunidades() {

    this.comunidadeService.getAllComunidade().subscribe((resp: Comunidade[]) => {
      this.listaComunidades = resp
    })

  }

  getByIdComunidade() {


    this.comunidadeService.getByIdComunidade(this.idComunidade).subscribe((resp: Comunidade) => {
      this.comunidade = resp
    })

  }

  getByIdPostagem(id: number) {

    this.postagemService.getByIdPostagem(id).subscribe((resp: Postagem) => {
      this.postagem = resp
    })
  }

  getByIdUser() {
    this.auth.getByIdUser(this.idUser).subscribe((resp: User) => {
      this.user = resp

    })
  }
}
