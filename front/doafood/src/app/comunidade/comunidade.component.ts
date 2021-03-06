import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { Comunidade } from '../model/Comunidade';
import { AlertasService } from '../service/alertas.service';
import { ComunidadeService } from '../service/comunidade.service';

@Component({
  selector: 'app-comunidade',
  templateUrl: './comunidade.component.html',
  styleUrls: ['./comunidade.component.css']
})
export class ComunidadeComponent implements OnInit {

  comunidade: Comunidade = new Comunidade()
  listaComunidades: Comunidade[]

  constructor(
    private router: Router,
    private comunidadeService: ComunidadeService,
    private alertas: AlertasService
  ) { }

  ngOnInit() {
    if (environment.token == "") {
      this.router.navigate(['/entrar'])
    }
    this.findAllComunidades()
  }
  
  findAllComunidades() {
    this.comunidadeService.getAllComunidade().subscribe((resp: Comunidade[]) => {
      this.listaComunidades = resp
    })
  }

  cadastrar() {
    this.comunidadeService.postComunidade(this.comunidade).subscribe((resp: Comunidade) => {
      this.comunidade = resp
      this.alertas.showAlertSuccess('Comunidade cadastrada!')
      
      this.findAllComunidades()
      this.comunidade = new Comunidade()
    }
    )
  }

}
