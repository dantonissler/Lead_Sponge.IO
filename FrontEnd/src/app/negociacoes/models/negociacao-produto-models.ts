import { Produto } from './../../produtos/models/produto.models';

export class NegociacaoProduto{
    id: number;
    valor: number;
    desconto: number;
    quantidade: number;
    temDesconto: boolean;
    reincidencia: string;
    tipoDesconto: string;
    produto = new Produto();
}
