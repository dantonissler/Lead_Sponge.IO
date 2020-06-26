import { Cliente } from '../../clientes/models/cliente.models';
export class Negociacao{
    id: number;
    nome: string;
    cargo: string;
    cliente = new Cliente();
}