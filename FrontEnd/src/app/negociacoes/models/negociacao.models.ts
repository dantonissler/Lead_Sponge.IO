import { Cliente } from '../../clientes/models/cliente.models';
export class negociacao{
    id: number;
    nome: string;
    cargo: string;
    cliente = new Cliente();
}