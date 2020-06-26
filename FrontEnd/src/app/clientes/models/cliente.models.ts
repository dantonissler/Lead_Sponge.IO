import { Segmento } from 'src/app/segmentos/models/segmento.models';

export class Cliente{
    id: number;
    nome: string;
    segmento = new Array<Segmento>();
	url: string;
    resumo: string;
    createdAt: Date;
    updatedAt: Date;
    createdByUser: string;
    modifiedByUser: string;
}