import { Contato } from './contato.models';
export class Email {
    id: number;
    nome: string;
    contato = new Array<Contato>();
}