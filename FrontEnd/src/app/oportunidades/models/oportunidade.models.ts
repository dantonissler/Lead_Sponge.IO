import { Cliente } from '../../clientes/models/cliente.models';
export class Oportunidade {
    id: number;
    nome: string;
    cargo: string;
    cliente = new Cliente();
}