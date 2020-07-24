import { Estagio } from './../../estagioNegociacao/models/estagio-negociacao.models';
import { Fonte } from './../../fonteNegociacao/models/fonte.models';
import { Campanha } from './../../campanhas/models/campanha.models';
import { Perda } from './../../motivoPerda/models/motivo-perda.models';
import { Cliente } from '../../clientes/models/cliente.models';
import { NegociacaoProduto } from './negociacao-produto-models';
import { HistEstagioNegociacao } from './historico-estagio-negociacoes.moduls';

export class Negociacao{
    id: number;
    nome: string;
    estatus: string;
    avaliacao: number;
    valorTotal: number;
    valorMensal: number;
    valorUnico: number;
    createdAt:Date;
    motivoPerda = new Perda();
    cliente = new Cliente();
    campanha = new Campanha();
    fonteNegociacao = new Fonte();
    estagio = new Estagio();
    negociacaoProduto = new NegociacaoProduto();
    histEstagioNegociacoes = new HistEstagioNegociacao();
}
