import { Email } from './email.models';
import { Telefone } from './telefone.models';

export class Contato {
    id: number;
    nome: string;
    cargo: string;
    emails = new Array<Telefone>();
    telefones = new Array<Email>();
}