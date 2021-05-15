import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Postagem } from 'src/app/model/Postagem';
import { Comunidade } from 'src/app/model/Comunidade';
import { User } from 'src/app/model/User';
import { AuthService } from 'src/app/service/auth.service';
import { PostagemService } from 'src/app/service/postagem.service';
import { ComunidadeService } from 'src/app/service/comunidade.service';
import { environment } from 'src/environments/environment.prod';
import { AlertasService } from 'src/app/service/alertas.service';

@Component({
  selector: 'app-postagem-delet',
  templateUrl: './postagem-delet.component.html',
  styleUrls: ['./postagem-delet.component.css']
})
export class PostagemDeletComponent implements OnInit {

  postagem: Postagem = new Postagem()
  listaPostagem: Postagem[]
  idPost: number



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
    private auth: AuthService,
    private alertas: AlertasService
  ) { }

  ngOnInit() {

    window.scrollTo(0, 0)

    if (environment.token == "") {
      //alert('Sua seção expirou, faça o login novamente!')
      this.router.navigate(['/entrar'])
    }

    this.idPost = this.route.snapshot.params['id']

    this.getByIdUser()
    this.getByIdPostagem(this.idPost)

  }


  apagarPostagem() {
    this.postagemService.deletePostagem(this.idPost).subscribe(() => {
      this.router.navigate(['/inicio'])
      this.alertas.showAlertSuccess('Postagem apagada com Sucesso!')

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
