
import { Postagem } from './Postagem';
export class User{
  public id: number;
  public nome: string
  public email: string
  public senha: string
  public telefone: string
  public tipo: string
  public postagem: Postagem[]
  
}