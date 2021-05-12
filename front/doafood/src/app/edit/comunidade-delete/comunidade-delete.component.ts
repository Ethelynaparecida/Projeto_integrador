import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Comunidade } from 'src/app/model/Comunidade';
import { ComunidadeService } from 'src/app/service/comunidade.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-comunidade-delet',
  templateUrl: './comunidade-delete.component.html',
  styleUrls: ['./comunidade-delete.component.css']
})
export class ComunidadeDeleteComponent implements OnInit {

  comunidade: Comunidade = new Comunidade()
  idComunidade: number

  constructor(
    private comunidadeService: ComunidadeService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    if(environment.token == ''){
      this.router.navigate(['/entrar'])
    }
    this.idComunidade = this.route.snapshot.params['id']
    this.findByIdComunidade(this.idComunidade)
  }

  findByIdComunidade(id: number){
    this.comunidadeService.getByIdComunidade(id).subscribe((resp: Comunidade)=>{
      this.comunidade=resp
    })
  }

  apagar(){
    this.comunidadeService.deleteComunidade(this.idComunidade).subscribe(()=>{
      this.router.navigate(['/comunidade'])
    })
  }

}