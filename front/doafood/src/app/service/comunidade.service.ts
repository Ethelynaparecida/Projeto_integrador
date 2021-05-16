import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Comunidade } from '../model/Comunidade';

@Injectable({
  providedIn: 'root'
})
export class ComunidadeService {

  constructor(
    private http: HttpClient
  ) { }

  token = {
    headers: new HttpHeaders().set('Authorization', environment.token)
  }

  getAllComunidade(): Observable<Comunidade[]> {
    return this.http.get<Comunidade[]>('http://localhost:8080/comunidade', this.token)
  }

  postComunidade(comunidade: Comunidade): Observable<Comunidade> {
    return this.http.post<Comunidade>('http://localhost:8080/comunidade', comunidade, this.token)
  }

  putComunidade(comunidade: Comunidade): Observable<Comunidade>{
    return this.http.put<Comunidade>('http://localhost:8080/comunidade', comunidade, this.token)
  }

  getByIdComunidade(id: number): Observable<Comunidade>{
    return this.http.get<Comunidade>(`http://localhost:8080/comunidade/${id}`, this.token)
  }

  deleteComunidade(id: number){
    return this.http.delete(`http://localhost:8080/comunidade/${id}`, this.token)
  }

}
