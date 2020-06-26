import { Negociacao } from './../../negociacoes/models/negociacao.models';
import { Cliente } from './../../clientes/models/cliente.models';
import { Usuario } from './../../usuarios/models/usuario.model';
export class Tarefa {
    id: number;
    assunto: string;
    descricao: string;
    horaMarcada: Date;
    tipo: 'EMAIL';
    usuario = new Usuario();
    cliente = new Cliente();
    negociacao = new Negociacao();
    createdAt: Date;
    updatedAt: Date;
    createdByUser: string;
    modifiedByUser: string;
}