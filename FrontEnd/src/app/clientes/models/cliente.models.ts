import { Contato } from './../../contatos/models/contato.models';
import { Segmento } from 'src/app/segmentos/models/segmento.models';
import { Negociacao } from 'src/app/negociacoes/models/negociacao.models';

export class Cliente{
    id: number;
    nome: string;
    /* segmento = new Array<Segmento>(); */
	url: string;
    resumo: string;
    /* contato = new Array<Contato>(); */
    negociacoes = new Array<Negociacao>();
    createdAt: Date;
    updatedAt: Date;
    createdByUser: string;
    modifiedByUser: string;
}