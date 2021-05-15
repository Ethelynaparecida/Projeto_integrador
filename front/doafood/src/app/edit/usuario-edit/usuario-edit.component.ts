import { UserLogin } from './../../model/UserLogin';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/User';
import { AuthService } from 'src/app/service/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { AlertasService } from 'src/app/service/alertas.service';

@Component({
  selector: 'app-usuario-edit',
  templateUrl: './usuario-edit.component.html',
  styleUrls: ['./usuario-edit.component.css']
})
export class UsuarioEditComponent implements OnInit {

  user: User = new User()
  tipoUsuario: string



  idUser: number


  confirmarSenha: string
  confirmarSenha1: string



  constructor(
    private auth: AuthService,
    private route: ActivatedRoute,
    private router: Router,
    private alertas: AlertasService
  ) { }

  ngOnInit() {
    window.scrollTo(0, 0)

    if (environment.token == '') {
      this.router.navigate(['/entrar'])
    }

    this.idUser = this.route.snapshot.params['id']
    this.findByIdUserLogin(this.idUser)
  }

  confirmSenha(event: any) {
    this.confirmarSenha = event.target.value

  }

  tipoUser(event: any) {
    this.tipoUsuario = event.target.value
  }
  atualizar() {
    this.user.tipo = this.tipoUsuario
    if (this.user.senha != this.confirmarSenha) {
      this.alertas.showAlertDanger('As senhas estão diferentes')
    } else {
      this.auth.putUser(this.user).subscribe((resp: User) => {
        this.user = resp
        this.router.navigate(['/inicio'])
        this.alertas.showAlertSuccess('Usuarie atualizado com sucesso!')
      })
    }

    environment.token = ''
    environment.nome = ''
    environment.id = 0
    environment.tipo = ''

    this.router.navigate(['/entrar'])
  }

  findByIdUserLogin(id: number) {
    this.auth.getByIdUser(id).subscribe((resp: User) => {
      this.user = resp
    })
  }

}

