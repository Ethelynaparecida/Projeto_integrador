import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';


@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  nome = environment.nome
  telefone = environment.telefone
  email = environment.email
  id = environment.id

  constructor(
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  sair(){
    this.router.navigate(['/entrar'])
    environment.token = ''
    environment.telefone = ''
    environment.nome = ''
    environment.id = 0
    environment.email = ''
  }

  tipoMenu() {
    let ok: boolean = false

    if (environment.token != "") {
      ok = true
    }

    return ok
  }

}
