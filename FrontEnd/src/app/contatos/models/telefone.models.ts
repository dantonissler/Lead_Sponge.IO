import { Contato } from './contato.models';
export class Telefone {
    id: number;
    telefone: string;
    tipoTelefone = 'COMERCIAL';
    contato = new Array<Contato>();
}