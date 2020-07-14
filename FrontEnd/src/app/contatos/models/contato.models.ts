import { Cliente } from './../../clientes/models/cliente.models';
import { Email } from './email.models';
import { Telefone } from './telefone.models';

export class Contato {
    id: number;
    nome: string;
    cargo: string;
    cliente = new Cliente();
    telefone = new Array<Telefone>();
    email = new Array<Email>();
    constructor(id?: number,nome?: string,cargo?: string) {
          this.id = id;
          this.nome = nome;
          this.cargo = cargo;
      }
}