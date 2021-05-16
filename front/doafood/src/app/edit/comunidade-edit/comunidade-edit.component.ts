import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Comunidade } from 'src/app/model/Comunidade';
import { AlertasService } from 'src/app/service/alertas.service';
import { ComunidadeService } from 'src/app/service/comunidade.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-comunidade-edit',
  templateUrl: './comunidade-edit.component.html',
  styleUrls: ['./comunidade-edit.component.css']
})
export class ComunidadeEditComponent implements OnInit {

  comunidade: Comunidade = new Comunidade()

  constructor(
    private comunidadeService: ComunidadeService,
    private router: Router,
    private route: ActivatedRoute,
    private alertas: AlertasService
  ) { }

  ngOnInit() {
    if(environment.token == ''){
      this.router.navigate(['/entrar'])
    }
    let id=this.route.snapshot.params['id']
    this.findByIdComunidade(id)
  }

  findByIdComunidade(id: number){
    this.comunidadeService.getByIdComunidade(id).subscribe((resp: Comunidade)=>{
      this.comunidade = resp
    })
  }

  atualizar(){
    this.comunidadeService.putComunidade(this.comunidade).subscribe((resp: Comunidade)=>{
      this.comunidade = resp
      this.alertas.showAlertSuccess('Comunidade atualizando!')
      this.router.navigate(['/comunidade'])
    })
  }

}

