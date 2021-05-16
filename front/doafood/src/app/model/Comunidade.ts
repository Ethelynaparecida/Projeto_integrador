import { Postagem } from "./Postagem"

export class Comunidade {
    public id: number
    public nome: string
    public postagem: Postagem[]
    public cidade: string
    public sobre: string
}