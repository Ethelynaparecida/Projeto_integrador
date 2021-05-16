import { Comunidade } from './Comunidade';
import { User } from './User';
export class Postagem{
  public id: number
  public quantidade: string
  public categoria: string
  public data: Date
  public usuario: User
  public comunidade: Comunidade
  public inscricao: User[]
  public descricao: string
  
}