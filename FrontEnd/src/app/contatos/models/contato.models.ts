import { Email } from './email.models';
import { Telefone } from './telefone.models';

export class Contato {
    id: number;
    nome: string;
    cargo: string;
    telefone = new Array<Telefone>();
    email = new Array<Email>();
}